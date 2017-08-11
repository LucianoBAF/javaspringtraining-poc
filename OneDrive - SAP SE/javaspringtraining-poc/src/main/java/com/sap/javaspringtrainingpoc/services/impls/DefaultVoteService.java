package com.sap.javaspringtrainingpoc.services.impls;

import com.sap.javaspringtrainingpoc.daos.VoteDao;
import com.sap.javaspringtrainingpoc.models.Restaurant;
import com.sap.javaspringtrainingpoc.models.User;
import com.sap.javaspringtrainingpoc.models.VoteHistory;
import com.sap.javaspringtrainingpoc.services.VoteService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by I863409 on 08/08/2017.
 */
public class DefaultVoteService implements VoteService {
    @Resource
    private VoteDao voteDao;


    @Override
    @Transactional
    public void addRestaurantToPoll(Restaurant restaurant) {
        voteDao.addRestaurantToPoll(restaurant);
    }

    @Override
    @Transactional
    public void addUserVoteToRestaurant(User user, Restaurant restaurant) {
        voteDao.addUserVoteToRestaurant(user,restaurant);
    }

    @Override
    @Transactional
    public void removeUserVoteToRestaurant(User user, Restaurant restaurant) {
        voteDao.removeUserVoteToRestaurant(user,restaurant);
    }

    @Override
    public List<VoteHistory> getTodayVoteHistory() {
        return voteDao.getTodayVoteHistory();
    }

    @Override
    public List<VoteHistory> getRestaurantVoteHistory(int restaurantId) {
        return voteDao.getRestaurantVoteHistory(restaurantId);
    }

    @Override
    public List<VoteHistory> getUserVoteHistory(int userId) {
        return voteDao.getUserVoteHistory(userId);
    }

    @Override
    public List<User> getRestaurantVotersToday(int restaurantId) {
        return voteDao.getRestaurantVotersToday(restaurantId);
    }

    @Override
    public Restaurant getVoterRestaurantToday(int userId) {

        List<VoteHistory> voteHistoryList =  getUserVoteHistory(userId);

        Restaurant restaurant = null;
        for(VoteHistory voteHistory : voteHistoryList){
            if (voteHistory.getDate() == LocalDate.now()) {
                restaurant = voteHistory.getRestaurant();
                break;
            }
        }
        return restaurant;
    }
}
