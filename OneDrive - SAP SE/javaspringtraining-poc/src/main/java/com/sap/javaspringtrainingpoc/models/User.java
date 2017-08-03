package com.sap.javaspringtrainingpoc.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
/**
 * Created by I863409 on 02/08/2017.
 */
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @NotBlank(message = "Please insert a non blank value")
    @Column(name = "NAME")
    private String name;

    @NotBlank(message = "Please insert a non blank value")
    @Pattern(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Format must be email@emailprovider.com")
    @Column(name = "EMAIL")
    private String email;

    @Size(min = 6, message = "At least 6 caracters needed")
    @Pattern(regexp = "(?:\\d+[a-zA-Z]|[a-zA-Z]+\\d)[a-zA-Z\\d]*", message = "Must contain letters AND numbers")
    @Column(name = "PASSWORD")
    private String password;

    @Transient //only temporarily needed. Not saved in the database
    private String passwordConfirm;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DATEOFBIRTH")
    private String dateOfBirth;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
