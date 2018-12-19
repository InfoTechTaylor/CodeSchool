package com.sg.commandmodel.location.editlocation;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


public class EditLocationCmdModel {

    @NotNull(message = "Does not contain id")
    private Long id;

    private Double latitude;

    private Double longitude;

    @NotEmpty(message = "Field cannot be empty.")
    @Length(max = 50, message = "No more than 50 characters allowed")
    private String name;

    @NotEmpty(message = "Field cannot be empty.")
    @Length(max = 100, message = "No more than 100 characters allowed")
    private String description;

    @NotEmpty(message = "Field cannot be empty.")
    @Length(max = 100, message = "No more than 100 characters allowed")
    private String street;

    @NotEmpty(message = "Field cannot be empty.")
    @Length(max = 100, message = "No more than 100 characters allowed")
    private String city;

    @Length(max = 10, message = "No more than 10 characters allowed")
    private String state;
    @Length(max = 10, message = "No more than 10 characters allowed")
    private String zip;
    @Length(max = 25, message = "No more than 25 characters allowed")
    private String country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
