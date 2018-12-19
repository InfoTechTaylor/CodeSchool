package com.sg.viewmodel.organization.createlistorg;

import com.sg.commandmodel.organization.createorg.CreateOrgCmdModel;

import java.util.List;

public class CreateListOrgViewModel {

    private List<OrgViewModel> organizations;

    //This is for location dropdown
    private List<LocationViewModel> locations;

    //This is for pagination
    private List<Integer> pageNumbers;
    private Integer pageNumber;

    //This is here for validations
    private CreateOrgCmdModel commandModel;



    public List<OrgViewModel> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<OrgViewModel> organizations) {
        this.organizations = organizations;
    }

    public List<LocationViewModel> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationViewModel> locations) {
        this.locations = locations;
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

    public CreateOrgCmdModel getCommandModel() {
        return commandModel;
    }

    public void setCommandModel(CreateOrgCmdModel commandModel) {
        this.commandModel = commandModel;
    }
}
