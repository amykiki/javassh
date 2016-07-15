package com.github.amysue.concurrency;

import java.util.concurrent.Callable;

/**
 * Created by Amysue on 2016/7/15.
 */
public class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "Result of TaskWithResult " + id;
    }
}
