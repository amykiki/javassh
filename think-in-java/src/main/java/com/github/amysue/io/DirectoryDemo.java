package com.github.amysue.io;

import java.io.File;

/**
 * Created by Amysue on 2016/8/26.
 */
public class DirectoryDemo {
    public static void main(String[] args) {
        Directory.TreeInfo ti = Directory.walk("D:\\IdealProj\\SSH\\think-in-java\\src\\main\\java\\com\\github\\amysue");
//        String regex = "M.*\\.java";
        String regex = ".*[Cc].\\.java";
        Directory.TreeInfo ti2 = Directory.walk("D:\\IdealProj\\SSH\\think-in-java\\src\\main\\java\\com\\github\\amysue", regex);
        PPrint.pprint(ti.dirs);
        PPrint.pprint(ti.files);
        System.out.println("============================================");
        for (File file : ti2) {
            System.out.println(file);
        }
        System.out.println("============================================");
        for (File file : Directory.local("D:\\Java\\ebook", ".*leetcode.*")) {
            System.out.println(file);
        }
    }
}
