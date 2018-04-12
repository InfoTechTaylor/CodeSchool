package com.sg.webservice.people;

import com.sg.commandmodel.people.editperson.EditPersonCmdModel;
import com.sg.dto.Person;
import com.sg.viewmodel.people.editperson.EditPersonViewModel;
import com.sg.webservices.people.EditPersonWebService;

public class EditPersonWebServiceImpl implements EditPersonWebService {
    @Override
    public EditPersonViewModel getEditPersonViewModel(Long id) {
        return null;
    }

    @Override
    public Person saveEditPersonCmdModel(EditPersonCmdModel editPersonCmdModel) {
        return null;
    }
}
