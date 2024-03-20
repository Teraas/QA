package com.famly.entity;

import javax.persistence.*;

@Entity
public class Location {
    public Long getLocationID() {
        return locationID;
    }

    public void setLocationID(Long locationID) {
        this.locationID = locationID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locationID;
    private String longitude;
    private String lattitude;

    @OneToOne(mappedBy = "location")
    User user;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public Location(){
    }

    public Location(String longitude, String lattitude) {
        this.longitude = longitude;
        this.lattitude = lattitude;
    }
}
