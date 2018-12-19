package com.sg.viewmodel.power.editpower;

import com.sg.commandmodel.power.editpower.EditPowerCmdModel;

public class EditPowerViewModel {


    private Long id;
    private EditPowerCmdModel editCommandModel;

    public EditPowerCmdModel getEditCommandModel() {
        return editCommandModel;
    }

    public void setEditCommandModel(EditPowerCmdModel editCommandModel) {
        this.editCommandModel = editCommandModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
