package com.sap.javaspringtrainingpoc.services;

import com.sap.javaspringtrainingpoc.models.Restaurant;

import java.util.List;

/**
 * Created by I863409 on 25/07/2017.
 */
public interface RestaurantService {
    List<Restaurant> listRestaurants();

    void saveRestaurant(Restaurant restaurant);

    boolean restaurantExists(int restaurantId);

    void updateRestaurant(Restaurant restaurant);

    Restaurant getRestaurantById(int restaurantId);

    Restaurant getRestaurantByName(String name);

    void deleteRestaurant(int restaurantId);
}
