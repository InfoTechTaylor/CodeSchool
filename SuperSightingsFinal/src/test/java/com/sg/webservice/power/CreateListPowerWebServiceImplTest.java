package com.sg.webservice.power;

import com.sg.commandmodel.power.createpower.CreatePowerCmdModel;
import com.sg.dto.Power;
import com.sg.util.TestHelperMethods;
import com.sg.viewmodel.power.createlistpower.CreateListPowerViewModel;
import com.sg.viewmodel.power.createlistpower.PowerViewModel;
import com.sg.webservices.power.CreateListPowerWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Transactional
@Rollback
public class CreateListPowerWebServiceImplTest {

    @Inject
    TestHelperMethods testHelperMethods;

    @Inject
    CreateListPowerWebService createListPowerWebService;

    @Test
    public void getCreateListPowerViewModel() {

        // arrange
        testHelperMethods.createTestPowers(15);

        // act
        CreateListPowerViewModel createListPowerViewModel =
                createListPowerWebService.getCreateListPowerViewModel(Integer.valueOf(5), 0, 5);

        // assert
        assert createListPowerViewModel.getPowers().size() == 5; // assert we get 5 powers
        assert createListPowerViewModel.getPageNumber() == 1; // assert we are on the right page
        assert createListPowerViewModel.getPageNumbers().size() == 5; // verify we have the right number of page nums
        assert createListPowerViewModel.getPageNumbers().get(0) == 1; // verify start of page nums is correct
        assert createListPowerViewModel.getPageNumbers().get(4) == 5; // verify end of page nums is correct

        int counter = 0;
        for (PowerViewModel power : createListPowerViewModel.getPowers()){
            assert power.getName().equals("Wealth" + counter);
            assert power.getId() != null;
            counter++;
        }

        // test that command model exists
        CreatePowerCmdModel createPowerCmdModel = createListPowerViewModel.getCommandModel();
        assert createPowerCmdModel != null;
    }

    @Test
    public void saveCreatePowerCmdModel() {

        // arrange
        CreatePowerCmdModel createPowerCmdModel = new CreatePowerCmdModel();
        createPowerCmdModel.setName("Strength");

        // act
        Power power =  createListPowerWebService.saveCreatePowerCmdModel(createPowerCmdModel);

        // assert
        assert power.getId() != null;
        assert power.getName().equals("Strength");
    }
}