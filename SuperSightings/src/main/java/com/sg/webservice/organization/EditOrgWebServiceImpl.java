package com.sg.webservice.organization;

import com.sg.commandmodel.organization.editorg.EditOrgCmdModel;
import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.service.LocationService;
import com.sg.service.OrganizationService;
import com.sg.viewmodel.organization.editorg.EditOrgViewModel;
import com.sg.viewmodel.organization.editorg.LocationViewModel;
import com.sg.webservices.organization.EditOrgWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class EditOrgWebServiceImpl implements EditOrgWebService {

    @Inject
    OrganizationService organizationService;

    @Inject
    LocationService locationService;

    private List<LocationViewModel> translate(List<Location> locations){
        List<LocationViewModel> locationViewModels = new ArrayList<>();

        for(Location location: locations){
            LocationViewModel locationViewModel = new LocationViewModel();
            locationViewModel.setId(location.getId());
            locationViewModel.setName(location.getName());
            locationViewModels.add(locationViewModel);
        }

        return locationViewModels;
    }

    @Override
    public EditOrgViewModel getEditOrgViewModel(Long id) {

        // instantiate
        EditOrgViewModel editOrgViewModel = new EditOrgViewModel();
        EditOrgCmdModel editOrgCmdModel = new EditOrgCmdModel();
        List<Location> locations = locationService.retrieveAllLocations(Integer.MAX_VALUE, Integer.valueOf(0));

        // lookup stuff
        Organization emptyOrg = new Organization();
        emptyOrg.setId(id);
        Organization org = organizationService.read(emptyOrg);

        // put stuff
        editOrgCmdModel.setLocationId(org.getLocation().getId());
        editOrgCmdModel.setId(org.getId());
        editOrgCmdModel.setName(org.getName());
        editOrgCmdModel.setDescription(org.getDescription());

        editOrgViewModel.setId(org.getId());
        editOrgViewModel.setEditCommandModel(editOrgCmdModel);
        editOrgViewModel.setLocations(translate(locations));

        return editOrgViewModel;
    }

    @Override
    public Organization saveEditOrgCmdModel(EditOrgCmdModel editOrgCmdModel) {

        // instantiate
        Organization org = new Organization();

        // lookup stuff
        Location locationToLookup = new Location();
        locationToLookup.setId(editOrgCmdModel.getLocationId());
        Location location = locationService.read(locationToLookup);

        // put stuff
        org.setId(editOrgCmdModel.getId());
        org.setName(editOrgCmdModel.getName());
        org.setLocation(location);
        org.setDescription(editOrgCmdModel.getDescription());

        // save stuff
        organizationService.update(org);

        return org;
    }
}
