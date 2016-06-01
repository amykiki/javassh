package org.mybatis.smvc.validators;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Amysue on 2016/5/31.
 */
public class ForbiddenValidator implements ConstraintValidator<Forbidden, String>{
    private String[] forbiddens;
    @Override
    public void initialize(Forbidden forbidden) {
        this.forbiddens = forbidden.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s)) {
            return true;
        }
        if (forbiddens.length <= 0) {
            return true;
        }
        boolean isValid = true;
        String fWord = "";
        for (String word : forbiddens) {
            if (s.contains(word)) {
                isValid = false;
                fWord = word;
                break;
            }
        }
        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("含有违禁词[" + fWord + "]").addConstraintViolation();
        }
        return isValid;
    }
}
