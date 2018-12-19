package com.sg.viewmodel.power.createlistpower;

import com.sg.commandmodel.power.createpower.CreatePowerCmdModel;

import java.util.List;

public class CreateListPowerViewModel {


    private List<PowerViewModel> powers;

    //This is for pagination
    private List<Integer> pageNumbers;
    private Integer pageNumber;

    //This is for validation
    private CreatePowerCmdModel commandModel;

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

    public CreatePowerCmdModel getCommandModel() {
        return commandModel;
    }

    public void setCommandModel(CreatePowerCmdModel commandModel) {
        this.commandModel = commandModel;
    }
}
