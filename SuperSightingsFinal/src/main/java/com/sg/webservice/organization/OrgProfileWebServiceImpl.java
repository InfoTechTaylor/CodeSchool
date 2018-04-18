package com.sg.webservice.organization;

import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.service.LocationService;
import com.sg.service.OrganizationService;
import com.sg.viewmodel.organization.orgprofile.OrgProfileViewModel;
import com.sg.webservices.organization.OrgProfileWebService;

import javax.inject.Inject;

public class OrgProfileWebServiceImpl implements OrgProfileWebService {

    @Inject
    OrganizationService organizationService;

    @Inject
    LocationService locationService;

    @Override
    public OrgProfileViewModel getOrgProfileViewModel(Long id) {

        // instantiate
        OrgProfileViewModel orgProfileViewModel = new OrgProfileViewModel();

        // lookup stuff
        Organization organization = new Organization();
        organization.setId(id);
        Organization readOrg = organizationService.read(organization);

        Location locationToLookup = new Location();
        locationToLookup.setId(readOrg.getLocation().getId());
        Location location = locationService.read(locationToLookup);

        // put stuff
        orgProfileViewModel.setId(readOrg.getId());
        orgProfileViewModel.setName(readOrg.getName());
        orgProfileViewModel.setDescription(readOrg.getDescription());
        orgProfileViewModel.setLocationId(readOrg.getLocation().getId());
        orgProfileViewModel.setLocationName(location.getName());

        return orgProfileViewModel;
    }
}
