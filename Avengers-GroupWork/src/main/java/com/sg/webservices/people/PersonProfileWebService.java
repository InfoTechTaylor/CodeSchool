package com.sg.webservices.people;

import com.sg.viewmodel.people.personprofile.PersonProfileViewModel;

public interface PersonProfileWebService {

    public PersonProfileViewModel getPersonProfileViewModel(Long id);
}
