package com.github.amysue.concurrency;

/**
 * Created by Amysue on 2016/8/8.
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
