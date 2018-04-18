package com.sg.webservice.power;

import com.sg.commandmodel.power.editpower.EditPowerCmdModel;
import com.sg.dto.Power;
import com.sg.service.PowerService;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.power.editpower.EditPowerViewModel;
import com.sg.webservices.power.EditPowerWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Transactional
@Rollback
public class EditPowerWebServiceImplTest {

    @Inject
    TestHelperMethods testHelperMethods;

    @Inject
    EditPowerWebService editPowerWebService;

    @Inject
    PowerService powerService;

    @Test
    public void getEditPowerViewModel() {

        // arrange
        Power power = testHelperMethods.createTestPowers(1).get(0);

        // act
        EditPowerViewModel editPowerViewModel = editPowerWebService.getEditPowerViewModel(power.getId());

        // assert
        assert editPowerViewModel.getId() != null;

        EditPowerCmdModel editPowerCmdModel = editPowerViewModel.getEditCommandModel();
        assertEquals(power.getId(), editPowerCmdModel.getId());
        assertEquals(power.getName(), editPowerCmdModel.getName());
    }

    @Test
    public void saveEditPowerCmdModel() {

        // arrange
        Power power = testHelperMethods.createTestPowers(1).get(0);
        EditPowerCmdModel editPowerCmdModel = new EditPowerCmdModel();

        editPowerCmdModel.setId(power.getId());
        editPowerCmdModel.setName(power.getName());

        // act
        Power powerCreated = editPowerWebService.saveEditPowerCmdModel(editPowerCmdModel);

        // assert
        Power powerFromDB = powerService.read(powerCreated);
        assertNotNull(powerFromDB.getId());
        assertEquals(powerFromDB.getId(), power.getId());
        assertEquals(powerFromDB.getName(), power.getName());
    }
}