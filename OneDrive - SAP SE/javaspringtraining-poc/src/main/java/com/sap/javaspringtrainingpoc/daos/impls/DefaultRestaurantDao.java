package com.sap.javaspringtrainingpoc.daos.impls;

import com.sap.javaspringtrainingpoc.daos.RestaurantDao;
import com.sap.javaspringtrainingpoc.models.Restaurant;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by I863409 on 24/07/2017.
 */
public class DefaultRestaurantDao  implements RestaurantDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    //@Transactional
    public List<Restaurant> listRestaurants() {
        List<Restaurant> restaurants;
        //This try automatically closes the session
        //If it is not used, is mandatory to use @Transactional
        try(Session session = sessionFactory.openSession()) {
            DetachedCriteria dc = DetachedCriteria
                    .forClass(Restaurant.class , "r")
                    .addOrder(Order.asc("name"));

            return dc.getExecutableCriteria(session).list();
        }
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {

        Session session = sessionFactory.getCurrentSession();
        session.save(restaurant);
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        //It is already declared as transaction in the service
        Session session = sessionFactory.getCurrentSession();
        session.update(restaurant);
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        try(Session session = sessionFactory.openSession()){
            return session.get(Restaurant.class,restaurantId);
        }
    }

    @Override
    public Restaurant getRestaurantByName(String restaurantName) {
        try(Session session = sessionFactory.openSession()){
            DetachedCriteria criteria = DetachedCriteria.forClass(Restaurant.class);
            criteria.add(Restrictions.like("name", restaurantName, MatchMode.ANYWHERE));
            return (Restaurant) criteria.getExecutableCriteria(session).uniqueResult();
        }
    }


    @Override
    public void deleteRestaurant(int restaurantId) {
        Session session = sessionFactory.getCurrentSession();
        Restaurant restaurant = session.load(Restaurant.class,restaurantId);
        session.delete(restaurant);
    }
}
