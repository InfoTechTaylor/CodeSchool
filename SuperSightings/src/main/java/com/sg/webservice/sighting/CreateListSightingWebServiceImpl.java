package com.sg.webservice.sighting;

import com.sg.commandmodel.sighting.createsighting.CreateSightingCmdModel;
import com.sg.dto.Sighting;
import com.sg.viewmodel.sighting.createlistsighting.CreateListSightingViewModel;
import com.sg.webservices.sighting.CreateListSightingWebService;

public class CreateListSightingWebServiceImpl implements CreateListSightingWebService{
    @Override
    public CreateListSightingViewModel getCreateListSightingViewModel(int limit, int offset, int pageNumbers) {
        return null;
    }

    @Override
    public Sighting saveCreateSightingCmdModel(CreateSightingCmdModel createSightingCmdModel) {
        return null;
    }
}
