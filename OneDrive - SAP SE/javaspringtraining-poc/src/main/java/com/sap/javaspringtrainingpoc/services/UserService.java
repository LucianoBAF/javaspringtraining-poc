package com.sap.javaspringtrainingpoc.services;

import com.sap.javaspringtrainingpoc.models.User;

/**
 * Created by I863409 on 02/08/2017.
 */
public interface UserService {
    void saveUser(User user);

    boolean userEmailExists(String userEmail);
}
