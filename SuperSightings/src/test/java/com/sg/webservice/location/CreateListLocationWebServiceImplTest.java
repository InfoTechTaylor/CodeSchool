package com.sg.webservice.location;

import com.sg.commandmodel.location.createlocation.CreateLocationCmdModel;
import com.sg.dto.Location;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.location.createlistlocation.CreateListLocationViewModel;
import com.sg.viewmodel.location.createlistlocation.LocationViewModel;
import com.sg.webservices.location.CreateListLocationWebService;
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
public class CreateListLocationWebServiceImplTest {

    @Inject
    CreateListLocationWebService createListLocationWebService;

    @Inject
    TestHelperMethods testHelperMethodsObj;

    @Test
    public void getCreateListLocationViewModel() {

        // arrange
        List<Location> locations = testHelperMethodsObj.createTestLocations(15);

        // act
        CreateListLocationViewModel createListLocationViewModel =
                createListLocationWebService.getCreateListLocationViewModel(5, 0, 5);

        // assert
        assert createListLocationViewModel.getLocations().size() == 5; // assert we get 5 locations
        assert createListLocationViewModel.getPageNumber() == 1; // assert we are on the right page
        assert createListLocationViewModel.getPageNumbers().size() == 5; // verify we have the right number of page nums
        assert createListLocationViewModel.getPageNumbers().get(0) == 1; // verify start of page nums is correct
        assert createListLocationViewModel.getPageNumbers().get(4) == 5; // verify end of page nums is correct

        int counter = 0;
        for (LocationViewModel location : createListLocationViewModel.getLocations()){
            assert location.getName().equals("Central Park" + counter);
            assert location.getCity().equals("New York");
            assert location.getId() != null;
            counter++;
        }

        // test that command model exists
        CreateLocationCmdModel createLocationCmdModel = createListLocationViewModel.getCommandModel();
        assert createLocationCmdModel != null;
    }

    @Test
    public void saveCreateLocationCmdModel() {

        // arrange
        CreateListLocationViewModel createListLocationViewModel = new CreateListLocationViewModel();

        CreateLocationCmdModel createLocationCmdModel = new CreateLocationCmdModel();
        createLocationCmdModel.setLatitude(40.779287);
        createLocationCmdModel.setLongitude(-73.969326);
        createLocationCmdModel.setName("Central Park");
        createLocationCmdModel.setDescription("near Belvedere Castle");
        createLocationCmdModel.setStreet("79th Street");
        createLocationCmdModel.setCity("New York");
        createLocationCmdModel.setState("NY");
        createLocationCmdModel.setZip("10021");
        createLocationCmdModel.setCountry("USA");

        createListLocationViewModel.setCommandModel(createLocationCmdModel);

        // act
        Location location =
                createListLocationWebService.saveCreateLocationCmdModel(createListLocationViewModel.getCommandModel());

        // assert
        assert location.getId() != null;
        assertEquals(40.779287, location.getLatitude(), 0);
        assertEquals(-73.969326, location.getLongitude(), 0);
        assertEquals("Central Park", location.getName());
        assertEquals("near Belvedere Castle", location.getDescription());
        assertEquals("79th Street", location.getStreet());
        assertEquals("New York", location.getCity());
        assertEquals("NY", location.getState());
        assertEquals("10021", location.getZip());
        assertEquals("USA", location.getCountry());
    }
}