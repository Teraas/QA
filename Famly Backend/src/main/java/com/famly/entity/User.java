package com.famly.entity;


import javax.persistence.*;

// Annotations
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    //@ManyToOne
    //@JoinColumn(name="locationId")
    public long locationId;

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
}
