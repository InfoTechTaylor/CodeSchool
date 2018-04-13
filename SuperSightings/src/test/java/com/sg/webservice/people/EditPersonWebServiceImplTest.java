package com.sg.webservice.people;

import com.sg.commandmodel.people.editperson.EditPersonCmdModel;
import com.sg.dto.*;
import com.sg.service.*;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.people.editperson.EditPersonViewModel;
import com.sg.webservices.people.EditPersonWebService;
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
public class EditPersonWebServiceImplTest {

    @Inject
    TestHelperMethods testHelperMethods;

    @Inject
    EditPersonWebService editPersonWebService;

    @Inject
    PersonPowerService personPowerService;

    @Inject
    PersonOrganizationService personOrganizationService;

    @Inject
    PersonService personService;

    @Inject
    PowerService powerService;

    @Inject
    OrganizationService organizationService;

    @Test
    public void getEditPersonViewModel() {

        // arrange
        Person createdPerson = testHelperMethods.createTestPersons(1).get(0);
        Location location = testHelperMethods.createTestLocations(1).get(0);
        Organization createdOrg = testHelperMethods.createTestOrgs(1, location).get(0);
        Power createdPower = testHelperMethods.createTestPowers(1).get(0);

        PersonPower personPower = new PersonPower();
        personPower.setPower(createdPower);
        personPower.setPerson(createdPerson);
        personPowerService.create(personPower);

        PersonOrganization personOrganization = new PersonOrganization();
        personOrganization.setPerson(createdPerson);
        personOrganization.setOrganization(createdOrg);
        personOrganizationService.create(personOrganization);

        // act
        EditPersonViewModel editPersonViewModel =
                editPersonWebService.getEditPersonViewModel(createdPerson.getId());

        // assert
        assert editPersonViewModel.getOrganizations().get(0).getId().equals(createdOrg.getId());
        assert editPersonViewModel.getPowers().get(0).getId().equals(createdPower.getId());
        assert editPersonViewModel.getId() != null;

        EditPersonCmdModel editCommandModel = editPersonViewModel.getEditCommandModel();
        assert editCommandModel.getDescription().equals(createdPerson.getDescription());
        assert editCommandModel.getId().equals(createdPerson.getId());
        assert editCommandModel.getName().equals(createdPerson.getName());
        assert editCommandModel.getType().equals(createdPerson.getType());

        Long[] powerId = editCommandModel.getPowerIds();
        Long[] orgId = editCommandModel.getOrgIds();

        assert powerId[0].equals(createdPower.getId());
        assert orgId[0].equals(createdOrg.getId());
    }

    @Test
    public void saveEditPersonCmdModel() {

        // arrange
        Person createdPerson = testHelperMethods.createTestPersons(1).get(0);
        Location location = testHelperMethods.createTestLocations(1).get(0);
        List<Organization> organizations = testHelperMethods.createTestOrgs(2, location);
        Organization createdOrg = organizations.get(0);
        List<Power> powers = testHelperMethods.createTestPowers(2);
        Power createdPower = powers.get(0);

        PersonPower personPower = new PersonPower();
        personPower.setPower(createdPower);
        personPower.setPerson(createdPerson);
        personPowerService.create(personPower);

        PersonOrganization personOrganization = new PersonOrganization();
        personOrganization.setPerson(createdPerson);
        personOrganization.setOrganization(createdOrg);
        personOrganizationService.create(personOrganization);

        EditPersonCmdModel editPersonCmdModel = new EditPersonCmdModel();
        editPersonCmdModel.setId(createdPerson.getId());
        editPersonCmdModel.setName("Superman");
        editPersonCmdModel.setDescription("Clark Kent turned super hero");
        editPersonCmdModel.setType("hero");

        Long[] orgIds = new Long[1];
        orgIds[0] = organizations.get(1).getId();

        Long[] powerIds = new Long[1];
        powerIds[0] = powers.get(1).getId();

        // update stuff
        // create new person org relationship
        PersonOrganization personOrganization1 = new PersonOrganization();
        personOrganization1.setPerson(createdPerson);
        Organization org2 = organizations.get(1);
        personOrganization1.setOrganization(org2);
        personOrganizationService.create(personOrganization1);

        // create new person power relationship
        PersonPower personPower1 = new PersonPower();
        personPower1.setPerson(createdPerson);
        personPower1.setPower(powers.get(1));
        personPowerService.create(personPower1);

        editPersonCmdModel.setOrgIds(orgIds);
        editPersonCmdModel.setPowerIds(powerIds);

        // act
        Person editedPerson = editPersonWebService.saveEditPersonCmdModel(editPersonCmdModel);

        // assert
        Person personFromDB = personService.read(editedPerson);
        assert personFromDB.getId() != null;
        assert personFromDB.getName().equals("Superman");
        assert personFromDB.getDescription().equals("Clark Kent turned super hero");
        assert personFromDB.getType().equals("hero");

        List<Power> allPowersForPerson =
                powerService.retrieveAllPowersByPerson(personFromDB, Integer.MAX_VALUE, Integer.valueOf(0));

        for(Power power: allPowersForPerson){
            assert power != null;
            assert power.getName().equals(powers.get(1).getName());
        }

        List<Organization> allOrgsForPerson =
                organizationService.retrieveAllOrganizationsByPerson(personFromDB, Integer.MAX_VALUE, Integer.valueOf(0));

        for(Organization organization : allOrgsForPerson){
            assert organization.getId().equals(organizations.get(1).getId());
            assert organization.getName().equals(organizations.get(1).getName());
            assert organization.getDescription().equals(organizations.get(1).getDescription());
            assert organization.getLocation().getId().equals(organizations.get(1).getLocation().getId());
        }

    }
}