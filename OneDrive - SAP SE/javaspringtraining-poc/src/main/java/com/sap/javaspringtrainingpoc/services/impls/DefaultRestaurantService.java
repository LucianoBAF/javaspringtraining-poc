package com.sap.javaspringtrainingpoc.services.impls;

import com.sap.javaspringtrainingpoc.daos.RestaurantDao;
import com.sap.javaspringtrainingpoc.daos.impls.DefaultRestaurantDao;
import com.sap.javaspringtrainingpoc.models.Restaurant;
import com.sap.javaspringtrainingpoc.services.RestaurantService;
import javafx.scene.control.Alert;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;

/**
 * Created by I863409 on 25/07/2017.
 */
public class DefaultRestaurantService implements RestaurantService {

    @Resource
    private RestaurantDao restaurantDao;

    @Override
    public List<Restaurant> listRestaurants() {

        List<Restaurant> restaurants = null;
        restaurants = restaurantDao.listRestaurants();
        return restaurants;
    }

    @Override
    @Transactional
    public void saveRestaurant(Restaurant restaurant) {
        restaurantDao.saveRestaurant(restaurant);
    }

    @Override
    public boolean restaurantExists(int restaurantId) {
        boolean restaurantExists = false;

        List<Restaurant> restaurants = restaurantDao.listRestaurants();
        for(Restaurant restaurant : restaurants){

            if(restaurant.getId() == restaurantId) {
                restaurantExists = true;
                break;
            }
        }
        return restaurantExists;
    }

    @Override
    @Transactional
    public void updateRestaurant(Restaurant restaurant) {
       Boolean exists = restaurantExists(restaurant.getId());
       restaurantDao.updateRestaurant(restaurant);
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {

        return restaurantDao.getRestaurantById(restaurantId);
    }

    @Override
    public Restaurant getRestaurantByName(String name) {
        return restaurantDao.getRestaurantByName(name);
    }

    @Override
    @Transactional
    public void deleteRestaurant(int restaurantId) {

        restaurantDao.deleteRestaurant(restaurantId);
    }
}
