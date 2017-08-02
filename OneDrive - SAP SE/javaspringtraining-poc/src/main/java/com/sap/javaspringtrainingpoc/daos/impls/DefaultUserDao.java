package com.sap.javaspringtrainingpoc.daos.impls;

import com.sap.javaspringtrainingpoc.daos.UserDao;
import com.sap.javaspringtrainingpoc.models.User;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;


/**
 * Created by I863409 on 02/08/2017.
 */
public class DefaultUserDao implements UserDao{
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public boolean userEmailExists(String userEmail) {
        try(Session session = sessionFactory.openSession()){
            DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
            criteria.add(Restrictions.like("email", userEmail.toString(), MatchMode.EXACT));
            boolean dontExists = criteria.getExecutableCriteria(session).uniqueResult().equals(null);
            return  !dontExists;
        }
    }
}
