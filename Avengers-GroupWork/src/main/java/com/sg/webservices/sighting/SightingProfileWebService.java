package com.sg.webservices.sighting;

import com.sg.viewmodel.sighting.sightingprofile.SightingProfileViewModel;

public interface SightingProfileWebService {

    public SightingProfileViewModel getSightingProfileViewModel(Long id);

}
