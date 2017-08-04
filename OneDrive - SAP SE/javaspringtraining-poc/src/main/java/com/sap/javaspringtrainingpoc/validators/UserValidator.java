package com.sap.javaspringtrainingpoc.validators;

import com.sap.javaspringtrainingpoc.models.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by I863409 on 02/08/2017.
 */
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class targetClass) {
        return User.class.isAssignableFrom(targetClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "required.password", "Field name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                "required.confirmPassword", "Field name is required.");

        User user = (User)target;

        if(!(user.getPassword().equals(user.getPasswordConfirm()))){
            errors.rejectValue("password", "erro especificado no validator");
        }

        //super.validate(target, errors);
    }
}
