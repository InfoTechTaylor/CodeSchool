package com.sg.viewmodel.sighting.editsighting;

import com.sg.commandmodel.sighting.editsighting.EditSightingCmdModel;


import java.util.List;

public class EditSightingViewModel {

    private Long id;
    private List<LocationViewModel> locations;
    private List<PersonViewModel> persons;



    private EditSightingCmdModel editCommandModel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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



    public EditSightingCmdModel getEditCommandModel() {
        return editCommandModel;
    }

    public void setEditCommandModel(EditSightingCmdModel editCommandModel) {
        this.editCommandModel = editCommandModel;
    }
}
