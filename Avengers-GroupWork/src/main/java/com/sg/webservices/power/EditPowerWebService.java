package com.sg.webservices.power;

import com.sg.commandmodel.power.editpower.EditPowerCmdModel;
import com.sg.dto.Power;
import com.sg.viewmodel.power.editpower.EditPowerViewModel;

public interface EditPowerWebService {

    public EditPowerViewModel getEditPowerViewModel(Long id);

    public Power saveEditPowerCmdModel(EditPowerCmdModel editCommandModel);
}
