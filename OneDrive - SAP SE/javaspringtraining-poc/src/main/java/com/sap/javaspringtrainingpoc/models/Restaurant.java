package com.sap.javaspringtrainingpoc.models;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "###.##")
    @Column(name = "AVERAGEPRICE")
    private double averagePrice;


    @Column(name = "LOCATION")
    private String location;

    @Column(name = "ALELOACCEPTED")
    private boolean aleloAccepted;

    @Column(name = "IMAGE")
    private String image;



    @OneToMany(
            mappedBy = "restaurant",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<VoteHistory> voteHistories;


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

    public List<VoteHistory> getVoteHistories() {
        return voteHistories;
    }

    public void setVoteHistories(List<VoteHistory> voteHistories) {
        this.voteHistories = voteHistories;
    }
}
