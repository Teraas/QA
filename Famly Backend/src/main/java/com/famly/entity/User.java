package com.famly.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

// Annotations
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    private String email;

    private String phone;

    private String password;

    private String firstName;

    private String middleName;

    private String lastName;

    private String status;

    private LocalDateTime createdTime;

    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name="locationId")
    //public Location location;
    public long locationId;

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "user", fetch = FetchType.EAGER)
    private UserDetail userDetails;

    @PreUpdate
    public void preUpdate() {
        this.createdTime = LocalDateTime.now();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public UserDetail getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetail userDetails) {
        this.userDetails = userDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getLocation() {
        return locationId;
    }

    public void setLocation(Long location) {
        this.locationId = location;
    }

    public User(){

    }
    public User(
            Long id, String firstName,
            String lastName, String email, String status, Long location)
    {

        super();

        this.id = id;

        this.firstName = firstName;

        this.lastName = lastName;

        this.email = email;
        this.status = status;


        this.locationId = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString()
    {

        return "User [id="
                + id + ", firstName="
                + firstName + ", lastName="
                + lastName + ", email="
                + email + "]";


    }

    public String getRole() {
        return "ADMIN";
    }
}
