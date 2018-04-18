package com.sg.webservice.location;

import com.sg.commandmodel.location.createlocation.CreateLocationCmdModel;
import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.dto.Sighting;
import com.sg.service.LocationService;
import com.sg.service.OrganizationService;
import com.sg.service.SightingService;
import com.sg.util.PagingUtil;
import com.sg.viewmodel.location.createlistlocation.CreateListLocationViewModel;
import com.sg.viewmodel.location.createlistlocation.LocationViewModel;
import com.sg.webservices.exception.RelationshipFoundException;
import com.sg.webservices.location.CreateListLocationWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CreateListLocationWebServiceImpl implements CreateListLocationWebService {


    @Inject
    LocationService locationService;

    @Inject
    OrganizationService organizationService;

    @Inject
    SightingService sightingService;

    private List<LocationViewModel> translate(List<Location> locations){

        List<LocationViewModel> locationViewModels = new ArrayList<>();

        for (Location location: locations){
            LocationViewModel locationViewModel = translate(location);
            locationViewModels.add(locationViewModel);
        }

        return locationViewModels;
    }

    private LocationViewModel translate(Location location){
        LocationViewModel locationViewModel = new LocationViewModel();
        locationViewModel.setId(location.getId());
        locationViewModel.setName(location.getName());
        locationViewModel.setCity(location.getCity());

        return locationViewModel;
    }

    @Override
    public CreateListLocationViewModel getCreateListLocationViewModel(Integer limit, Integer offset, Integer numPagesToShow) {

        if(limit == null) limit = 5;
        if(offset == null) offset = 0;
        if(numPagesToShow == null) numPagesToShow = 5;

        // instantiate
        CreateListLocationViewModel createListLocationViewModel = new CreateListLocationViewModel();
        CreateLocationCmdModel createLocationCmdModel = new CreateLocationCmdModel();

        // lookup stuff
        List<Location> locations = locationService.retrieveAllLocations(limit, offset);

        // calculate page info
        Integer currentPage = PagingUtil.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtil.getPageNumbers(currentPage, numPagesToShow);

        // put stuff
        createListLocationViewModel.setPageNumber(currentPage);
        createListLocationViewModel.setPageNumbers(pages);
        createListLocationViewModel.setLocations(translate(locations));
        createListLocationViewModel.setCommandModel(createLocationCmdModel);

        return createListLocationViewModel;
    }

    @Override
    public Location saveCreateLocationCmdModel(CreateLocationCmdModel createLocationCmdModel) {

        // instantiate
        Location location = new Location();

        // put stuff
        location.setLatitude(createLocationCmdModel.getLatitude());
        location.setLongitude(createLocationCmdModel.getLongitude());
        location.setName(createLocationCmdModel.getName());
        location.setDescription(createLocationCmdModel.getDescription());
        location.setStreet(createLocationCmdModel.getStreet());
        location.setCity(createLocationCmdModel.getCity());
        location.setState(createLocationCmdModel.getState());
        location.setZip(createLocationCmdModel.getZip());
        location.setCountry(createLocationCmdModel.getCountry());

        // save stuff
        Location createdLocation = locationService.create(location);

        return createdLocation;
    }

    @Override
    public void deleteLocationCmdModel(Long id) throws RelationshipFoundException {

        // instantiate
        Location location = new Location();
        location.setId(id);

        // look up stuff
        List<Organization> allOrgs =
                organizationService.retrieveAllOrganizations(Integer.MAX_VALUE, Integer.valueOf(0));
        List<Sighting> allSightings =
                sightingService.retrieveAllSightingsByLocation(location, Integer.MAX_VALUE, Integer.valueOf(0));


        // check for relationship between location and org
        int orgCounter = 0;
        boolean hasOrgs = false;
        for (Organization org: allOrgs){
            if(org.getLocation().getId().equals(location.getId())){
                hasOrgs = true;
                orgCounter++;
            }

        }

        if(!hasOrgs && allSightings.size() == 0) {
            locationService.delete(location);
        } else{
            throw new RelationshipFoundException(orgCounter + " orgs found with this location and " +
                    allSightings.size() + " sightings found with this location. Deletion aborted.");
        }
    }
}
