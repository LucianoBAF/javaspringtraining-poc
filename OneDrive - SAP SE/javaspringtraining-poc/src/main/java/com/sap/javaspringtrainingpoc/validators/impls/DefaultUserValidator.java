package com.sap.javaspringtrainingpoc.validators.impls;

import com.sap.javaspringtrainingpoc.models.User;
import com.sap.javaspringtrainingpoc.validators.UserValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * Created by I863409 on 02/08/2017.
 */
public class DefaultUserValidator implements UserValidator {
    @Override
    public boolean supports(Class targetClass) {
        return User.class.isAssignableFrom(targetClass);
        //return super.supports(user);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "required.password", "Field name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                "required.confirmPassword", "Field name is required.");

        User user = (User)target;

        if(!(user.getPassword().equals(user.getPasswordConfirm()))){
            errors.rejectValue("password", "notmatch.password");
        }

        //super.validate(target, errors);
    }
}
