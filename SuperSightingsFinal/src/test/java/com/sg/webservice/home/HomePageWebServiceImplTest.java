package com.sg.webservice.home;

import com.sg.dto.Location;
import com.sg.dto.Sighting;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.home.HomeViewModel;
import com.sg.viewmodel.home.SightingViewModel;
import com.sg.webservices.home.HomePageWebService;
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
public class HomePageWebServiceImplTest {

    @Inject
    HomePageWebService homePageWebService;

    @Inject
    TestHelperMethods testHelperMethods;

    @Test
    public void getHomeViewModel() {
        // arrange
        Location location = testHelperMethods.createTestLocations(1).get(0);
        List<Sighting> sightings = testHelperMethods.createTestSightingsTwo(15, location);

        // act
        HomeViewModel homeViewModel =
                homePageWebService.getHomeViewModel(Integer.valueOf(10), Integer.valueOf(0), Integer.valueOf(5));

        // assert
        assert homeViewModel.getSightings().size() == 10;
        assert homeViewModel.getPageNumber() == 1;
        assert homeViewModel.getPageNumbers().size() == 5;

        int counter = 0;
        for(SightingViewModel viewModel : homeViewModel.getSightings()){
            assert viewModel.getId() != null;
            assert viewModel.getDescription().equals("Superman vs Batman showdown" + counter);
            counter++;
        }

    }
}