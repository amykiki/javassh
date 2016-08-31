package com.github.amysue.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Amysue on 2016/8/31.
 */
public class Echo {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = br.readLine()) != null && s.length() != 0) {
            System.out.println(s);
        }
    }
}
