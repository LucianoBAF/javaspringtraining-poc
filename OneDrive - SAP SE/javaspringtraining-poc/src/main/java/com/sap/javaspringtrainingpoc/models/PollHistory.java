package com.sap.javaspringtrainingpoc.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

/**
 * Created by I863409 on 08/08/2017.
 */
@Entity
@Table(name="POLLHISTORY")
public class PollHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DATE")
    private String date;

    @Column(name = "RESTAURANT")
    private Restaurant restaurant;

    //Stores the users that voted in the restaurant above
    @Column(name = "USERS")
    private List<User> users;

    @Column(name = "USERSCOUNT")
    private int usersCount;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }
}
