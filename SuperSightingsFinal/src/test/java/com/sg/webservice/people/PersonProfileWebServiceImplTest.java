package com.sg.webservice.people;

import com.sg.dto.*;
import com.sg.service.OrganizationService;
import com.sg.service.PersonOrganizationService;
import com.sg.service.PersonPowerService;
import com.sg.service.PowerService;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.people.personprofile.PersonProfileViewModel;
import com.sg.webservices.people.PersonProfileWebService;
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
public class PersonProfileWebServiceImplTest {

    @Inject
    TestHelperMethods testHelperMethods;

    @Inject
    PersonProfileWebService personProfileWebService;

    @Inject
    PersonPowerService personPowerService;

    @Inject
    PersonOrganizationService personOrganizationService;

    @Inject
    OrganizationService organizationService;

    @Inject
    PowerService powerService;

    @Test
    public void getPersonProfileViewModel() {

        // arrange
        Person person = testHelperMethods.createTestPersons(1).get(0);
        Location location = testHelperMethods.createTestLocations(1).get(0);
        List<Organization> orgs = testHelperMethods.createTestOrgs(2, location);
        List<Power> powers = testHelperMethods.createTestPowers(2);

        PersonPower personPower = new PersonPower();
        personPower.setPower(powers.get(0));
        personPower.setPerson(person);
        personPowerService.create(personPower);
        personPower.setPower(powers.get(1));
        personPower.setPerson(person);
        personPowerService.create(personPower);

        PersonOrganization personOrganization = new PersonOrganization();
        personOrganization.setOrganization(orgs.get(0));
        personOrganization.setPerson(person);
        personOrganizationService.create(personOrganization);
        personOrganization.setOrganization(orgs.get(1));
        personOrganization.setPerson(person);
        personOrganizationService.create(personOrganization);

        // act
        PersonProfileViewModel personProfileViewModel = personProfileWebService.getPersonProfileViewModel(person.getId());

        // assert
        assert personProfileViewModel.getId().equals(person.getId());
        assert personProfileViewModel.getName().equals(person.getName());
        assert personProfileViewModel.getDescription().equals(person.getDescription());
        assert personProfileViewModel.getType().equals(person.getType());

        List<Power> personsPowers = powerService.retrieveAllPowersByPerson(person, Integer.MAX_VALUE, 0);
        int counter = 0;
        boolean containsPower1 = false;
        boolean containsPower2 = false;
        for(Power power : personsPowers){
            assert power.getId() != null;
            if(power.getName().equals("Wealth" + counter)) containsPower1 = true;
            if(power.getName().equals("Wealth" + counter)) containsPower2 = true;
            counter++;
        }

        assert containsPower1;
        assert containsPower2;

        List<Organization> personsOrgs =
                organizationService.retrieveAllOrganizationsByPerson(person, Integer.MAX_VALUE, 0);
        int counter1 = 0;
        boolean containsOrg1 = false;
        boolean containsOrg2 = false;
        for(Organization org : personsOrgs){
            assert org.getId() != null;
            if(org.getName().equals("The Avengers")) containsOrg1 = true;
            if(org.getName().equals("The Avengers")) containsOrg2 = true;
            assert org.getLocation().getId().equals(location.getId());
//            assert org.getLocation().getName().equals(location.getName());
            assert org.getDescription().equals("Earth's No. 1 Team");
            counter1++;
        }

        assert containsOrg1;
        assert containsOrg2;


    }
}