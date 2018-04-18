package com.sg.controller;

import com.sg.commandmodel.organization.createorg.CreateOrgCmdModel;
import com.sg.commandmodel.people.createperson.CreatePersonCmdModel;
import com.sg.commandmodel.people.editperson.EditPersonCmdModel;
import com.sg.dto.Person;
import com.sg.service.PersonService;
import com.sg.viewmodel.organization.orgprofile.OrgProfileViewModel;
import com.sg.viewmodel.people.createlistperson.CreateListPersonViewModel;
import com.sg.viewmodel.people.editperson.EditPersonViewModel;
import com.sg.viewmodel.people.personprofile.PersonProfileViewModel;
import com.sg.webservices.exception.RelationshipFoundException;
import com.sg.webservices.people.CreateListPersonWebService;
import com.sg.webservices.people.EditPersonWebService;
import com.sg.webservices.people.PersonProfileWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping(value="/person")
public class PersonController {

    @Inject
    CreateListPersonWebService createListPersonWebService;

    @Inject
    PersonProfileWebService personProfileWebService;

    @Inject
    EditPersonWebService editPersonWebService;

    @Inject
    PersonService personService;

    @RequestMapping(value="/createList")
    public String listPeople(@RequestParam(required=false) Integer offset, @RequestParam(required = false) String errorMessage,
                             Model model){
        CreateListPersonViewModel viewModel = createListPersonWebService.getCreateListPersonViewModel(5, offset, 5);
        model.addAttribute("personsList", viewModel.getPersons());
        model.addAttribute("commandModel", viewModel.getCommandModel());
        model.addAttribute("viewModel", viewModel);
        model.addAttribute("errorMessage", errorMessage);
        return "person/createList";
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("commandModel")CreatePersonCmdModel commandModel,
                         @RequestParam(required=false) Integer offset,
                         BindingResult bindingResult,
                         Model model){

        if(bindingResult.hasErrors()){

            // reload edit form if there are errors
            CreateListPersonViewModel viewModel = createListPersonWebService.getCreateListPersonViewModel(5,offset,5);

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "person/createList";
        }

        Person person = createListPersonWebService.saveCreatePersonCmdModel(commandModel);

        return "redirect:/person/show?id=" + person.getId();
    }

    @RequestMapping(value="/edit")
    public String editPerson(@RequestParam(required = true) Long id, Model model){
        EditPersonViewModel viewModel = editPersonWebService.getEditPersonViewModel(id);
        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getEditCommandModel());
        model.addAttribute("powersList", viewModel.getPowers());
        model.addAttribute("orgsList", viewModel.getOrganizations());
        return "person/edit";
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public String saveEditPerson(@Valid @ModelAttribute("commandModel")EditPersonCmdModel commandModel,
                                 BindingResult bindingResult,
                                 Model model){

        if(bindingResult.hasErrors()){

            // reload edit form if there are errors
            EditPersonViewModel viewModel = editPersonWebService.getEditPersonViewModel(commandModel.getId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", viewModel.getEditCommandModel());

            return "person/edit";
        }

        Person person = editPersonWebService.saveEditPersonCmdModel(commandModel);
        return "redirect:/person/show?id=" + person.getId();
    }

    @RequestMapping(value="show")
    public String show(@RequestParam(required = true) Long id, Model model){

        PersonProfileViewModel viewModel = personProfileWebService.getPersonProfileViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "person/show";
    }

    @RequestMapping(value="delete")
    public String delete(@RequestParam(required = true) Long id){

        try {
            createListPersonWebService.deletePersonCmdModel(id);
        } catch (RelationshipFoundException e){
            //model.addAttribute("errorMessage", e.getMessage());
            String message = e.getMessage();
            return "redirect:/person/createList?errorMessage=" + message;
        }

        return "redirect:/person/createList";
    }
}
