package com.sap.javaspringtrainingpoc.daos;

import com.sap.javaspringtrainingpoc.models.Restaurant;

import java.util.List;

/**
 * Created by I863409 on 24/07/2017.
 */
public interface RestaurantDao {

    List<Restaurant> listRestaurants();

    Restaurant getRestaurantById(int id);

    Restaurant getRestaurantByName(String name);

    void saveRestaurant(Restaurant restaurant);

    void updateRestaurant(Restaurant restaurant);

    void deleteRestaurant(int restaurantId);

}
