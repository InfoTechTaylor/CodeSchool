package com.sg.viewmodel.location.createlistlocation;

import com.sg.commandmodel.location.createlocation.CreateLocationCmdModel;

import java.util.List;

public class CreateListLocationViewModel {

    private List<Integer> pageNumbers;
    private Integer pageNumber;
    private List<LocationViewModel> locations;

    private CreateLocationCmdModel commandModel;

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

    public List<LocationViewModel> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationViewModel> locations) {
        this.locations = locations;
    }

    public CreateLocationCmdModel getCommandModel() {
        return commandModel;
    }

    public void setCommandModel(CreateLocationCmdModel commandModel) {
        this.commandModel = commandModel;
    }
}
