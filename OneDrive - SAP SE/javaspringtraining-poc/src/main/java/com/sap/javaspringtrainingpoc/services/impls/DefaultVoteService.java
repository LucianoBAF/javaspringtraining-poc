package com.sap.javaspringtrainingpoc.services.impls;

import com.sap.javaspringtrainingpoc.daos.RestaurantDao;
import com.sap.javaspringtrainingpoc.daos.VoteDao;
import com.sap.javaspringtrainingpoc.models.*;
import com.sap.javaspringtrainingpoc.services.RestaurantService;
import com.sap.javaspringtrainingpoc.services.VoteService;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by I863409 on 08/08/2017.
 */
public class DefaultVoteService implements VoteService {
    @Resource
    private VoteDao voteDao;

    @Resource
    private RestaurantService restaurantService;

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

    //Group various vote entries by restaurant on each voting day
    @Override
    public List<VoteHistoryData> getCompleteVoteHistory() {
        List<Restaurant> restaurantList = restaurantService.listRestaurants();
        List<VoteHistory> voteHistoryList = voteDao.getCompleteVoteHistory();
        List<VoteHistoryData> voteHistoryListFinal = new ArrayList<>();

        List<LocalDate> datesChecked = new ArrayList();

        //Check for a initial record date
        for(VoteHistory voteRecord : voteHistoryList){
            LocalDate currentDate = voteRecord.getDate();
            if(! datesChecked.contains(currentDate))
            {
                datesChecked.add(currentDate);

                //Get all votes that occurred on that day
                List<VoteHistory> dateLocalHistory = getVoteHistoryByDate(currentDate);
                List<Restaurant> restaurantsChecked = new ArrayList();
                for(VoteHistory vote : dateLocalHistory) {
                    //First occurrence of restaurant in this day (currentDate)
                    if (!restaurantsChecked.contains(vote.getRestaurant())) {
                        restaurantsChecked.add(vote.getRestaurant());

                        VoteHistoryData voteHistoryData = new VoteHistoryData(vote);
                        voteHistoryData.setNumberOfVotes(1);
                        voteHistoryData.getVoters().add(vote.getUser());

                        voteHistoryListFinal.add(voteHistoryData);
                    }
                    else {
                        VoteHistoryData voteHistoryData = new VoteHistoryData();
                        // Fetch the right restaurant (which already have a vote)
                        // in order to register the vote and voters
                        for(int i=voteHistoryListFinal.size() - 1; i>=0; i--){
                            if(voteHistoryListFinal.get(i).getRestaurant().getName().equals(vote.getRestaurant().getName())){
                                voteHistoryData = voteHistoryListFinal.get(i);
                                break;
                            }
                        }

                        voteHistoryData.setNumberOfVotes(voteHistoryData.getNumberOfVotes() + 1);
                        voteHistoryData.getVoters().add(vote.getUser());
                    }
                }

            }
        }

        voteHistoryListFinal.sort(Comparator.comparing(VoteHistoryData::getDate).reversed().thenComparing(VoteHistoryData::getNumberOfVotes).reversed());
        return voteHistoryListFinal;
    }

    @Override
    public List<VoteHistory> getTodayVoteHistory() {
        return voteDao.getTodayVoteHistory();
    }

    @Override
    public List<VoteHistory> getVoteHistoryByDate(LocalDate date) {
        return voteDao.getVoteHistoryByDate(date);
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

    @Override
    public List<RestaurantData> getTodayRestaurantsDataByVotes(List<Restaurant> restaurants,List<VoteHistory> voteHistoryToday) {
        //Object that contains all restaurant parameters plus voters count and voters list
        List<RestaurantData> restaurantsDataList = new ArrayList<>();

        //Initialize both maps with no vote count or voters name
        for (Restaurant restaurant : restaurants) {
            restaurantsDataList.add(new RestaurantData(restaurant));
        }

        for (VoteHistory vote : voteHistoryToday) {
            Restaurant actualRestaurant = vote.getRestaurant();
            int restaurantId = actualRestaurant.getId();
            int indexActualRestaurant = getRestaurantIndexById(restaurantsDataList,restaurantId);

            RestaurantData restaurantData = restaurantsDataList.get(indexActualRestaurant);
            restaurantData.setNumberOfVotes(restaurantData.getNumberOfVotes()+1);
            restaurantData.getVoters().add(vote.getUser());
        }

        //Organizes restaurant list by vote count
        RestaurantData tempRestaurant;
        for(int j = 0;j < restaurantsDataList.size()-1; j++) {
            for (int i = 0;i < restaurantsDataList.size()-1; i++) {
                if(restaurantsDataList.get(i).getNumberOfVotes() < restaurantsDataList.get(i+1).getNumberOfVotes()){
                    tempRestaurant = restaurantsDataList.get(i);
                    restaurantsDataList.set(i,restaurantsDataList.get(i+1));
                    restaurantsDataList.set(i+1,tempRestaurant);
                }
            }
        }

        return restaurantsDataList;
    }

    private int getRestaurantIndexById(List<RestaurantData> restaurantDataList, int restaurantId){
        for (int i = 0; i < restaurantDataList.size(); i++) {
            if (restaurantDataList.get(i) !=null && restaurantDataList.get(i).getId() == restaurantId) {
                return i;
            }
        }
        return -1;//not found
    }

    //Return the id of the restaurant voted by the user with email "loggedUserEmail"
    //If 0 it means an invalid restaurant id, thus no vote
    @Override
    public int getTodayRestaurantIdVotedByUserEmail(List<VoteHistory> voteHistoryToday, String loggedUserEmail) {
        int restaurantUserVotedToday = 0;

        for (VoteHistory vote : voteHistoryToday) {
            Restaurant actualRestaurant = vote.getRestaurant();
            int restaurantId = actualRestaurant.getId();

            if(vote.getUser().getEmail().equals(loggedUserEmail)){
                restaurantUserVotedToday = restaurantId;
            }
        }
        return restaurantUserVotedToday;
    }


}
