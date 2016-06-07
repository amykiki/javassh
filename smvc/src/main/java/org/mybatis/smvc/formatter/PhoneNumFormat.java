package org.mybatis.smvc.formatter;

import java.lang.annotation.*;

/**
 * Created by Amysue on 2016/6/5.
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PhoneNumFormat {
}
