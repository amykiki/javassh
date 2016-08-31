package com.github.amysue.io;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Amysue on 2016/8/31.
 */
public class OSExecute {
    public static void command(String command) {
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = results.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        OSExecute.command("javap D:\\IdealProj\\Leetcode\\out\\production\\Leetcode\\UniquePath6202.class");
    }
}
