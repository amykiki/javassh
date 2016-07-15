package com.github.amysue.interfaces;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Amysue on 2016/6/30.
 */
public class InterfaceImplTest {
    InterfaceImpl ifImple = new InterfaceImpl();

    @Test
    public void testPrintSelf() throws Exception {
        ifImple.printSelf();
        System.out.println(InterfaceJava8.isNull("hahha"));
        System.out.println(InterfaceJava8.isNull(null));
        System.out.println(InterfaceJava8.isNull("   "));
        ifImple.printStr("  ");
        ifImple.printStr("hahhha");
        ifImple.isNull("");
    }
}