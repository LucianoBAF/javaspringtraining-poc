package com.sap.javaspringtrainingpoc.daos;

import com.sap.javaspringtrainingpoc.models.User;
/**
 * Created by I863409 on 02/08/2017.
 */
public interface UserDao {
    void saveUser(User user);

    boolean userEmailExists(String userEmail);

//    boolean confirmPassword(User user);

    User getUserByEmail(String email);

    User getUserById (int userId);
}
