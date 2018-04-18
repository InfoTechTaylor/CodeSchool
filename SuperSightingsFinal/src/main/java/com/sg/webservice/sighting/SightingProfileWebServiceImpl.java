package com.sg.webservice.sighting;

import com.sg.dto.Location;
import com.sg.dto.Person;
import com.sg.dto.Sighting;
import com.sg.service.LocationService;
import com.sg.service.PersonService;
import com.sg.service.SightingService;
import com.sg.viewmodel.sighting.sightingprofile.PersonViewModel;
import com.sg.viewmodel.sighting.sightingprofile.SightingProfileViewModel;
import com.sg.webservices.sighting.SightingProfileWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SightingProfileWebServiceImpl implements SightingProfileWebService {

    @Inject
    SightingService sightingService;

    @Inject
    LocationService locationService;

    @Inject
    PersonService personService;

    @Override
    public SightingProfileViewModel getSightingProfileViewModel(Long id) {
        // instantiate
        SightingProfileViewModel sightingProfileViewModel = new SightingProfileViewModel();

        // look up stuff
        Sighting sighting = new Sighting();
        sighting.setId(id);
        Sighting sightingRead = sightingService.read(sighting);

        Location location = new Location();
        location.setId(sightingRead.getLocation().getId());
        Location locationRead = locationService.read(location);
        sightingRead.setLocation(locationRead);

        List<Person> allPersonsAtSighting
                = personService.retrieveAllPersonsBySighting(sighting, Integer.MAX_VALUE, Integer.valueOf(0));

        // put stuff
        sightingProfileViewModel.setDate(sightingRead.getSightingDate());
        sightingProfileViewModel.setId(sightingRead.getId());
        sightingProfileViewModel.setLocationId(sightingRead.getLocation().getId());
        sightingProfileViewModel.setLocationName(sightingRead.getLocation().getName());
        sightingProfileViewModel.setPersons(translate(allPersonsAtSighting));
        sightingProfileViewModel.setDescription(sightingRead.getDescription());

        return sightingProfileViewModel;
    }


    private List<PersonViewModel> translate(List<Person> allPersons){
        List<PersonViewModel> allViews = new ArrayList<>();
        for(Person person : allPersons){
            PersonViewModel viewModel = new PersonViewModel();
            viewModel.setId(person.getId());
            viewModel.setName(person.getName());
            allViews.add(viewModel);
        }
        return allViews;
    }
}
