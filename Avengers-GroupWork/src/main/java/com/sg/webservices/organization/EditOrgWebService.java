package com.sg.webservices.organization;

import com.sg.commandmodel.organization.editorg.EditOrgCmdModel;
import com.sg.dto.Organization;
import com.sg.viewmodel.organization.editorg.EditOrgViewModel;

public interface EditOrgWebService {

    public EditOrgViewModel getEditOrgViewModel(Long id);

    public Organization saveEditOrgCmdModel(EditOrgCmdModel editCommandModel);
}
