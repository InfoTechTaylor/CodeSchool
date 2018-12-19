package com.sg.webservices.location;


import com.sg.commandmodel.location.editlocation.EditLocationCmdModel;
import com.sg.dto.Location;
import com.sg.viewmodel.location.editlocation.EditLocationViewModel;

public interface EditLocationWebService {

    public EditLocationViewModel getEditLocationViewModel(Long id);

    public Location saveEditLocationCmdModel(EditLocationCmdModel editCommandModel);
}
