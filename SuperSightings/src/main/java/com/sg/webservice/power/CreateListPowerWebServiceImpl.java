package com.sg.webservice.power;

import com.sg.commandmodel.power.createpower.CreatePowerCmdModel;
import com.sg.dto.Power;
import com.sg.service.PowerService;
import com.sg.util.PagingUtil;
import com.sg.viewmodel.power.createlistpower.CreateListPowerViewModel;
import com.sg.viewmodel.power.createlistpower.PowerViewModel;
import com.sg.webservices.power.CreateListPowerWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CreateListPowerWebServiceImpl implements CreateListPowerWebService {

    @Inject
    PowerService powerService;

    private List<PowerViewModel> translate(List<Power> powers){
        List<PowerViewModel> powerViewModels = new ArrayList<>();

        for(Power power : powers){
            PowerViewModel powerViewModel = new PowerViewModel();
            powerViewModel.setId(power.getId());
            powerViewModel.setName(power.getName());
            powerViewModels.add(powerViewModel);
        }

        return powerViewModels;
    }

    @Override
    public CreateListPowerViewModel getCreateListPowerViewModel(int limit, int offset, int numPagesToShow) {

        // instantiate
        CreateListPowerViewModel createListPowerViewModel = new CreateListPowerViewModel();
        CreatePowerCmdModel createPowerCmdModel = new CreatePowerCmdModel();

        // look up stuff
        List<Power> powers = powerService.retrieveAllPowers(limit, offset);

        // calculate page info
        Integer currentPage = PagingUtil.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtil.getPageNumbers(currentPage, numPagesToShow);

        // put stuff
        createListPowerViewModel.setPageNumber(currentPage);
        createListPowerViewModel.setPageNumbers(pages);
        createListPowerViewModel.setCommandModel(createPowerCmdModel);
        createListPowerViewModel.setPowers(translate(powers));


        return createListPowerViewModel;
    }

    @Override
    public Power saveCreatePowerCmdModel(CreatePowerCmdModel createPowerCmdModel) {

        // instantiate
        Power power = new Power();

        // put stuff
        power.setName(createPowerCmdModel.getName());

        // save stuff
        Power createdPower = powerService.create(power);

        return createdPower;
    }
}
