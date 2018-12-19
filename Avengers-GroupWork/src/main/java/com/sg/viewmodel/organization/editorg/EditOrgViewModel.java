package com.sg.viewmodel.organization.editorg;

import com.sg.commandmodel.organization.editorg.EditOrgCmdModel;

import java.util.List;

public class EditOrgViewModel {


    private Long id;
    private List<LocationViewModel> locations;

    //This is for validations and original values for organization
    private EditOrgCmdModel editCommandModel;

    public List<LocationViewModel> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationViewModel> locations) {
        this.locations = locations;
    }

    public EditOrgCmdModel getEditCommandModel() {
        return editCommandModel;
    }

    public void setEditCommandModel(EditOrgCmdModel editCommandModel) {
        this.editCommandModel = editCommandModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
