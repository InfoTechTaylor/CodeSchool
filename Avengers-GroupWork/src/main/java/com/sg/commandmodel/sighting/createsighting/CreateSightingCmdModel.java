package com.sg.commandmodel.sighting.createsighting;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CreateSightingCmdModel {


    @NotEmpty(message = "Please choose a person")
    private Long[] personIds;

    @NotNull(message = "Please choose a location")
    private Long locationId;

    @NotNull(message = "Please enter a date.")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @NotEmpty(message = "Field cannot be empty.")
    @Length(max = 255, message = "No more than 255 characters allowed")
    private String description;

    public Long[] getPersonIds() {
        return personIds;
    }

    public void setPersonIds(Long[] personIds) {
        this.personIds = personIds;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
