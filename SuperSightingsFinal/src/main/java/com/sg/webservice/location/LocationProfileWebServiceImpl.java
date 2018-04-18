package com.sg.webservice.location;

import com.sg.dto.Location;
import com.sg.service.LocationService;
import com.sg.viewmodel.location.locationprofile.LocationProfileViewModel;
import com.sg.webservices.location.LocationProfileWebService;

import javax.inject.Inject;

public class LocationProfileWebServiceImpl implements LocationProfileWebService {


    @Inject
    LocationService locationService;

    @Override
    public LocationProfileViewModel getLocationViewModel(Long id) {

        // instantiate
        LocationProfileViewModel locationProfileViewModel = new LocationProfileViewModel();

        // look stuff up
        Location lazyLocation = new Location();
        lazyLocation.setId(id);
        Location location = locationService.read(lazyLocation);

        // put stuff
        locationProfileViewModel.setId(location.getId());
        locationProfileViewModel.setLatitude(location.getLatitude());
        locationProfileViewModel.setLongitude(location.getLongitude());
        locationProfileViewModel.setName(location.getName());
        locationProfileViewModel.setDescription(location.getDescription());
        locationProfileViewModel.setStreet(location.getStreet());
        locationProfileViewModel.setCity(location.getCity());
        locationProfileViewModel.setState(location.getState());
        locationProfileViewModel.setZip(location.getZip());
        locationProfileViewModel.setCountry(location.getCountry());

        return locationProfileViewModel;
    }
}
