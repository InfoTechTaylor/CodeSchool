package com.sg.webservice.sighting;

import com.sg.commandmodel.sighting.createsighting.CreateSightingCmdModel;
import com.sg.dto.Location;
import com.sg.dto.Person;
import com.sg.dto.Sighting;
import com.sg.service.PersonService;
import com.sg.service.SightingService;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.sighting.createlistsighting.CreateListSightingViewModel;
import com.sg.webservices.sighting.CreateListSightingWebService;
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
public class CreateListSightingWebServiceImplTest {

    @Inject
    CreateListSightingWebService createListSightingWebService;

    @Inject
    TestHelperMethods testHelperMethods;

    @Inject
    SightingService sightingService;

    @Test
    public void getCreateListSightingViewModel() {

        // arrange
        List<Location> locations = testHelperMethods.createTestLocations(15);
        List<Person> persons = testHelperMethods.createTestPersons(15);
        List<Sighting> sightings = testHelperMethods.createTestSightings(15, locations.get(0));

        // act
        CreateListSightingViewModel createListSightingViewModel =
                createListSightingWebService.getCreateListSightingViewModel(5, 0, 5);

        // assert
        assert createListSightingViewModel.getSightings().size() == 5; // assert we get 5 powers
        assert createListSightingViewModel.getPageNumber() == 1; // assert we are on the right page
        assert createListSightingViewModel.getPageNumbers().size() == 5; // verify we have the right number of page nums
        assert createListSightingViewModel.getPageNumbers().get(0) == 1; // verify start of page nums is correct
        assert createListSightingViewModel.getPageNumbers().get(4) == 5; // verify end of page nums is correct

        assert createListSightingViewModel.getCommandModel() != null;
        assert createListSightingViewModel.getPersons().size() == 15;
        assert createListSightingViewModel.getLocations().size() == 15;
    }

    @Test
    public void saveCreateSightingCmdModel() {

        // arrange
        Location location = testHelperMethods.createTestLocations(1).get(0);
        List<Person> persons = testHelperMethods.createTestPersons(2);

        CreateSightingCmdModel createSightingCmdModel = new CreateSightingCmdModel();
        createSightingCmdModel.setLocationId(location.getId());
        createSightingCmdModel.setDate(LocalDate.parse("2018-04-10"));
        createSightingCmdModel.setDescription("Batman vs Superman");

        Long[] personIds = new Long[persons.size()];
        int counter = 0;
        for(Person person : persons){
            personIds[counter] = person.getId();
            counter++;
        }
        createSightingCmdModel.setPersonIds(personIds);

        // act
        Sighting sighting=
                createListSightingWebService.saveCreateSightingCmdModel(createSightingCmdModel);

        // assert
        Sighting sightingFromDB = sightingService.read(sighting);
        assert sightingFromDB.getId() != null;
        assert sightingFromDB.getLocation().getId().equals(location.getId());
        assert sightingFromDB.getDescription().equals("Batman vs Superman");
        assert sightingFromDB.getSightingDate().equals(LocalDate.parse("2018-04-10"));
    }
}