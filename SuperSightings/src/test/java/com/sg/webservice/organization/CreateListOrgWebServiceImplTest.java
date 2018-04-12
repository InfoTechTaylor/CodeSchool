package com.sg.webservice.organization;

import com.sg.commandmodel.organization.createorg.CreateOrgCmdModel;
import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.organization.createlistorg.CreateListOrgViewModel;
import com.sg.viewmodel.organization.createlistorg.OrgViewModel;
import com.sg.webservices.organization.CreateListOrgWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Transactional
@Rollback
public class CreateListOrgWebServiceImplTest {

    @Inject
    TestHelperMethods testHelperMethods;

    @Inject
    CreateListOrgWebService createListOrgWebService;

    @Test
    public void getCreateListOrgViewModel() {

        // arrange
        Location location = testHelperMethods.createTestLocations(1).get(0);
        List<Organization> orgs = testHelperMethods.createTestOrgs(15, location);

        // act
        CreateListOrgViewModel createListOrgViewModel =
                createListOrgWebService.getCreateListOrgViewModel(5, 0, 5);

        // assert
        assert createListOrgViewModel.getOrganizations().size() == 5; // assert we get 5 locations
        assert createListOrgViewModel.getPageNumber() == 1; // assert we are on the right page
        assert createListOrgViewModel.getPageNumbers().size() == 5; // verify we have the right number of page nums
        assert createListOrgViewModel.getPageNumbers().get(0) == 1; // verify start of page nums is correct
        assert createListOrgViewModel.getPageNumbers().get(4) == 5; // verify end of page nums is correct

        for (OrgViewModel org : createListOrgViewModel.getOrganizations()){
            assert org.getName().equals("The Avengers");
            assert org.getId() != null;
        }

        // test that command model exists
        CreateOrgCmdModel createOrgCmdModel = createListOrgViewModel.getCommandModel();
        assert createOrgCmdModel != null;

    }

    @Test
    public void saveCreateOrgCmdModel() {

        // arrange
        CreateListOrgViewModel createListOrgViewModel = new CreateListOrgViewModel();
        CreateOrgCmdModel createOrgCmdModel = new CreateOrgCmdModel();
        Location location = testHelperMethods.createTestLocations(1).get(0);

        createOrgCmdModel.setLocationId(location.getId());
        createOrgCmdModel.setName("The Avengers");
        createOrgCmdModel.setDescription("Earth's No. 1 Team");

        createListOrgViewModel.setCommandModel(createOrgCmdModel);

        // act
        Organization org = createListOrgWebService.saveCreateOrgCmdModel(createListOrgViewModel.getCommandModel());

        // assert
        assert org.getId() != null;
        assert org.getName().equals("The Avengers");
        assert org.getDescription().equals("Earth's No. 1 Team");
        assert org.getLocation().getId().equals(location.getId());

    }
}