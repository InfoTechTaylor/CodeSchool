package com.sg.webservices.organization;

import com.sg.commandmodel.organization.createorg.CreateOrgCmdModel;
import com.sg.dto.Organization;
import com.sg.viewmodel.organization.createlistorg.CreateListOrgViewModel;
import com.sg.webservices.exception.RelationshipFoundException;

public interface CreateListOrgWebService {


    public CreateListOrgViewModel getCreateListOrgViewModel(Integer limit, Integer offset, Integer pageNumbers);

    public Organization saveCreateOrgCmdModel(CreateOrgCmdModel commandModel);

    public void deleteOrgCmdModel(Long id) throws RelationshipFoundException;

}
