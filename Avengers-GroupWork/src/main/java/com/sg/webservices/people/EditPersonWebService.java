package com.sg.webservices.people;

import com.sg.commandmodel.people.editperson.EditPersonCmdModel;
import com.sg.dto.Person;
import com.sg.viewmodel.people.editperson.EditPersonViewModel;

public interface EditPersonWebService {

    public EditPersonViewModel getEditPersonViewModel(Long id);

    public Person saveEditPersonCmdModel(EditPersonCmdModel editCommandModel);

}
