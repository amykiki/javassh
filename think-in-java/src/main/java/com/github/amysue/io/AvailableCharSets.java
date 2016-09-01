package com.github.amysue.io;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * Created by Amysue on 2016/9/1.
 */
public class AvailableCharSets {
    public static void main(String[] args) {
        SortedMap<String, Charset> charSets = Charset.availableCharsets();
        Iterator<String> it = charSets.keySet().iterator();
        String encoding = System.getProperty("file.encoding");
        System.out.println("encoding = " + encoding);
        while (it.hasNext()) {
            String csName = it.next();
            System.out.print(csName);
            Iterator aliases = charSets.get(csName).aliases().iterator();
            if (aliases.hasNext()) {
                System.out.print(": ");
            }
            while (aliases.hasNext()) {
                System.out.print(aliases.next());
                if (aliases.hasNext()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
