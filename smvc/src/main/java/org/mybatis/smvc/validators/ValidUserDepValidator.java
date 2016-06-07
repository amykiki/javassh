package org.mybatis.smvc.validators;

import org.mybatis.smvc.entity.Department;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Amysue on 2016/6/1.
 */
public class ValidUserDepValidator implements ConstraintValidator<ValidUserDep, Department>{
    @Override
    public void initialize(ValidUserDep validUserDep) {
    }

    @Override
    public boolean isValid(Department department, ConstraintValidatorContext constraintValidatorContext) {
        if (department == null) {
            return false;
        }
        return department.getId() > 0;
    }
}
