package com.famly.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author harish.kumar-mbp
 * createdOn 10/12/23
 */
@Entity

public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private LocalDateTime updateTime;

    private int age;
    private String gender;
    private String city;
    private String country;
    private String address;
    private int pinCode;
    private String profileLink;
    private String famly_tree_link;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getProfileLink() {
        return profileLink;
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

    public String getFamly_tree_link() {
        return famly_tree_link;
    }

    public void setFamly_tree_link(String famly_tree_link) {
        this.famly_tree_link = famly_tree_link;
    }

    @PreUpdate
    public void preUpdate() {
        this.updateTime = LocalDateTime.now();
    }

}
