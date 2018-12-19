package com.sg.webservices.power;

import com.sg.commandmodel.power.createpower.CreatePowerCmdModel;
import com.sg.dto.Power;
import com.sg.viewmodel.power.createlistpower.CreateListPowerViewModel;
import com.sg.webservices.exception.RelationshipFoundException;

public interface CreateListPowerWebService {


    public CreateListPowerViewModel getCreateListPowerViewModel(Integer limit, Integer offset, Integer pageNumbers);

    public Power saveCreatePowerCmdModel(CreatePowerCmdModel commandModel);

    public void deletePowerCmdModel(Long id) throws RelationshipFoundException;
}
