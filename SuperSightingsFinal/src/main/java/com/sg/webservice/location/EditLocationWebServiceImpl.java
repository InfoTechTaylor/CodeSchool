package com.sg.webservice.location;

import com.sg.commandmodel.location.editlocation.EditLocationCmdModel;
import com.sg.dto.Location;
import com.sg.service.LocationService;
import com.sg.viewmodel.location.editlocation.EditLocationViewModel;
import com.sg.webservices.location.EditLocationWebService;

import javax.inject.Inject;

public class EditLocationWebServiceImpl implements EditLocationWebService{

    @Inject
    LocationService locationService;

    private EditLocationCmdModel translate(Location location){
        EditLocationCmdModel editLocationCmdModel = new EditLocationCmdModel();

        editLocationCmdModel.setId(location.getId());
        editLocationCmdModel.setLatitude(location.getLatitude());
        editLocationCmdModel.setLongitude(location.getLongitude());
        editLocationCmdModel.setName(location.getName());
        editLocationCmdModel.setDescription(location.getDescription());
        editLocationCmdModel.setStreet(location.getStreet());
        editLocationCmdModel.setCity(location.getCity());
        editLocationCmdModel.setState(location.getState());
        editLocationCmdModel.setZip(location.getZip());
        editLocationCmdModel.setCountry(location.getCountry());

        return editLocationCmdModel;
    }

    @Override
    public EditLocationViewModel getEditLocationViewModel(Long id) {

        // instantiate
        EditLocationViewModel editLocationViewModel = new EditLocationViewModel();

        // lookup stuff
        Location location = new Location();
        location.setId(id);
        Location createdLocation = locationService.read(location);
        editLocationViewModel.setId(id);

        // put stuff
        editLocationViewModel.setEditCommandModel(translate(createdLocation));

        return editLocationViewModel;
    }

    @Override
    public Location saveEditLocationCmdModel(EditLocationCmdModel editLocationCmdModel) {

        // instantiate
        Location location = new Location();

        // put stuff
        location.setId(editLocationCmdModel.getId());
        location.setLatitude(editLocationCmdModel.getLatitude());
        location.setLongitude(editLocationCmdModel.getLongitude());
        location.setName(editLocationCmdModel.getName());
        location.setDescription(editLocationCmdModel.getDescription());
        location.setStreet(editLocationCmdModel.getStreet());
        location.setCity(editLocationCmdModel.getCity());
        location.setState(editLocationCmdModel.getState());
        location.setZip(editLocationCmdModel.getZip());
        location.setCountry(editLocationCmdModel.getCountry());

        // save stuff
        locationService.update(location);

        return location;
    }
}
