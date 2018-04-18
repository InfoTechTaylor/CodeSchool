package com.sg.webservice.people;

import com.sg.commandmodel.people.createperson.CreatePersonCmdModel;
import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.dto.Person;
import com.sg.dto.Power;
import com.sg.service.OrganizationService;
import com.sg.service.PowerService;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.people.createlistperson.CreateListPersonViewModel;
import com.sg.viewmodel.people.createlistperson.OrgViewModel;
import com.sg.viewmodel.people.createlistperson.PersonViewModel;
import com.sg.viewmodel.people.createlistperson.PowerViewModel;
import com.sg.webservices.people.CreateListPersonWebService;
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
public class CreateListPersonWebServiceImplTest {

    @Inject
    TestHelperMethods testHelperMethods;

    @Inject
    CreateListPersonWebService createListPersonWebService;

    @Inject
    OrganizationService organizationService;

    @Inject
    PowerService powerService;

    @Test
    public void getCreateListPersonViewModel() {

        // arrange
        List<Person> persons =  testHelperMethods.createTestPersons(15);
        Location location = testHelperMethods.createTestLocations(1).get(0);
        List<Organization> orgs = testHelperMethods.createTestOrgs(15, location);
        testHelperMethods.createTestPowers(15);

        // act
        CreateListPersonViewModel createListPersonViewModel =
                createListPersonWebService.getCreateListPersonViewModel(Integer.valueOf(5), 0, 5);

        // assert
        assert createListPersonViewModel.getPersons().size() == 5; // assert we get 5 locations
        assert createListPersonViewModel.getPageNumber() == 1; // assert we are on the right page
        assert createListPersonViewModel.getPageNumbers().size() == 5; // verify we have the right number of page nums
        assert createListPersonViewModel.getPageNumbers().get(0) == 1; // verify start of page nums is correct
        assert createListPersonViewModel.getPageNumbers().get(4) == 5; // verify end of page nums is correct

        assert createListPersonViewModel.getCommandModel() != null;
        assert createListPersonViewModel.getOrganizations().size() == 15;
        assert createListPersonViewModel.getPowers().size() == 15;

        List<OrgViewModel> orgViews = createListPersonViewModel.getOrganizations();
        int counter = 0;
        for (OrgViewModel org : orgViews){
            assert org.getId() != null;
            assert org.getName().equals("The Avengers");
            counter++;
        }

        List<PowerViewModel> powerViews = createListPersonViewModel.getPowers();
        int counter2 = 0;
        for (PowerViewModel powerView :powerViews){
            assert powerView.getId() != null;
            assert powerView.getName().equals("Wealth" + counter2);
            counter2++;
        }

        List<PersonViewModel> personViews = createListPersonViewModel.getPersons();
        int counter3 = 0;
        for(PersonViewModel personViewModel: personViews){
            assert personViewModel.getId() != null;
            assert personViewModel.getName().equals("Bruce Wayne" + counter3);
            assert personViewModel.getType().equals("Person");
            counter3++;
        }
    }

    @Test
    public void saveCreatePersonCmdModel() {

        // arrange
        Location location = testHelperMethods.createTestLocations(1).get(0);
        List<Organization> orgs = testHelperMethods.createTestOrgs(2, location);
        List<Power> powers = testHelperMethods.createTestPowers(2);


        CreatePersonCmdModel createPersonCmdModel = new CreatePersonCmdModel();
//        createPersonCmdModel.setStartDate(LocalDate.parse("2018-04-02"));
//        createPersonCmdModel.setEndDate(null);
        createPersonCmdModel.setName("Bruce Wayne");
        createPersonCmdModel.setDescription("Rich man dressed like bat");
        createPersonCmdModel.setType("person");

        Long[] orgIds = new Long[2];
        orgIds[0] = orgs.get(0).getId();
        orgIds[1] = orgs.get(1).getId();
        createPersonCmdModel.setOrgIds(orgIds);

        Long[] powerIds = new Long[2];
        powerIds[0] = powers.get(0).getId();
        powerIds[1] = powers.get(1).getId();
        createPersonCmdModel.setPowerIds(powerIds);


        // act
        Person savedPerson = createListPersonWebService.saveCreatePersonCmdModel(createPersonCmdModel);

        // assert
        assert savedPerson.getId() != null;
        assert savedPerson.getName().equals("Bruce Wayne");
        assert savedPerson.getDescription().equals("Rich man dressed like bat");
        assert savedPerson.getType().equals("person");

        List<Power> personsPowers = powerService.retrieveAllPowersByPerson(savedPerson, Integer.MAX_VALUE, 0);
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
                organizationService.retrieveAllOrganizationsByPerson(savedPerson, Integer.MAX_VALUE, 0);
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