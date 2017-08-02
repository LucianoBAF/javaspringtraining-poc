package com.sap.javaspringtrainingpoc.services.impls;

import com.sap.javaspringtrainingpoc.daos.UserDao;
import com.sap.javaspringtrainingpoc.models.User;
import com.sap.javaspringtrainingpoc.services.UserService;
import javafx.scene.control.Alert;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by I863409 on 02/08/2017.
 */
public class DefaultUserService implements UserService{

    private UserDao userDao;

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);

        Alert alert = null;
        alert.setTitle("User saved");
        alert.show();
    }

    @Override
    public boolean userEmailExists(String userEmail) {
        boolean emailExists = userDao.userEmailExists(userEmail);

        Alert alert = null;
        alert.setTitle("Email exists: "+ emailExists);
        alert.show();

        return emailExists;
    }
}
