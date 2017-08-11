package com.sap.javaspringtrainingpoc.daos;

import com.sap.javaspringtrainingpoc.models.Restaurant;
import com.sap.javaspringtrainingpoc.models.User;
import com.sap.javaspringtrainingpoc.models.VoteHistory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by I863409 on 08/08/2017.
 */
public interface VoteDao {
    void addRestaurantToPoll(Restaurant restaurant);

    void addUserVoteToRestaurant(User user, Restaurant restaurant);

    void removeUserVoteToRestaurant(User user, Restaurant restaurant);

    List<VoteHistory> getTodayVoteHistory();

    List<VoteHistory> getRestaurantVoteHistory(int restaurantId);

    List<VoteHistory> getUserVoteHistory(int userId);

    List<User> getRestaurantVotersToday(int restaurantId);

}
