package com.sg.viewmodel.people.editperson;

import com.sg.commandmodel.people.editperson.EditPersonCmdModel;

import java.util.List;

public class EditPersonViewModel {

    private Long id;
    private List<OrgViewModel> organizations;
    private List<PowerViewModel> powers;

    private EditPersonCmdModel editCommandModel;




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

    public EditPersonCmdModel getEditCommandModel() {
        return editCommandModel;
    }

    public void setEditCommandModel(EditPersonCmdModel editCommandModel) {
        this.editCommandModel = editCommandModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
