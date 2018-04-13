package com.sg.webservice.sighting;

import com.sg.commandmodel.sighting.editsighting.EditSightingCmdModel;
import com.sg.dto.Location;
import com.sg.dto.Person;
import com.sg.dto.PersonSighting;
import com.sg.dto.Sighting;
import com.sg.service.LocationService;
import com.sg.service.PersonService;
import com.sg.service.PersonSightingService;
import com.sg.service.SightingService;
import com.sg.viewmodel.sighting.editsighting.EditSightingViewModel;
import com.sg.viewmodel.sighting.editsighting.LocationViewModel;
import com.sg.viewmodel.sighting.editsighting.PersonViewModel;
import com.sg.webservices.sighting.EditSightingWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class EditSightingWebServiceImpl implements EditSightingWebService {

    @Inject
    SightingService sightingService;

    @Inject
    LocationService locationService;

    @Inject
    PersonService personService;

    @Inject
    PersonSightingService personSightingService;

    @Override
    public EditSightingViewModel getEditSightingViewModel(Long id) {
        // instantiate
        EditSightingViewModel editSightingViewModel = new EditSightingViewModel();

        // look up stuff
        Sighting sightingToLookup = new Sighting();
        sightingToLookup.setId(id);
        Sighting sightingRead = sightingService.read(sightingToLookup);

        List<Location> allLocationsDropdown = locationService.retrieveAllLocations(Integer.MAX_VALUE, Integer.valueOf(0));
        List<Person> allPersonsDropdown = personService.retrieveAllPersons(Integer.MAX_VALUE, Integer.valueOf(0));

        List<Person> allPersons =
                personService.retrieveAllPersonsBySighting(sightingRead, Integer.MAX_VALUE, 0);

        Long[] personIds = new Long[allPersons.size()];
        int counter = 0;
        for(Person person : allPersons){
            personIds[counter] = person.getId();
        }

        // put stuff
        editSightingViewModel.setId(id);
        editSightingViewModel.setLocations(translateLocations(allLocationsDropdown));
        editSightingViewModel.setPersons(translatePersons(allPersonsDropdown));

        EditSightingCmdModel editSightingCmdModel = translateSighting(sightingRead);
        editSightingCmdModel.setPersonIds(personIds);
        editSightingViewModel.setEditCommandModel(editSightingCmdModel);

        return editSightingViewModel;
    }

    private EditSightingCmdModel translateSighting(Sighting sighting){
        EditSightingCmdModel editSightingCmdModel = new EditSightingCmdModel();
        editSightingCmdModel.setId(sighting.getId());
        editSightingCmdModel.setDescription(sighting.getDescription());
        editSightingCmdModel.setDate(sighting.getSightingDate());
        editSightingCmdModel.setLocationId(sighting.getLocation().getId());
        return editSightingCmdModel;
    }

    private List<PersonViewModel> translatePersons(List<Person> allPersons){
        List<PersonViewModel> allViews = new ArrayList<>();
        for(Person person : allPersons){
            PersonViewModel viewModel = new PersonViewModel();
            viewModel.setId(person.getId());
            viewModel.setName(person.getName());
            allViews.add(viewModel);
        }
        return allViews;
    }

    private List<LocationViewModel> translateLocations(List<Location> allLocations){
        List<LocationViewModel> allViews = new ArrayList<>();
        for(Location location : allLocations){
            LocationViewModel viewModel = new LocationViewModel();
            viewModel.setId(location.getId());
            viewModel.setName(location.getName());
            allViews.add(viewModel);
        }
        return allViews;
    }

    @Override
    public Sighting saveEditSightingCmdModel(EditSightingCmdModel editSightingCmdModel) {

        // instantiate
        Sighting sighting = new Sighting();

        // look up stuff
        Location locationToLookup = new Location();
        locationToLookup.setId(editSightingCmdModel.getLocationId());
        Location locationRead = locationService.read(locationToLookup);

        // put stuff
        sighting.setId(editSightingCmdModel.getId());
        sighting.setSightingDate(editSightingCmdModel.getDate());
        sighting.setDescription(editSightingCmdModel.getDescription());
        sighting.setLocation(locationRead);

        // save stuff
        sightingService.update(sighting);

        // remove relationships
        List<PersonSighting> personSightings
                = personSightingService.retrieveAllPersonSightingsBySighting(sighting, Integer.MAX_VALUE, Integer.valueOf(0));
        for(PersonSighting personSighting :personSightings){
            personSightingService.delete(personSighting);
        }

        // add relationships
        Long[] personIds = editSightingCmdModel.getPersonIds();
        for(int i=0; i< personIds.length; i++){
            PersonSighting personSighting = new PersonSighting();
            personSighting.setSighting(sighting);
            Person person = new Person();
            person.setId(personIds[i]);
            personSighting.setPerson(personService.read(person));
            personSightingService.create(personSighting);
        }

        return sighting;
    }
}
