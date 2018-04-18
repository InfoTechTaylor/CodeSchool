package com.sg.webservice.location;

import com.sg.dto.Location;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.location.locationprofile.LocationProfileViewModel;
import com.sg.webservices.location.LocationProfileWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Transactional
@Rollback
public class LocationProfileWebServiceImplTest {

    @Inject
    TestHelperMethods testHelperMethodsObj;

    @Inject
    LocationProfileWebService locationProfileWebService;

    @Test
    public void getLocationViewModel() {

        // arrange
        Location location = testHelperMethodsObj.createTestLocations(1).get(0);

        // act
        LocationProfileViewModel locationProfileViewModel =
                locationProfileWebService.getLocationViewModel(location.getId());

        // assert
        assert locationProfileViewModel.getId() != null;
        assertEquals(locationProfileViewModel.getId(), location.getId());
        assertEquals(40.779287, locationProfileViewModel.getLatitude(), 0);
        assertEquals(-73.969326, locationProfileViewModel.getLongitude(), 0);
        assertEquals("Central Park0", locationProfileViewModel.getName());
        assertEquals("near Belvedere Castle", locationProfileViewModel.getDescription());
        assertEquals("79th Street", locationProfileViewModel.getStreet());
        assertEquals("New York", locationProfileViewModel.getCity());
        assertEquals("NY", locationProfileViewModel.getState());
        assertEquals("10021", locationProfileViewModel.getZip());
        assertEquals("USA", locationProfileViewModel.getCountry());
    }
}