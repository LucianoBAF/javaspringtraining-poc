package com.sap.javaspringtrainingpoc.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I863409 on 16/08/2017.
 */
public class VoteHistoryData extends VoteHistory{
    private int numberOfVotes;

    private List<User> voters;


    //Constructors
    public VoteHistoryData(){}

    public VoteHistoryData(VoteHistory voteHistory){
        this.setId(voteHistory.getId());
        this.setDate(voteHistory.getDate());
        this.setRestaurant(voteHistory.getRestaurant());
        this.setUser(voteHistory.getUser());

        this.setNumberOfVotes(0);
        this.setVoters(new ArrayList<>());
    }



    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public List<User> getVoters() {
        return voters;
    }

    public void setVoters(List<User> voters) {
        this.voters = voters;
    }
}
