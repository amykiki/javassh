package org.mybatis.smvc.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Amysue on 2016/5/31.
 */
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ForbiddenValidator.class)
@Documented
public @interface Forbidden {
    String message() default "含有违禁词";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String[] value();

    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Forbidden[] value();
    }

}
