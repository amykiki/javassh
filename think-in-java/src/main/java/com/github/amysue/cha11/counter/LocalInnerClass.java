package com.github.amysue.cha11.counter;

/**
 * Created by Amysue on 2016/6/22.
 */
public class LocalInnerClass {
    private int count = 0;

    Counter getCounter(final String name) {
        class LocalCounter implements Counter {
            public LocalCounter() {
                System.out.println("Local Counter");
            }

            public int next() {
                System.out.print(name);
                return count++;
            }
        }
        return new LocalCounter();
    }
    Counter getCounter1(final String name) {
        class LocalCounter1 implements Counter {
            public LocalCounter1() {
                System.out.println("Local Counter1");
            }

            public int next() {
                System.out.print(name);
                return count++;
            }
        }
        return new LocalCounter1();
    }
    Counter getCounter2(final String name) {
        return new Counter(){
            {
                System.out.println("Anonymous Counter()");
            }

            public int next() {
                System.out.print(name);
                return count++;
            }
        };
    }

    public class LocalCounter3 implements Counter{
        private String name;
        public LocalCounter3(String name) {
            System.out.println("Local Counter3");
            this.name = name;
        }
        public int next() {
            System.out.print(name);
            return count++;
        }
    }

    public static class staticCounter implements Counter {
        private int i;
        private String name;
        public staticCounter(int i, String name) {
            System.out.println("Static Counter");
            this.i = i;
            this.name = name;
        }
        public int next() {
            System.out.print(name);
            return i++;
        }
    }

    public static void main(String[] args) {
        LocalInnerClass lic = new LocalInnerClass();
        Counter c1 = lic.getCounter("Local Inner "),
                c2 = lic.getCounter2("Anonymous Inner "),
                c3 = lic.getCounter1("Local Inner2 "),
                c4 = lic.new LocalCounter3("Local Inner3 ");
        lic.printCounter(c1);
        lic.printCounter(c3);
        lic.printCounter(c2);
        lic.printCounter(c4);
        Counter c5 = new LocalInnerClass.staticCounter(c4.next(), "Static Inner ");
        lic.printCounter(c5);
    }

    public void printCounter(Counter counter) {
        for (int i = 0; i < 5; i++) {
            System.out.println(counter.next());
        }
    }
}
