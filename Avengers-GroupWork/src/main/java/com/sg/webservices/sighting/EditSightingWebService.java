package com.sg.webservices.sighting;

import com.sg.commandmodel.sighting.editsighting.EditSightingCmdModel;
import com.sg.dto.Sighting;
import com.sg.viewmodel.sighting.editsighting.EditSightingViewModel;

public interface EditSightingWebService {

    public EditSightingViewModel getEditSightingViewModel(Long id);

    public Sighting saveEditSightingCmdModel(EditSightingCmdModel editCommandModel);
}
