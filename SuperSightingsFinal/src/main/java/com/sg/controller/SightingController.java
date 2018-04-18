package com.sg.controller;

import com.sg.commandmodel.sighting.createsighting.CreateSightingCmdModel;
import com.sg.commandmodel.sighting.editsighting.EditSightingCmdModel;
import com.sg.dto.Sighting;
import com.sg.viewmodel.organization.orgprofile.OrgProfileViewModel;
import com.sg.viewmodel.sighting.createlistsighting.CreateListSightingViewModel;
import com.sg.viewmodel.sighting.editsighting.EditSightingViewModel;
import com.sg.viewmodel.sighting.sightingprofile.SightingProfileViewModel;
import com.sg.webservices.exception.RelationshipFoundException;
import com.sg.webservices.location.CreateListLocationWebService;
import com.sg.webservices.sighting.CreateListSightingWebService;
import com.sg.webservices.sighting.EditSightingWebService;
import com.sg.webservices.sighting.SightingProfileWebService;
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
@RequestMapping(value="/sighting")
public class SightingController {

    @Inject
    CreateListSightingWebService createListSightingWebService;

    @Inject
    SightingProfileWebService sightingProfileWebService;

    @Inject
    EditSightingWebService editSightingWebService;

    @RequestMapping(value = "/createList")
    public String createList(@RequestParam(required=false) Integer offset,
                             @RequestParam(required = false) String errorMessage, Model model) {
        CreateListSightingViewModel viewModel = createListSightingWebService.getCreateListSightingViewModel(5, offset, 5);
        model.addAttribute("viewModel", viewModel);
        model.addAttribute("sightingsList", viewModel.getSightings());
        model.addAttribute("commandModel", viewModel.getCommandModel());
        model.addAttribute("errorMessage", errorMessage);
        return "sighting/createList";
    }

    @RequestMapping(value = "/show")
    public String show(@RequestParam(required = true) Long id, Model model) {
        // required = true is default, added for clarity

        SightingProfileViewModel viewModel = sightingProfileWebService.getSightingProfileViewModel(id);

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("personsList", viewModel.getPersons());

        return "sighting/show";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("commandModel") CreateSightingCmdModel commandModel,
                         @RequestParam(required=false) Integer offset,
                            BindingResult bindingResult,
                            Model model) {

        if (bindingResult.hasErrors()) {

            // reload edit form if there are errors
            CreateListSightingViewModel viewModel = createListSightingWebService.getCreateListSightingViewModel(5, offset, 5);

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);
            model.addAttribute("locations", viewModel.getLocations());

            return "sighting/createList";
        }

        Sighting sighting = createListSightingWebService.saveCreateSightingCmdModel(commandModel);

        return "redirect:/sighting/show?id=" + sighting.getId();
    }


    @RequestMapping(value="/edit")
    public String editOrg(@RequestParam(required = true) Long id, Model model){

        EditSightingViewModel editSightingViewModel =
                editSightingWebService.getEditSightingViewModel(id);

        model.addAttribute("viewModel", editSightingViewModel);
        model.addAttribute("commandModel", editSightingViewModel.getEditCommandModel());

        return "sighting/edit";
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public String saveEdit(@Valid @ModelAttribute("commandModel")EditSightingCmdModel commandModel,
                              BindingResult bindingResult,
                              Model model){

        if(bindingResult.hasErrors()){

            // reload edit form if there are errors
            EditSightingViewModel viewModel = editSightingWebService.getEditSightingViewModel(commandModel.getId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "sighting/edit";
        }

        Sighting sighting = editSightingWebService.saveEditSightingCmdModel(commandModel);

        // did not design app to have a power profile page so no need to use power var
        return "redirect:/sighting/show?id=" + sighting.getId();
    }

    @RequestMapping(value="/delete")
    public String delete(@RequestParam(required = true) Long id){
        try {
            createListSightingWebService.deleteSightingCmdModel(id);
        } catch (RelationshipFoundException e){
            //model.addAttribute("errorMessage", e.getMessage());
            String message = e.getMessage();
            return "redirect:/location/createList?errorMessage=" + message;
        }

        return "redirect:/location/createList";
    }

}