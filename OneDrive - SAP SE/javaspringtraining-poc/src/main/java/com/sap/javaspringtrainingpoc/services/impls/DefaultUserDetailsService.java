package com.sap.javaspringtrainingpoc.services.impls;

import com.sap.javaspringtrainingpoc.daos.UserDao;
import com.sap.javaspringtrainingpoc.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by I863409 on 03/08/2017.
 */
public class DefaultUserDetailsService implements UserDetailsService {

    @Resource
    UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException, DataAccessException {
        User user = userDao.getUserByEmail(userEmail);

        if(user != null){
            ArrayList<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));

            return new org.springframework.security.core.userdetails.User(userEmail,user.getPassword(),grantedAuthorityList);
        }

        return null;
    }
}
