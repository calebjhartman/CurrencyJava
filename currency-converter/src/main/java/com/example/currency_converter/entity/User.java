package com.example.currency_converter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity 
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String favCurrencyCode;


    public User(Long id, String username, String favCurrencyCode) {
        this.id = id;
        this.username = username;
        this.favCurrencyCode = favCurrencyCode;
    }

    public User() {}

    public void setId(Long id) { this.id = id; }

    public Long getId() {return this.id; }

    public void setUsername(String username) { this.username = username; }

    public String getUsername() { return this.username; }

    public void setFavCurrencyCode(String favCurrencyCode) { this.favCurrencyCode = favCurrencyCode; }

    public String getFavCurrencyCode() { return this.favCurrencyCode; }
    

}


