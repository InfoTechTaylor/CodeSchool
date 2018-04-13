package com.sg.webservice.people;

import com.sg.commandmodel.people.editperson.EditPersonCmdModel;
import com.sg.dto.*;
import com.sg.service.*;
import com.sg.viewmodel.people.editperson.EditPersonViewModel;
import com.sg.viewmodel.people.editperson.OrgViewModel;
import com.sg.viewmodel.people.editperson.PersonOrgViewModel;
import com.sg.viewmodel.people.editperson.PowerViewModel;
import com.sg.webservices.people.EditPersonWebService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EditPersonWebServiceImpl implements EditPersonWebService {

    @Inject
    EditPersonWebService editPersonWebService;

    @Inject
    PowerService powerService;

    @Inject
    OrganizationService organizationService;

    @Inject
    PersonService personService;

    @Inject
    PersonPowerService personPowerService;

    @Inject
    PersonOrganizationService personOrganizationService;


    //Translate Methods ************************************************************************************
    private List<OrgViewModel> translateOrgs(List<Organization> organizations){
        List<OrgViewModel> orgViewModels = new ArrayList<>();

        for(Organization org: organizations){
            OrgViewModel orgViewModel = translate(org);
            orgViewModels.add(orgViewModel);
        }
        return orgViewModels;
    }

    private OrgViewModel translate(Organization org) {
        OrgViewModel orgViewModel = new OrgViewModel();
        orgViewModel.setId(org.getId());
        orgViewModel.setName(org.getName());
        return orgViewModel;
    }

    private List<PowerViewModel> translatePowers(List<Power> powers){
        List<PowerViewModel> powerViewModels = new ArrayList<>();

        for(Power power: powers){
            PowerViewModel powerViewModel = translate(power);
            powerViewModels.add(powerViewModel);
        }
        return powerViewModels;
    }

    private PowerViewModel translate(Power power) {
        PowerViewModel powerViewModel = new PowerViewModel();
        powerViewModel.setId(power.getId());
        powerViewModel.setName(power.getName());
        return powerViewModel;
    }

    private EditPersonCmdModel translate(Person person) {
        EditPersonCmdModel editPersonCmdModel = new EditPersonCmdModel();
        editPersonCmdModel.setId(person.getId());
        editPersonCmdModel.setName(person.getName());
        editPersonCmdModel.setType(person.getType());
        editPersonCmdModel.setDescription(person.getDescription());
        return editPersonCmdModel;
    }


    @Override
    public EditPersonViewModel getEditPersonViewModel(Long id) {
        //Instantiate
        EditPersonViewModel editPersonViewModel = new EditPersonViewModel();

        //Look stuff up
        Person person = new Person();
        person.setId(id);
        Person personToEdit = personService.read(person);
        //For dropdowns
        List<Power> allPowers = powerService.retrieveAllPowers(Integer.MAX_VALUE, 0);
        List<Organization> allOrgs = organizationService.retrieveAllOrganizations(Integer.MAX_VALUE, 0);

        //For selected powers and orgs
        List<Power> existingPowers = powerService.retrieveAllPowersByPerson(personToEdit, Integer.MAX_VALUE, 0);
        List<Organization> existingOrgs = organizationService.retrieveAllOrganizationsByPerson(personToEdit, Integer.MAX_VALUE, 0);

        //Put stuff up
        editPersonViewModel.setOrganizations(translateOrgs(allOrgs));
        editPersonViewModel.setPowers(translatePowers(allPowers));
        editPersonViewModel.setEditCommandModel(translate(personToEdit));
        editPersonViewModel.setId(personToEdit.getId());

        if(existingPowers.size() > 0){
            Long[] powerIds = new Long[existingPowers.size()];

            int counter = 0;
            for(Power power: existingPowers){
                powerIds[counter] = power.getId();
                counter++;
            }
            editPersonViewModel.getEditCommandModel().setPowerIds(powerIds);
        }

        if(existingOrgs.size() > 0){
            Long[] orgIds = new Long[existingOrgs.size()];

            int counter = 0;
            for(Organization org : existingOrgs){
                orgIds[counter] = org.getId();
                counter++;
            }
            editPersonViewModel.getEditCommandModel().setOrgIds(orgIds);

        }

        return editPersonViewModel;
    }

    @Override
    public Person saveEditPersonCmdModel(EditPersonCmdModel editPersonCmdModel) {
        //Instantiate
        Person person = new Person();

        //Look stuff up
        List<Organization> organizations = new ArrayList<>();
        if(editPersonCmdModel.getOrgIds() != null){

            for(Long orgId: editPersonCmdModel.getOrgIds()){
                Organization org = new Organization();
                org.setId(orgId);
                organizations.add(organizationService.read(org));
            }
        }
        List<Power> powers = new ArrayList<>();
        if(editPersonCmdModel.getPowerIds() != null){

            for(Long powerId: editPersonCmdModel.getPowerIds()){
                Power power = new Power();
                power.setId(powerId);
                powers.add(powerService.read(power));
            }
        }

        //Put stuff
        person.setId(editPersonCmdModel.getId());
        person.setName(editPersonCmdModel.getName());
        person.setType(editPersonCmdModel.getType());
        person.setDescription(editPersonCmdModel.getDescription());

        personService.update(person);


        //Delete Exisiting Relationships
        List<PersonPower> previousPowers = personPowerService.retrieveAllPersonPowerByPerson(person, Integer.MAX_VALUE, Integer.valueOf(0));
        for(PersonPower personPower: previousPowers){
            personPowerService.delete(personPower);
        }
        List<PersonOrganization> previousOrgs = personOrganizationService.retrieveAllPersonOrganizationByPerson(person, Integer.MAX_VALUE, 0);
        for(PersonOrganization personOrganization: previousOrgs){
            personOrganizationService.delete(personOrganization);
        }

        //Create Relationships
        for(Organization org: organizations){
            PersonOrganization personOrganization = new PersonOrganization();
            personOrganization.setPerson(person);
            personOrganization.setOrganization(org);
            personOrganizationService.create(personOrganization);
        }

        for(Power power: powers){
            PersonPower personPower = new PersonPower();
            personPower.setPerson(person);
            personPower.setPower(power);
            personPowerService.create(personPower);
        }

        return person;

    }
}