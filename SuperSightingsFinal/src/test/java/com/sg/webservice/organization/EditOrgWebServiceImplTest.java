package com.sg.webservice.organization;

import com.sg.commandmodel.organization.editorg.EditOrgCmdModel;
import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.service.OrganizationService;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.organization.editorg.EditOrgViewModel;
import com.sg.viewmodel.organization.editorg.LocationViewModel;
import com.sg.webservices.organization.EditOrgWebService;
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
public class EditOrgWebServiceImplTest {

    @Inject
    TestHelperMethods testHelperMethods;

    @Inject
    EditOrgWebService editOrgWebService;

    @Inject
    OrganizationService organizationService;

    @Test
    public void getEditOrgViewModel() {

        // arrange
        List<Location> locationList = testHelperMethods.createTestLocations(15);
        Location location = locationList.get(0);
        Organization org = testHelperMethods.createTestOrgs(1, location).get(0);

        // act
        EditOrgViewModel editOrgViewModel = editOrgWebService.getEditOrgViewModel(org.getId());

        // assert
        assert editOrgViewModel.getId() != null;
        assert editOrgViewModel.getEditCommandModel().getId().equals(org.getId());
        assert editOrgViewModel.getEditCommandModel().getName().equals(org.getName());
        assert editOrgViewModel.getEditCommandModel().getDescription().equals(org.getDescription());
        assert editOrgViewModel.getEditCommandModel().getLocationId().equals(org.getLocation().getId());
        assert editOrgViewModel.getLocations().size() == 15;

        // loop through locationviewmodels list that is returned in the act step (act translates list of DTO locations
        // to LocationViewModel list
        List<LocationViewModel> locations = editOrgViewModel.getLocations();
        int counter = 0;
        for(LocationViewModel locationViewModel : locations){
            assert locationViewModel.getId() != null;
            assert locationViewModel.getName().equals("Central Park" + counter);
            counter++;
        }

    }

    @Test
    public void saveEditOrgCmdModel() {
        // arrange
        Location location = testHelperMethods.createTestLocations(1).get(0);
        Organization org = testHelperMethods.createTestOrgs(1, location).get(0);
        EditOrgCmdModel editOrgCmdModel = new EditOrgCmdModel();
        editOrgCmdModel.setId(org.getId());
        editOrgCmdModel.setName(org.getName());
        editOrgCmdModel.setDescription(org.getDescription());
        editOrgCmdModel.setLocationId(org.getLocation().getId());

        // act
        Organization savedOrg = editOrgWebService.saveEditOrgCmdModel(editOrgCmdModel);

        // assert
        Organization orgFromDB = organizationService.read(savedOrg);
        assert orgFromDB.getId().equals(editOrgCmdModel.getId());
        assert orgFromDB.getName().equals(editOrgCmdModel.getName());
        assert orgFromDB.getDescription().equals(editOrgCmdModel.getDescription());
        assert orgFromDB.getLocation().getId().equals(editOrgCmdModel.getLocationId());
    }
}