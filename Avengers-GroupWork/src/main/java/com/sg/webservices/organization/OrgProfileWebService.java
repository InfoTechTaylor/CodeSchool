package com.sg.webservices.organization;

import com.sg.viewmodel.organization.orgprofile.OrgProfileViewModel;

public interface OrgProfileWebService {

    public OrgProfileViewModel getOrgProfileViewModel(Long id);
}
