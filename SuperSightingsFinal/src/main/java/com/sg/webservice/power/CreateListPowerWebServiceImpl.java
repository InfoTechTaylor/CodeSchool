package com.sg.webservice.power;

import com.sg.commandmodel.power.createpower.CreatePowerCmdModel;
import com.sg.dto.Person;
import com.sg.dto.Power;
import com.sg.service.PersonService;
import com.sg.service.PowerService;
import com.sg.util.PagingUtil;
import com.sg.viewmodel.power.createlistpower.CreateListPowerViewModel;
import com.sg.viewmodel.power.createlistpower.PowerViewModel;
import com.sg.webservices.exception.RelationshipFoundException;
import com.sg.webservices.power.CreateListPowerWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CreateListPowerWebServiceImpl implements CreateListPowerWebService {

    @Inject
    PowerService powerService;

    @Inject
    PersonService personService;

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
    public CreateListPowerViewModel getCreateListPowerViewModel(Integer limit, Integer offset, Integer numPagesToShow) {

        if(limit == null) limit = 5;
        if(offset == null) offset = 0;
        if(numPagesToShow == null) numPagesToShow = 5;

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

    @Override
    public void deletePowerCmdModel(Long id) throws RelationshipFoundException {
        //Instantiate
        Power power = new Power();
        power.setId(id);

        //Look stuff up
        List<Person> allPersonsWPower = personService.retrieveAllPersonsByPower(power, Integer.MAX_VALUE, Integer.valueOf(0));

        //delete stuff
        if(allPersonsWPower.size() == 0){
            powerService.delete(power);
        }else{
            throw new RelationshipFoundException(allPersonsWPower.size() + " people are associated with this power. Action ignored.");
        }
    }
}
