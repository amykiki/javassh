package com.github.amysue.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by Amysue on 2016/8/26.
 */
public class DirList2 {
    public static FilenameFilter fileter(String regex) {
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }

    public static void main(String[] args) {
        File file = new File("D:\\IdealProj\\javaee\\src");
//        String regex = "";
        String regex = "Test.*";
        String[] list;
        if (regex.equals("")) {
            list = file.list();
        } else {
            list = file.list(fileter(regex));
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }
}
