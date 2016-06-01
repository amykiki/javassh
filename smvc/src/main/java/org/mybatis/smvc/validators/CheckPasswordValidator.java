package org.mybatis.smvc.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

/**
 * Created by Amysue on 2016/6/1.
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class CheckPasswordValidator implements ConstraintValidator<CheckPassword, Object[]> {
    @Override
    public void initialize(CheckPassword checkPassword) {

    }

    @Override
    public boolean isValid(Object[] objects, ConstraintValidatorContext constraintValidatorContext) {
        if (objects.length < 2) {
            throw new IllegalArgumentException("必须输入新密码和确认密码");
        }
        if (objects[0] == null || objects[1] == null) {
            return true;
        }
        String newPwd = (String) objects[0];
        String confirmPwd = (String) objects[1];
        if (!newPwd.equals(confirmPwd)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("新密码和确认密码不一致")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
