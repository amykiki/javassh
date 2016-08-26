package com.github.amysue.io;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Amysue on 2016/8/26.
 */
public class PPrint {
    public static String pformat(Collection<?> c) {
        if (c.size() == 0) {
            return "[]";
        }
        StringBuilder result = new StringBuilder("[");
        for (Object elem : c) {
            if (c.size() != 1) {
                result.append("\n");
            }
            result.append(elem);
        }
        if (c.size() != 1) {
            result.append("\n");
        }
        result.append("]");
        return result.toString();
    }

    public static void pprint(Collection<?> collection) {
        System.out.println(pformat(collection));
    }

    public static void pprint(Object[] objects) {
        System.out.println(pformat(Arrays.asList(objects)));
    }
}
