package com.sg.webservice.sighting;

import com.sg.commandmodel.sighting.editsighting.EditSightingCmdModel;
import com.sg.dto.Sighting;
import com.sg.viewmodel.sighting.editsighting.EditSightingViewModel;
import com.sg.webservices.sighting.EditSightingWebService;

public class EditSightingWebServiceImpl implements EditSightingWebService {
    @Override
    public EditSightingViewModel getEditSightingViewModel(Long id) {
        return null;
    }

    @Override
    public Sighting saveEditSightingCmdModel(EditSightingCmdModel editSightingCmdModel) {
        return null;
    }
}
