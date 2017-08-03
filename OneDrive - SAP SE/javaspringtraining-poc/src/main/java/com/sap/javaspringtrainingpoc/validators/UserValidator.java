package com.sap.javaspringtrainingpoc.validators;

import org.springframework.validation.Errors;

/**
 * Created by I863409 on 02/08/2017.
 */
public interface UserValidator {
    public boolean supports(Class targetclass);
    public void validate(Object target, Errors errors);
}
