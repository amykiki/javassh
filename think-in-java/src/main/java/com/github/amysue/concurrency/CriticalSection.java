package com.github.amysue.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Amysue on 2016/8/13.
 */
public class CriticalSection {
    class Pair {
        private int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair() {
            this(0, 0);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
        public void incrementX(){x++;}
        public void incrementY(){y++;}

        public String toString() {
            return "x: " + x + ", y: " + y;
        }

        public class PairValuesNotEqualException extends RuntimeException {
            public PairValuesNotEqualException() {
                super("Pair values not equal: " + Pair.this);
            }
        }

        public void checkState() {
            if (x != y) {
                throw new PairValuesNotEqualException();
            }
        }
    }

    class RunPair implements Runnable {
        private Pair pair;

        public RunPair(Pair pair) {
            this.pair = pair;
        }

        @Override
        public void run() {
            while (true) {
                pair.incrementX();
                pair.incrementY();
                try {
                    pair.checkState();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread());
                    System.exit(1);
                }
            }
        }
    }
    public void testPair() {
        Pair pair = new Pair();
        Pair pair2 = new Pair();
        ExecutorService exec = Executors.newCachedThreadPool();
        RunPair rp1 = new RunPair(pair), rp2 = new RunPair(pair), rp3 = new RunPair(pair2);
        exec.execute(rp1);
        exec.execute(rp2);
        exec.execute(rp3);
        exec.shutdown();
    }

    abstract class PairManager {
        AtomicInteger checkCounter = new AtomicInteger(0);
        protected Pair p = new Pair();
        private List<Pair> storage = Collections.synchronizedList(new ArrayList<>());

        public synchronized Pair getPair() {
            return new Pair(p.getX(), p.getY());
        }

        protected void store(Pair p) {
            storage.add(p);
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {}
        }

        public abstract void increment();
    }

    class PairManager1 extends PairManager {
        public synchronized void increment() {
            p.incrementX();
            p.incrementY();
            store(getPair());
        }
    }

    class PairManager2 extends PairManager {
        public void increment() {
            Pair temp;
            synchronized (this) {
                p.incrementX();
                p.incrementY();
                temp = getPair();
            }
            store(temp);
        }
    }

    class PairManipulator implements Runnable {
        private PairManager pm;

        public PairManipulator(PairManager pm) {
            this.pm = pm;
        }

        @Override
        public void run() {
            while (true) {
                pm.increment();
                pm.getPair().checkState();
            }
        }

        public String toString() {
            return "Pair: " + pm.getPair() + " checkCounter = " + pm.checkCounter.get();
        }
    }
    public void testPairManage() {
        PairManager pman = new PairManager1();
        PairManipulator pm1 = new PairManipulator(pman);
        PairManipulator pm2 = new PairManipulator(pman);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(pm1);
        exec.execute(pm2);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e){
            System.out.println("SLeep interrupted");
        }
        System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
        System.exit(0);
    }

    class PairChecker implements Runnable {
        private PairManager pm;

        public PairChecker(PairManager pm) {
            this.pm = pm;
        }

        @Override
        public void run() {
            while (true) {
                pm.checkCounter.incrementAndGet();
                pm.getPair().checkState();
            }
        }
    }
    class ExplicitPairManager1 extends PairManager {
        private Lock lock = new ReentrantLock();

        @Override
        public synchronized void increment() {
            lock.lock();
            try {
                p.incrementX();
                p.incrementY();
                store(getPair());
            } finally {
                lock.unlock();
            }
        }
    }

    class ExplicitPairManager2 extends PairManager {
        private Lock lock = new ReentrantLock();
        @Override
        public void increment() {
            Pair temp;
            lock.lock();
            try {
                p.incrementX();
                p.incrementY();
                temp = getPair();
            }finally {
                lock.unlock();
            }
            store(temp);
        }
    }

    public void testApproaches() {
        PairManager pman1 = new PairManager1();
        PairManager pman2 = new PairManager2();
//        PairManager pman1 = new ExplicitPairManager1();
//        PairManager pman2 = new ExplicitPairManager2();
        ExecutorService exec = Executors.newCachedThreadPool();
        PairManipulator pm1 = new PairManipulator(pman1);
        PairManipulator pm2 = new PairManipulator(pman2);
        PairChecker pcheck1 = new PairChecker(pman1);
        PairChecker pcheck2 = new PairChecker(pman2);
        exec.execute(pm1);
        exec.execute(pm2);
        exec.execute(pcheck1);
        exec.execute(pcheck2);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Sleep Interrupted");
        }
        System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
        System.exit(0);
    }
    public static void main(String[] args) {
        CriticalSection cs = new CriticalSection();
//        cs.testPair();
//        cs.testPairManage();
        cs.testApproaches();
    }

}
