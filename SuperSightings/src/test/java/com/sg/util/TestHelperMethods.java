package com.sg.util;

import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.dto.Power;
import com.sg.service.LocationService;
import com.sg.service.OrganizationService;
import com.sg.service.PowerService;
import com.sg.viewmodel.organization.editorg.LocationViewModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TestHelperMethods {

    private LocationService locationService;
    private PowerService powerService;
    private OrganizationService organizationService;

    @Inject
    public TestHelperMethods(LocationService locationService, PowerService powerService,
                             OrganizationService organizationService) {
        this.locationService = locationService;
        this.powerService = powerService;
        this.organizationService = organizationService;
    }

    public List<Location> createTestLocations(int numLocations){

        // create list to add locations to
        List<Location> locations = new ArrayList<>();

        for(int i=0; i < numLocations; i++){
            Location location = new Location();
            location.setLatitude(40.779287);
            location.setLongitude(-73.969326);
            location.setName("Central Park" + i);
            location.setDescription("near Belvedere Castle");
            location.setStreet("79th Street");
            location.setCity("New York");
            location.setState("NY");
            location.setZip("10021");
            location.setCountry("USA");
            Location createdLocation = locationService.create(location);

            locations.add(createdLocation);
        }

        return locations;
    }

    public List<Power> createTestPowers(int numPowers){
        List<Power> powers = new ArrayList<>();

        for(int i=0; i < numPowers; i++) {
            Power power = new Power();
            power.setName("Wealth" + i);
            Power createdPower = powerService.create(power);
            powers.add(createdPower);
        }

        return powers;
    }


    public List<Organization> createTestOrgs(int numOrgs, Location location){

        List<Organization> orgs = new ArrayList<>();

        for(int i=0; i < numOrgs; i++){
            Organization org = new Organization();
            org.setLocation(location);
            org.setName("The Avengers");
            org.setDescription("Earth's No. 1 Team");
            Organization createdOrg = organizationService.create(org);
            orgs.add(createdOrg);
        }

        return orgs;
    }

    public List<LocationViewModel> translateLocationList(List<Location> locations){
        List<LocationViewModel> locationViewModels = new ArrayList<>();

        for (Location currentLocation : locations){
            LocationViewModel locationViewModel = new LocationViewModel();
            locationViewModel.setId(currentLocation.getId());
            locationViewModel.setName(currentLocation.getName());
            locationViewModels.add(locationViewModel);
        }
        return locationViewModels;
    }

}
