package com.sg.webservice.people;

import com.sg.dto.Organization;
import com.sg.dto.Person;
import com.sg.dto.PersonOrganization;
import com.sg.dto.Power;
import com.sg.service.*;
import com.sg.viewmodel.people.personprofile.OrgViewModel;
import com.sg.viewmodel.people.personprofile.PersonProfileViewModel;
import com.sg.viewmodel.people.personprofile.PowerViewModel;
import com.sg.webservices.people.PersonProfileWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PersonProfileWebServiceImpl implements PersonProfileWebService {

    @Inject
    PersonProfileWebService personProfileWebService;

    @Inject
    PersonService personService;

    @Inject
    PowerService powerService;

    @Inject
    OrganizationService organizationService;

    private List<PowerViewModel> translatePowers(List<Power> allPowers){
        List<PowerViewModel> allPowerViews = new ArrayList<>();

        for(Power power: allPowers){
            PowerViewModel powerViewModel = new PowerViewModel();
            powerViewModel.setId(power.getId());
            powerViewModel.setName(power.getName());
            allPowerViews.add(powerViewModel);
        }

        return allPowerViews;
    }

    private List<OrgViewModel> translateOrgs(List<Organization> allOrgs){
        List<OrgViewModel> allOrgViews = new ArrayList<>();

        for(Organization org : allOrgs){
            OrgViewModel orgViewModel = new OrgViewModel();
            orgViewModel.setId(org.getId());
            orgViewModel.setName(org.getName());
        }
        return allOrgViews;
    }

    @Override
    public PersonProfileViewModel getPersonProfileViewModel(Long id) {

        // instantiate
        PersonProfileViewModel personProfileViewModel = new PersonProfileViewModel();

        // look up stuff
        Person person = new Person();
        person.setId(id);
        Person personRead = personService.read(person);

        List<Organization> allOrgsForPerson = organizationService.retrieveAllOrganizationsByPerson(person, Integer.MAX_VALUE, Integer.valueOf(0));
        List<Power> allPowersForPerson = powerService.retrieveAllPowersByPerson(person, Integer.MAX_VALUE, Integer.valueOf(0));

        // put stuff
        personProfileViewModel.setId(personRead.getId());
        personProfileViewModel.setName(personRead.getName());
        personProfileViewModel.setType(personRead.getType());
        personProfileViewModel.setDescription(personRead.getDescription());
        personProfileViewModel.setOrganizations(translateOrgs(allOrgsForPerson));
        personProfileViewModel.setPowers(translatePowers(allPowersForPerson));


        return personProfileViewModel;
    }
}
