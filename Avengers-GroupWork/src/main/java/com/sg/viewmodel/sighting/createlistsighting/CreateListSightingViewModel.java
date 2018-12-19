package com.sg.viewmodel.sighting.createlistsighting;

import com.sg.commandmodel.sighting.createsighting.CreateSightingCmdModel;

import java.util.List;

public class CreateListSightingViewModel {

    private List<LocationViewModel> locations;
    private List<PersonViewModel> persons;
    private List<SightingViewModel> sightings;

    //This is for pagination
    private List<Integer> pageNumbers;
    private Integer pageNumber;

    //This is for validations of create form.
    private CreateSightingCmdModel commandModel;


    public List<LocationViewModel> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationViewModel> locations) {
        this.locations = locations;
    }

    public List<PersonViewModel> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonViewModel> persons) {
        this.persons = persons;
    }

    public List<SightingViewModel> getSightings() {
        return sightings;
    }

    public void setSightings(List<SightingViewModel> sightings) {
        this.sightings = sightings;
    }

    public List<Integer> getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(List<Integer> pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public CreateSightingCmdModel getCommandModel() {
        return commandModel;
    }

    public void setCommandModel(CreateSightingCmdModel commandModel) {
        this.commandModel = commandModel;
    }
}
