package com.github.amysue.io;

import java.io.File;
import java.io.IOException;

/**
 * Created by Amysue on 2016/8/26.
 */
public class ProcessFile {
    public interface Strategy {
        void process(File file);
    }
    private Strategy strategy;
    private String ext;

    public ProcessFile(Strategy strategy, String ext) {
        this.strategy = strategy;
        this.ext = ext;
    }

    public void start(String[] args) {
        try {
            if (args.length == 0) {
                processDirectoryTree(new File("."));
            } else {
                for (String arg : args) {
                    File fileArg = new File(arg);
                    if (fileArg.isDirectory()) {
                        processDirectoryTree(fileArg);
                    } else {
                        if (!arg.endsWith("." + ext)) {
                            arg += "." + ext;
                        }
                        strategy.process(new File(arg).getCanonicalFile());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processDirectoryTree(File root) throws IOException{
        for (File file : Directory.walk(root.getAbsolutePath(), ".*\\." + ext)) {
            strategy.process(file.getCanonicalFile());
        }
    }

    public static void main(String[] args) {
        String[] dirs = {"D:\\Download", "D:\\Java\\ebook\\leetcode-solution.pdf"};
        new ProcessFile(new Strategy(){
            @Override
            public void process(File file) {
                System.out.println(file);
            }
        }, "zip").start(dirs);

    }
}
