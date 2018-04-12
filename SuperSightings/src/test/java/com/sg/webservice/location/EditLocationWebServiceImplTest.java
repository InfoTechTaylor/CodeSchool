package com.sg.webservice.location;

import com.sg.commandmodel.location.editlocation.EditLocationCmdModel;
import com.sg.dto.Location;
import com.sg.service.LocationService;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.location.editlocation.EditLocationViewModel;
import com.sg.webservices.location.EditLocationWebService;
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
public class EditLocationWebServiceImplTest {

    @Inject
    EditLocationWebService editLocationWebService;

    @Inject
    TestHelperMethods testHelperMethodsObj;

    @Inject
    LocationService locationService;

    @Test
    public void getEditLocationViewModel() {

        // arrange
        Location location = testHelperMethodsObj.createTestLocations(1).get(0);

        // act
        EditLocationViewModel editLocationViewModel = editLocationWebService.getEditLocationViewModel(location.getId());

        // assert
        assert editLocationViewModel.getId() != null;

        EditLocationCmdModel editCommandModel = editLocationViewModel.getEditCommandModel();
        assertEquals(location.getId(), editCommandModel.getId());
        assertEquals(40.779287, editCommandModel.getLatitude(), 0);
        assertEquals(-73.969326, editCommandModel.getLongitude(), 0);
        assertEquals("Central Park0", editCommandModel.getName());
        assertEquals("near Belvedere Castle", editCommandModel.getDescription());
        assertEquals("79th Street", editCommandModel.getStreet());
        assertEquals("New York", editCommandModel.getCity());
        assertEquals("NY", editCommandModel.getState());
        assertEquals("10021", editCommandModel.getZip());
        assertEquals("USA", editCommandModel.getCountry());
    }

    @Test
    public void saveEditLocationCmdModel() {

        // arrange
        Location location = testHelperMethodsObj.createTestLocations(1).get(0);
        EditLocationCmdModel editLocationCmdModel = new EditLocationCmdModel();

        editLocationCmdModel.setId(location.getId());
        editLocationCmdModel.setLatitude(42.779287);
        editLocationCmdModel.setLongitude(-74.969326);
        editLocationCmdModel.setName("City Hall");
        editLocationCmdModel.setDescription("NYC city hall");
        editLocationCmdModel.setStreet("1st Street");
        editLocationCmdModel.setCity("New York City");
        editLocationCmdModel.setState("NH");
        editLocationCmdModel.setZip("10101");
        editLocationCmdModel.setCountry("UK");

        // act
        Location locationCreated = editLocationWebService.saveEditLocationCmdModel(editLocationCmdModel);

        // assert
        assert locationCreated.getId() != null;
        assertEquals(location.getId(), locationCreated.getId());
        assertEquals(42.779287, locationCreated.getLatitude(), 0);
        assertEquals(-74.969326, locationCreated.getLongitude(), 0);
        assertEquals("City Hall", locationCreated.getName());
        assertEquals("NYC city hall", locationCreated.getDescription());
        assertEquals("1st Street", locationCreated.getStreet());
        assertEquals("New York City", locationCreated.getCity());
        assertEquals("NH", locationCreated.getState());
        assertEquals("10101", locationCreated.getZip());
        assertEquals("UK", locationCreated.getCountry());
    }
}