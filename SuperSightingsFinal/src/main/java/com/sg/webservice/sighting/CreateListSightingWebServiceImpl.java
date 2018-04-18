package com.sg.webservice.sighting;

import com.sg.commandmodel.sighting.createsighting.CreateSightingCmdModel;
import com.sg.dto.Location;
import com.sg.dto.Person;
import com.sg.dto.PersonSighting;
import com.sg.dto.Sighting;
import com.sg.service.LocationService;
import com.sg.service.PersonService;
import com.sg.service.PersonSightingService;
import com.sg.service.SightingService;
import com.sg.util.PagingUtil;
import com.sg.viewmodel.sighting.createlistsighting.CreateListSightingViewModel;
import com.sg.viewmodel.sighting.createlistsighting.LocationViewModel;
import com.sg.viewmodel.sighting.createlistsighting.PersonViewModel;
import com.sg.viewmodel.sighting.createlistsighting.SightingViewModel;
import com.sg.webservices.sighting.CreateListSightingWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CreateListSightingWebServiceImpl implements CreateListSightingWebService{

    @Inject
    LocationService locationService;

    @Inject
    PersonService personService;

    @Inject
    SightingService sightingService;

    @Inject
    PersonSightingService personSightingService;

    private List<SightingViewModel> translateSightings(List<Sighting> allSightings){
        List<SightingViewModel> allViews = new ArrayList<>();
        for(Sighting sighting: allSightings){
            Location location = new Location();
            location.setId(sighting.getLocation().getId());
            Location locationRead = locationService.read(location);

            sighting.setLocation(locationRead);

            SightingViewModel view = new SightingViewModel();
            view.setId(sighting.getId());
            view.setDate(sighting.getSightingDate());
            view.setLocationId(sighting.getLocation().getId());
            view.setLocationName(sighting.getLocation().getName());
            allViews.add(view);
        }
        return allViews;
    }

    private List<LocationViewModel> translateLocations(List<Location> allLocations){
        List<LocationViewModel> allViews = new ArrayList<>();
        for(Location location: allLocations){
            LocationViewModel view = new LocationViewModel();
            view.setId(location.getId());
            view.setName(location.getName());
            allViews.add(view);
        }
        return allViews;
    }

    private List<PersonViewModel> translatePersons(List<Person> allPersons){
        List<PersonViewModel> allViews = new ArrayList<>();
        for(Person person : allPersons){
            PersonViewModel view = new PersonViewModel();
            view.setId(person.getId());
            view.setName(person.getName());
            allViews.add(view);
        }
        return allViews;
    }

    @Override
    public CreateListSightingViewModel getCreateListSightingViewModel(Integer limit, Integer offset, Integer pageNumbers) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;
        if(pageNumbers == null) pageNumbers = 5;
        // instantiate
        CreateListSightingViewModel createListSightingViewModel = new CreateListSightingViewModel();
        CreateSightingCmdModel createSightingCmdModel = new CreateSightingCmdModel();

        // lookup stuff
        List<Location> allLocationsDropdown = locationService.retrieveAllLocations(Integer.MAX_VALUE, Integer.valueOf(0));
        List<Person> allPersonsDropdown = personService.retrieveAllPersons(Integer.MAX_VALUE, Integer.valueOf(0));
        List<Sighting> allSightingsTable = sightingService.retrieveAllSightings(limit, offset);

        // calculate page info
        Integer currentPage = PagingUtil.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtil.getPageNumbers(currentPage, pageNumbers);

        // put stuff
        createListSightingViewModel.setPageNumber(currentPage);
        createListSightingViewModel.setPageNumbers(pages);
        createListSightingViewModel.setLocations(translateLocations(allLocationsDropdown));
        createListSightingViewModel.setPersons(translatePersons(allPersonsDropdown));
        createListSightingViewModel.setSightings(translateSightings(allSightingsTable));
        createListSightingViewModel.setCommandModel(createSightingCmdModel);

        return createListSightingViewModel;
    }



    @Override
    public Sighting saveCreateSightingCmdModel(CreateSightingCmdModel createSightingCmdModel) {

        // instantiate
        Sighting sighting = new Sighting();

        // lookup stuff
        Location location = new Location();
        location.setId(createSightingCmdModel.getLocationId());
        Location locationRead = locationService.read(location);

        // put stuff
        sighting.setDescription(createSightingCmdModel.getDescription());
        sighting.setLocation(locationRead);
        sighting.setSightingDate(createSightingCmdModel.getDate());

        // save stuff
        Sighting createdSighting = sightingService.create(sighting);

        // create relationships for person sighting
        Long[] personIds = createSightingCmdModel.getPersonIds();
        for(int i = 0 ; i < personIds.length ; i++){
            Person personToLookup = new Person();
            personToLookup.setId(personIds[i]);
            personService.read(personToLookup);
            PersonSighting personSighting = new PersonSighting();
            personSighting.setSighting(createdSighting);
            personSighting.setPerson(personToLookup);
            personSightingService.create(personSighting);
        }
        return createdSighting;
    }

    @Override
    public void deleteSightingCmdModel(Long id) {
        //Instantiate
        Sighting sighting = new Sighting();
        sighting.setId(id);

        //Delete the relationship
        List<PersonSighting> personSightings = personSightingService.retrieveAllPersonSightingsBySighting(sighting, Integer.MAX_VALUE, Integer.valueOf(0));
        for(PersonSighting personSighting: personSightings){
            personSightingService.delete(personSighting);
        }

        //Delete
        sightingService.delete(sighting);
    }
}
