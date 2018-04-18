package com.sg.controller;

import com.sg.commandmodel.organization.createorg.CreateOrgCmdModel;
import com.sg.commandmodel.organization.editorg.EditOrgCmdModel;
import com.sg.dto.Organization;
import com.sg.service.OrganizationService;
import com.sg.viewmodel.organization.createlistorg.CreateListOrgViewModel;
import com.sg.viewmodel.organization.editorg.EditOrgViewModel;
import com.sg.viewmodel.organization.orgprofile.OrgProfileViewModel;
import com.sg.webservices.exception.RelationshipFoundException;
import com.sg.webservices.organization.CreateListOrgWebService;
import com.sg.webservices.organization.EditOrgWebService;
import com.sg.webservices.organization.OrgProfileWebService;
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
@RequestMapping(value="/organization")
public class OrgController {

    @Inject
    CreateListOrgWebService createListOrgWebService;

    @Inject
    OrganizationService orgService;

    @Inject
    EditOrgWebService editOrgWebService;

    @Inject
    OrgProfileWebService orgProfileWebService;

    @RequestMapping(value="/createList")
    public String listOrgs(Model model, @RequestParam(required=false) Integer offset, @RequestParam(required = false) String errorMessage){

        CreateListOrgViewModel viewModel =
                createListOrgWebService.getCreateListOrgViewModel(5, offset, 5);

        model.addAttribute("allOrgs", viewModel.getOrganizations());
        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getCommandModel());
        model.addAttribute("errorMessage", errorMessage);
        return "organization/createList";
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String createOrg(@Valid @ModelAttribute("commandModel")CreateOrgCmdModel commandModel,
                            @RequestParam(required=false) Integer offset,
                            BindingResult bindingResult,
                            Model model){

        if(bindingResult.hasErrors()){

            // reload edit form if there are errors
            CreateListOrgViewModel viewModel = createListOrgWebService.getCreateListOrgViewModel(5,offset,5);

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "organization/createList";
        }

        Organization org = createListOrgWebService.saveCreateOrgCmdModel(commandModel);

        return "redirect:/organization/show?id=" + org.getId();
    }

    @RequestMapping(value="/delete", method = RequestMethod.GET)
    public String deletePower(@RequestParam(required = true) Long id){


        try {
            createListOrgWebService.deleteOrgCmdModel(id);
        } catch (RelationshipFoundException e){
            //model.addAttribute("errorMessage", e.getMessage());
            String message = e.getMessage();
            return "redirect:/organization/createList?errorMessage=" + message;
        }

        return "redirect:/organization/createList";
    }

    @RequestMapping(value="/edit")
    public String editOrg(@RequestParam(required = true) Long id, Model model){

        EditOrgViewModel editOrgViewModel =
                editOrgWebService.getEditOrgViewModel(id);

        model.addAttribute("viewModel", editOrgViewModel);
        model.addAttribute("commandModel", editOrgViewModel.getEditCommandModel());

        return "organization/edit";
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public String saveEditOrg(@Valid @ModelAttribute("commandModel")EditOrgCmdModel commandModel,
                                BindingResult bindingResult,
                                Model model){

        if(bindingResult.hasErrors()){

            // reload edit form if there are errors
            EditOrgViewModel viewModel = editOrgWebService.getEditOrgViewModel(commandModel.getId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "organization/edit";
        }

        Organization org = editOrgWebService.saveEditOrgCmdModel(commandModel);

        // did not design app to have a power profile page so no need to use power var
        return "redirect:/organization/show?id=" + org.getId();
    }

    @RequestMapping(value = "/show")
    public String show(@RequestParam(required = true) Long id, Model model) {
        // required = true is default, added for clarity

        OrgProfileViewModel viewModel = orgProfileWebService.getOrgProfileViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "organization/show";
    }
}
