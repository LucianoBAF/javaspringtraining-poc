package com.sap.javaspringtrainingpoc.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by I863409 on 24/07/2017.
 */
@Entity
@Table(name = "RESTAURANT")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    @NotEmpty
    private String name;

    @Column(name = "AVERAGEPRICE")
    private double averagePrice;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "ALELOACCEPTED")
    private boolean aleloAccepted;

    @Column(name = "IMAGE")
    private String image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAleloAccepted() {
        return aleloAccepted;
    }

    public void setAleloAccepted(boolean aleloAccepted) {
        this.aleloAccepted = aleloAccepted;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
