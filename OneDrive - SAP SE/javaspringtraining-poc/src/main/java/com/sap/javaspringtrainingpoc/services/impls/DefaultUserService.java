package com.sap.javaspringtrainingpoc.services.impls;

import com.sap.javaspringtrainingpoc.daos.UserDao;
import com.sap.javaspringtrainingpoc.models.User;
import com.sap.javaspringtrainingpoc.services.UserService;
import javafx.scene.control.Alert;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.*;

/**
 * Created by I863409 on 02/08/2017.
 */
public class DefaultUserService implements UserService{

    @Resource
    private UserDao userDao;

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);

        //JOptionPane.showMessageDialog(null, "User saved");
    }

    @Override
    public boolean userEmailExists(String userEmail) {
        boolean emailExists = userDao.userEmailExists(userEmail);
        return emailExists;
    }

    @Override
    public boolean confirmPassword(User user){
        return userDao.confirmPassword(user);
    }

}
