package com.sg.webservice.sighting;

import com.sg.commandmodel.sighting.editsighting.EditSightingCmdModel;
import com.sg.dto.Location;
import com.sg.dto.Person;
import com.sg.dto.PersonSighting;
import com.sg.dto.Sighting;
import com.sg.service.PersonService;
import com.sg.service.PersonSightingService;
import com.sg.service.SightingService;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.sighting.editsighting.EditSightingViewModel;
import com.sg.webservices.sighting.EditSightingWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Transactional
@Rollback
public class EditSightingWebServiceImplTest {

    @Inject
    TestHelperMethods testHelperMethods;

    @Inject
    EditSightingWebService editSightingWebService;

    @Inject
    PersonSightingService personSightingService;

    @Inject
    PersonService personService;

    @Test
    public void getEditSightingViewModel() {
        // arrange
        List<Location> allLocations = testHelperMethods.createTestLocations(15);
        Location location = allLocations.get(0);
        List<Person> allPersons = testHelperMethods.createTestPersons(15);
        Sighting sighting = testHelperMethods.createTestSightings(1, location).get(0);

        PersonSighting personSighting = new PersonSighting();
        personSighting.setPerson(allPersons.get(0));
        personSighting.setSighting(sighting);
        personSightingService.create(personSighting);
        personSighting.setPerson(allPersons.get(1));
        personSighting.setSighting(sighting);
        personSightingService.create(personSighting);

        // act
        EditSightingViewModel editSightingViewModel =
                editSightingWebService.getEditSightingViewModel(sighting.getId());

        // assert
        assert editSightingViewModel.getId().equals(sighting.getId());
        assert editSightingViewModel.getLocations().size() == 15;
        assert editSightingViewModel.getPersons().size() == 15;

        EditSightingCmdModel editCommandModel = editSightingViewModel.getEditCommandModel();
        assert editCommandModel.getId() != null;
        assert editCommandModel.getId().equals(sighting.getId());
        assert editCommandModel.getDescription().equals(sighting.getDescription());
        assert editCommandModel.getDate().equals(sighting.getSightingDate());
        assert editCommandModel.getLocationId().equals(sighting.getLocation().getId());

        Long[] personIds = new Long[2];
        personIds[0] = allPersons.get(0).getId();
        personIds[1] = allPersons.get(1).getId();

        boolean isFirstPerson = false;
        boolean isSecondPerson = false;
        for(int i=0; i<personIds.length; i++){
            if(personIds[i] == allPersons.get(0).getId()) isFirstPerson = true;
            if(personIds[i] == allPersons.get(1).getId()) isSecondPerson = true;
        }
        assert isFirstPerson;
        assert isSecondPerson;

    }

    @Test
    public void saveEditSightingCmdModel() {
        // arrange
        List<Location> allLocations = testHelperMethods.createTestLocations(2);
        Location location = allLocations.get(0);
        Sighting sighting = testHelperMethods.createTestSightings(1, location).get(0);
        List<Person> allPersons = testHelperMethods.createTestPersons(15);
        EditSightingCmdModel editSightingCmdModel = new EditSightingCmdModel();

        PersonSighting personSighting = new PersonSighting();
        personSighting.setPerson(allPersons.get(0));
        personSighting.setSighting(sighting);
        personSightingService.create(personSighting);
        personSighting.setPerson(allPersons.get(1));
        personSighting.setSighting(sighting);
        personSightingService.create(personSighting);

        Long[] personIds = new Long[2];
        personIds[0] = allPersons.get(2).getId();
        personIds[1] = allPersons.get(3).getId();

        editSightingCmdModel.setId(sighting.getId());
        editSightingCmdModel.setDate(LocalDate.parse("2018-04-10"));
        editSightingCmdModel.setDescription("super cool sighting");
        editSightingCmdModel.setLocationId(allLocations.get(1).getId());
        editSightingCmdModel.setPersonIds(personIds);

        // act
        Sighting sightingEdited = editSightingWebService.saveEditSightingCmdModel(editSightingCmdModel);

        // assert
        assert sightingEdited.getId() != null;
        assert sightingEdited.getSightingDate().equals(LocalDate.parse("2018-04-10"));
        assert sightingEdited.getDescription().equals("super cool sighting");
        assert sightingEdited.getLocation().getId().equals(allLocations.get(1).getId());

        List<Person> personsForSighting = personService.retrieveAllPersonsBySighting(sightingEdited, Integer.MAX_VALUE, Integer.valueOf(0));
        boolean isFirstPerson = false;
        boolean isSecondPerson = false;
        for(Person person : personsForSighting){
            if(person.getId().equals(allPersons.get(2).getId())) isFirstPerson = true;
            if(person.getId().equals(allPersons.get(3).getId())) isSecondPerson = true;
        }

        assert isFirstPerson;
        assert isSecondPerson;
    }
}