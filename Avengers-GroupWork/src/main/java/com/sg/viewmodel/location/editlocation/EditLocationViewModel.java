package com.sg.viewmodel.location.editlocation;

import com.sg.commandmodel.location.editlocation.EditLocationCmdModel;

public class EditLocationViewModel {

    private Long id;
    private EditLocationCmdModel editCommandModel;

    public EditLocationCmdModel getEditCommandModel() {
        return editCommandModel;
    }

    public void setEditCommandModel(EditLocationCmdModel editCommandModel) {
        this.editCommandModel = editCommandModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
