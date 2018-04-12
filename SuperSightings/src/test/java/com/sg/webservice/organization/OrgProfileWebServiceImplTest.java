package com.sg.webservice.organization;

import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.organization.orgprofile.OrgProfileViewModel;
import com.sg.webservices.organization.OrgProfileWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Transactional
@Rollback
public class OrgProfileWebServiceImplTest {

    @Inject
    TestHelperMethods testHelperMethods;

    @Inject
    OrgProfileWebService orgProfileWebService;

    @Test
    public void getOrgProfileViewModel() {
        // arrange
        Location location = testHelperMethods.createTestLocations(1).get(0);
        Organization org = testHelperMethods.createTestOrgs(1, location).get(0);

        // act
        OrgProfileViewModel orgProfileViewModel = orgProfileWebService.getOrgProfileViewModel(org.getId());

        // assert
        assert orgProfileViewModel.getId().equals(org.getId());
        assert orgProfileViewModel.getName().equals(org.getName());
        assert orgProfileViewModel.getDescription().equals(org.getDescription());
        assert orgProfileViewModel.getLocationId().equals(org.getLocation().getId());
        assert orgProfileViewModel.getLocationName().equals(org.getLocation().getName());
    }
}