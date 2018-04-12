package com.sg.webservice.organization;

import com.sg.commandmodel.organization.editorg.EditOrgCmdModel;
import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.service.LocationService;
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

import static org.junit.Assert.*;

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
    LocationService locationService;

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
        assert savedOrg.getId().equals(editOrgCmdModel.getId());
        assert savedOrg.getName().equals(editOrgCmdModel.getName());
        assert savedOrg.getDescription().equals(editOrgCmdModel.getDescription());
        assert savedOrg.getLocation().getId().equals(editOrgCmdModel.getLocationId());
    }
}