package com.sg.webservice.home;

import com.sg.dto.Location;
import com.sg.dto.Sighting;
import com.sg.service.LocationService;
import com.sg.service.SightingService;
import com.sg.util.PagingUtil;
import com.sg.viewmodel.home.HomeViewModel;
import com.sg.viewmodel.home.SightingViewModel;
import com.sg.webservices.home.HomePageWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class HomePageWebServiceImpl implements HomePageWebService{

    @Inject
    SightingService sightingService;

    @Inject
    LocationService locationService;

    @Override
    public HomeViewModel getHomeViewModel(Integer limit, Integer offset, Integer pageNumbers) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;
        if(pageNumbers == null) pageNumbers = 5;

        // initialize
        HomeViewModel homeViewModel = new HomeViewModel();

        // look up stuff
        List<Sighting> allSightings = sightingService.retrieveAllSightings(limit, offset);
        List<Sighting> allSightingsWithLocation = new ArrayList<>();
        for(Sighting sighting : allSightings){
            Location location = new Location();
            location.setId(sighting.getLocation().getId());
            sighting.setLocation(locationService.read(location));
            allSightingsWithLocation.add(sighting);
        }

        // calculate page info
        Integer currentPage = PagingUtil.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtil.getPageNumbers(currentPage, pageNumbers);

        // put stuff
        homeViewModel.setPageNumber(currentPage);
        homeViewModel.setPageNumbers(pages);
        homeViewModel.setSightings(translate(allSightingsWithLocation));

        return homeViewModel;
    }

    private List<SightingViewModel> translate(List<Sighting> allSightings){
        List<SightingViewModel> allViews = new ArrayList<>();
        for(Sighting sighting : allSightings){
            SightingViewModel viewModel = new SightingViewModel();
            viewModel.setId(sighting.getId());
            viewModel.setDate(sighting.getSightingDate());
            viewModel.setDescription(sighting.getDescription());
            viewModel.setLocationId(sighting.getLocation().getId());
            viewModel.setLocationName(sighting.getLocation().getName());
            allViews.add(viewModel);
        }
        return allViews;
    }
}
