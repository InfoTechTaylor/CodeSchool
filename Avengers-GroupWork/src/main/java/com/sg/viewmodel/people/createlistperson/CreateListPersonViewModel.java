package com.sg.viewmodel.people.createlistperson;

import com.sg.commandmodel.people.createperson.CreatePersonCmdModel;

import java.util.List;

public class CreateListPersonViewModel {

    private List<PersonViewModel> persons;
    private List<OrgViewModel> organizations;
    private List<PowerViewModel> powers;

    //This is for pagination
    private List<Integer> pageNumbers;
    private Integer pageNumber;

    private CreatePersonCmdModel commandModel;


    public List<PersonViewModel> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonViewModel> persons) {
        this.persons = persons;
    }

    public List<OrgViewModel> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<OrgViewModel> organizations) {
        this.organizations = organizations;
    }

    public List<PowerViewModel> getPowers() {
        return powers;
    }

    public void setPowers(List<PowerViewModel> powers) {
        this.powers = powers;
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

    public CreatePersonCmdModel getCommandModel() {
        return commandModel;
    }

    public void setCommandModel(CreatePersonCmdModel commandModel) {
        this.commandModel = commandModel;
    }

}
