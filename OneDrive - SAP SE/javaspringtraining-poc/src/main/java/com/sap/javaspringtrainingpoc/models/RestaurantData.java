package com.sap.javaspringtrainingpoc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by I863409 on 14/08/2017.
 */
public class RestaurantData extends Restaurant{

    private int numberOfVotes;
    
    private List<User> voters;


    //Constructors
    public RestaurantData() {}

    public RestaurantData(Restaurant restaurant){
        this.setId(restaurant.getId());
        this.setName(restaurant.getName());
        this.setAveragePrice(restaurant.getAveragePrice());
        this.setLocation(restaurant.getLocation());
        this.setAleloAccepted(restaurant.isAleloAccepted());
        this.setImage(restaurant.getImage());

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
