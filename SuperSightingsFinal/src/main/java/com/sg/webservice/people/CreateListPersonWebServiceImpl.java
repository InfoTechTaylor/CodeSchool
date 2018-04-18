package com.sg.webservice.people;

import com.sg.commandmodel.people.createperson.CreatePersonCmdModel;
import com.sg.dto.*;
import com.sg.service.*;
import com.sg.util.PagingUtil;
import com.sg.viewmodel.people.createlistperson.CreateListPersonViewModel;
import com.sg.viewmodel.people.createlistperson.OrgViewModel;
import com.sg.viewmodel.people.createlistperson.PersonViewModel;
import com.sg.viewmodel.people.createlistperson.PowerViewModel;
import com.sg.webservices.exception.RelationshipFoundException;
import com.sg.webservices.people.CreateListPersonWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CreateListPersonWebServiceImpl implements CreateListPersonWebService {

    @Inject
    CreateListPersonWebService createListPersonWebService;

    @Inject
    OrganizationService organizationService;

    @Inject
    PowerService powerService;

    @Inject
    PersonService personService;

    @Inject
    PersonPowerService personPowerService;

    @Inject
    PersonOrganizationService personOrganizationService;

    @Inject
    SightingService sightingService;

    public List<OrgViewModel> translateOrgs(List<Organization> orgs){
        List<OrgViewModel> orgViews = new ArrayList<>();

        for(Organization org : orgs){
            OrgViewModel orgViewModel = new OrgViewModel();
            orgViewModel.setId(org.getId());
            orgViewModel.setName(org.getName());
            orgViews.add(orgViewModel);
        }

        return  orgViews;
    }

    public List<PowerViewModel> translatePowers(List<Power> powers){
        List<PowerViewModel> powerViews = new ArrayList<>();

        for(Power power : powers){
            PowerViewModel powerViewModel = new PowerViewModel();
            powerViewModel.setId(power.getId());
            powerViewModel.setName(power.getName());
            powerViews.add(powerViewModel);
        }

        return powerViews;
    }

    public List<PersonViewModel> translatePersons(List<Person> persons){
        List<PersonViewModel> personViews = new ArrayList<>();

        for(Person person: persons){
            PersonViewModel personViewModel = new PersonViewModel();
            personViewModel.setId(person.getId());
            personViewModel.setName(person.getName());
            personViewModel.setType(person.getType());
            personViews.add(personViewModel);
        }

        return personViews;
    }

    @Override
    public CreateListPersonViewModel getCreateListPersonViewModel(Integer limit, Integer offset, Integer numPagesToShow) {

        if(limit == null) limit = 5;
        if(offset == null) offset = 0;
        if(numPagesToShow == null) numPagesToShow = 5;

        // instantiate
        CreateListPersonViewModel createListPersonViewModel = new CreateListPersonViewModel();
        CreatePersonCmdModel createPersonCmdModel = new CreatePersonCmdModel();

        // look up stuff
        List<Organization> allOrgs = organizationService.retrieveAllOrganizations(Integer.MAX_VALUE, 0);
        List<Power> allPowers = powerService.retrieveAllPowers(Integer.MAX_VALUE, 0);
        List<Person> allPersons = personService.retrieveAllPersons(limit, offset);

        // calculate page info
        Integer currentPage = PagingUtil.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtil.getPageNumbers(currentPage, numPagesToShow);

        // put stuff
        createListPersonViewModel.setOrganizations(translateOrgs(allOrgs));
        createListPersonViewModel.setPowers(translatePowers(allPowers));
        createListPersonViewModel.setPersons(translatePersons(allPersons));
        createListPersonViewModel.setPageNumber(currentPage);
        createListPersonViewModel.setPageNumbers(pages);
        createListPersonViewModel.setCommandModel(createPersonCmdModel);

        return createListPersonViewModel;
    }

    @Override
    public Person saveCreatePersonCmdModel(CreatePersonCmdModel createPersonCmdModel) {

        // instantiate person & create in DB
        Person person = new Person();
        person.setName(createPersonCmdModel.getName());
        person.setDescription(createPersonCmdModel.getDescription());
        person.setType(createPersonCmdModel.getType());
        Person createdPerson = personService.create(person);

        // lookup stuff & create person org relationships
        Long[] orgIds = createPersonCmdModel.getOrgIds();
        for(int i=0; i< orgIds.length; i++){
            Organization orgToLookup = new Organization();
            orgToLookup.setId(orgIds[i]);
            Organization readOrganization = organizationService.read(orgToLookup);

            PersonOrganization personOrganization = new PersonOrganization();
            personOrganization.setOrganization(readOrganization);
            personOrganization.setPerson(createdPerson);

            personOrganizationService.create(personOrganization);
        }

        // lookup stuff & create person power relationships
        Long[] powerIds = createPersonCmdModel.getPowerIds();
        for(int i=0; i < powerIds.length; i++){
            Power powerToLookup = new Power();
            powerToLookup.setId(powerIds[i]);
            Power readPower = powerService.read(powerToLookup);

            PersonPower personPower = new PersonPower();
            personPower.setPerson(createdPerson);
            personPower.setPower(readPower);

            personPowerService.create(personPower);
        }

        return createdPerson;
    }

    @Override
    public void deletePersonCmdModel(Long id) throws RelationshipFoundException {
        //Instantiate
        Person person = new Person();
        person.setId(id);
        Person readPerson = personService.read(person);
        //Look stuff up
        List<Sighting> allSightings = sightingService.retrieveAllSightingsByPerson(readPerson, Integer.MAX_VALUE, Integer.valueOf(0));
        List<PersonPower> allPowers = personPowerService.retrieveAllPersonPowerByPerson(readPerson, Integer.MAX_VALUE, Integer.valueOf(0));
        if(allSightings.size() == 0 & allPowers.size()==0){
            personService.delete(person);
        }else{
            throw new RelationshipFoundException(allSightings.size() + " sighting(s)  and " + allPowers.size() + " power(s) associated with " +
                    readPerson.getName() + ".  Action ignored." );
        }
    }
}
