package com.sg.webservices.location;

import com.sg.viewmodel.location.locationprofile.LocationProfileViewModel;

public interface LocationProfileWebService {

    public LocationProfileViewModel getLocationViewModel(Long id);
}
