package com.sg.webservices.sighting;

import com.sg.commandmodel.sighting.createsighting.CreateSightingCmdModel;
import com.sg.dto.Sighting;
import com.sg.viewmodel.sighting.createlistsighting.CreateListSightingViewModel;
import com.sg.webservices.exception.RelationshipFoundException;

public interface CreateListSightingWebService {


    public CreateListSightingViewModel getCreateListSightingViewModel(Integer limit, Integer offset, Integer pageNumbers);

    public Sighting saveCreateSightingCmdModel(CreateSightingCmdModel commandModel);

    public void deleteSightingCmdModel(Long id) throws RelationshipFoundException;
}
