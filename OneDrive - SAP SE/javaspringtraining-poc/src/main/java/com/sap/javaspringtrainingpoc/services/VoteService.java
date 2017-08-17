package com.sap.javaspringtrainingpoc.services;

import com.sap.javaspringtrainingpoc.models.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by I863409 on 08/08/2017.
 */
public interface VoteService {

    void addRestaurantToPoll(Restaurant restaurant);

    void addUserVoteToRestaurant(User user, Restaurant restaurant);

    void removeUserVoteToRestaurant(User user, Restaurant restaurant);

    List<VoteHistoryData> getCompleteVoteHistory();

    List<VoteHistory> getTodayVoteHistory();

    List<VoteHistory> getVoteHistoryByDate(LocalDate date);

    List<VoteHistory> getRestaurantVoteHistory(int restaurantId);

    List<VoteHistory> getUserVoteHistory(int userId);

    List<User> getRestaurantVotersToday(int restaurantId);

    Restaurant getVoterRestaurantToday(int userId);

    List<RestaurantData> getTodayRestaurantsDataByVotes(List<Restaurant> restaurants,List<VoteHistory> voteHistoryToday);

    int getTodayRestaurantIdVotedByUserEmail(List<VoteHistory> voteHistoryToday, String loggedUserEmail);
}
