package org.mybatis.smvc.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

/**
 * Created by Amysue on 2016/6/1.
 */
@Target({FIELD, TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidUserDepValidator.class})
@Documented
public @interface ValidUserDep {
    String message() default "必须选择部门";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
