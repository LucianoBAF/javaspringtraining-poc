package com.sap.javaspringtrainingpoc.services.impls;

import com.sap.javaspringtrainingpoc.daos.UserDao;
import com.sap.javaspringtrainingpoc.models.User;
import com.sap.javaspringtrainingpoc.services.UserService;
import javafx.scene.control.Alert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.*;

/**
 * Created by I863409 on 02/08/2017.
 */
public class DefaultUserService implements UserService{

    @Resource
    private UserDao userDao;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void saveUser(User user) {
        encodeUserPasswords(user);
        userDao.saveUser(user);
    }

    @Override
    public boolean userEmailExists(String userEmail) {
        boolean emailExists = userDao.userEmailExists(userEmail);
        return emailExists;
    }

    @Override
    public void encodeUserPasswords(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        user.setPasswordConfirm(encodePassword(user.getPasswordConfirm()));
    }

    @Override
    public boolean confirmPassword(User user){
        User dbUser = userDao.getUserByEmail(user.getPassword());
        return user.getPassword().equals(dbUser.getPassword());
    }

    @Override
    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String userEmail) {
        return userDao.getUserByEmail(userEmail);
    }

}
