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
@Constraint(validatedBy = {CheckPasswordValidator.class})
@Target({METHOD, CONSTRUCTOR, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckPassword {
    String message() default "两次输入密码不一致";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
