package com.sg.webservice.people;

import com.sg.commandmodel.people.createperson.CreatePersonCmdModel;
import com.sg.dto.Person;
import com.sg.viewmodel.people.createlistperson.CreateListPersonViewModel;
import com.sg.webservices.people.CreateListPersonWebService;

public class CreateListPersonWebServiceImpl implements CreateListPersonWebService {
    @Override
    public CreateListPersonViewModel getCreateListPersonViewModel(int limit, int offset, int pageNumbers) {
        return null;
    }

    @Override
    public Person saveCreatePersonCmdModel(CreatePersonCmdModel createPersonCmdModel) {
        return null;
    }
}
