package com.sg.webservice.power;

import com.sg.commandmodel.power.editpower.EditPowerCmdModel;
import com.sg.dto.Power;
import com.sg.service.PowerService;
import com.sg.viewmodel.power.editpower.EditPowerViewModel;
import com.sg.webservices.power.EditPowerWebService;

import javax.inject.Inject;

public class EditPowerWebServiceImpl implements EditPowerWebService {

    @Inject
    PowerService powerService;

    private EditPowerCmdModel translate(Power power){
        EditPowerCmdModel editPowerCmdModel = new EditPowerCmdModel();
        editPowerCmdModel.setId(power.getId());
        editPowerCmdModel.setName(power.getName());
        return editPowerCmdModel;
    }

    @Override
    public EditPowerViewModel getEditPowerViewModel(Long id) {
        // instantiate
        EditPowerViewModel editPowerViewModel = new EditPowerViewModel();

        // lookup stuff
        Power power = new Power();
        power.setId(id);
        Power readPower = powerService.read(power);

        // put stuff
        editPowerViewModel.setId(power.getId());
        editPowerViewModel.setEditCommandModel(translate(readPower));

        return editPowerViewModel;
    }

    @Override
    public Power saveEditPowerCmdModel(EditPowerCmdModel editPowerCmdModel) {

        // instantiate
        Power power = new Power();

        // put stuff
        power.setId(editPowerCmdModel.getId());
        power.setName(editPowerCmdModel.getName());

        // save stuff
        powerService.update(power);
        return power;
    }
}
