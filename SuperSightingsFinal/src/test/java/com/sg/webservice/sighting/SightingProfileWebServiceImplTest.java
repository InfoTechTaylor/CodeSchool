package com.sg.webservice.sighting;

import com.sg.dto.Location;
import com.sg.dto.Person;
import com.sg.dto.PersonSighting;
import com.sg.dto.Sighting;
import com.sg.service.PersonSightingService;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.sighting.sightingprofile.SightingProfileViewModel;
import com.sg.webservices.sighting.SightingProfileWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Transactional
@Rollback
public class SightingProfileWebServiceImplTest {

    @Inject
    TestHelperMethods testHelperMethods;

    @Inject
    SightingProfileWebService sightingProfileWebService;

    @Inject
    PersonSightingService personSightingService;

    @Test
    public void getSightingProfileViewModel() {

        // arrange
        Location location = testHelperMethods.createTestLocations(1).get(0);
        Sighting sighting = testHelperMethods.createTestSightings(1, location).get(0);
        List<Person> allPersons = testHelperMethods.createTestPersons(15);

        PersonSighting personSighting = new PersonSighting();
        personSighting.setPerson(allPersons.get(0));
        personSighting.setSighting(sighting);
        personSightingService.create(personSighting);
        personSighting.setPerson(allPersons.get(1));
        personSighting.setSighting(sighting);
        personSightingService.create(personSighting);

        // act
        SightingProfileViewModel sightingProfileViewModel =
                sightingProfileWebService.getSightingProfileViewModel(sighting.getId());

        // assert
        assert sightingProfileViewModel.getId().equals(sighting.getId());
        assert sightingProfileViewModel.getDate().equals(sighting.getSightingDate());
        assert sightingProfileViewModel.getLocationId().equals(sighting.getLocation().getId());
        assert sightingProfileViewModel.getLocationName().equals(sighting.getLocation().getName());

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
}