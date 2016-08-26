package com.github.amysue.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by Amysue on 2016/8/26.
 */
public class DirList {
    public static void main(String[] args) {
        File path = new File("D:\\Download");
//        String rgx = "";
        String rgx = ".*\\.exe";
        String[] list;
        if (rgx.equals("")) {
            list = path.list();
        } else {
            list = path.list(new DirFilter(rgx));
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }

}

class DirFilter implements FilenameFilter {
    private Pattern pattern;

    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}