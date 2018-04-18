package com.sg.controller;

import com.sg.commandmodel.location.createlocation.CreateLocationCmdModel;
import com.sg.commandmodel.location.editlocation.EditLocationCmdModel;

import com.sg.dto.Location;
import com.sg.service.LocationService;
import com.sg.viewmodel.location.createlistlocation.CreateListLocationViewModel;
import com.sg.viewmodel.location.createlistlocation.LocationViewModel;
import com.sg.viewmodel.location.editlocation.EditLocationViewModel;
import com.sg.viewmodel.location.locationprofile.LocationProfileViewModel;
import com.sg.webservices.exception.RelationshipFoundException;
import com.sg.webservices.location.CreateListLocationWebService;
import com.sg.webservices.location.EditLocationWebService;
import com.sg.webservices.location.LocationProfileWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(value="/location")
public class LocationController {

    @Inject
    CreateListLocationWebService createListLocationWebService;

    @Inject
    LocationService locationService;

    @Inject
    EditLocationWebService editLocationWebService;

    @Inject
    LocationProfileWebService locationProfileWebService;

    @RequestMapping(value="/createList")
    public String createList(@RequestParam(required = false) Integer offset,
                             @RequestParam(required = false) String errorMessage, Model model){
        CreateListLocationViewModel viewModel = createListLocationWebService.getCreateListLocationViewModel(5, offset, 5);
        model.addAttribute("viewModel", viewModel);
        model.addAttribute("allLocations", viewModel.getLocations());
        model.addAttribute("commandModel", viewModel.getCommandModel());
        model.addAttribute("errorMessage", errorMessage);
        return "location/createList";
    }

    @RequestMapping(value="/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(required = true) Long id, Model model){

        try {
            createListLocationWebService.deleteLocationCmdModel(id);
        } catch (RelationshipFoundException e){
            //model.addAttribute("errorMessage", e.getMessage());
            String message = e.getMessage();
            return "redirect:/location/createList?errorMessage=" + message;
        }

        return "redirect:/location/createList";
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("commandModel")CreateLocationCmdModel commandModel,
                         BindingResult bindingResult,
                         @RequestParam(required=false) Integer offset,
                         Model model){

        if(bindingResult.hasErrors()){

            // reload edit form if there are errors
            CreateListLocationViewModel viewModel = createListLocationWebService.getCreateListLocationViewModel(5,offset,5);

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);
            model.addAttribute("allLocations", viewModel.getLocations());

            return "location/createList";
        }

        Location location = createListLocationWebService.saveCreateLocationCmdModel(commandModel);

        return "redirect:/location/show?id=" + location.getId();
    }

    @RequestMapping(value="/edit")
    public String edit(@RequestParam(required = true) Long id, Model model){

        EditLocationViewModel editLocationViewModel =
                editLocationWebService.getEditLocationViewModel(id);

        model.addAttribute("viewModel", editLocationViewModel);
        model.addAttribute("commandModel", editLocationViewModel.getEditCommandModel());

        return "location/edit";
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public String saveEdit(@Valid @ModelAttribute("commandModel")EditLocationCmdModel commandModel,
                              BindingResult bindingResult,
                              Model model){

        if(bindingResult.hasErrors()){

            // reload edit form if there are errors
            EditLocationViewModel viewModel = editLocationWebService.getEditLocationViewModel(commandModel.getId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "location/edit";
        }

        Location location = editLocationWebService.saveEditLocationCmdModel(commandModel);

        // did not design app to have a power profile page so no need to use power var
        return "redirect:/location/show?id=" + location.getId();
    }

    @RequestMapping(value = "/show")
    public String show(@RequestParam(required = true) Long id, Model model) {
        // required = true is default, added for clarity

        LocationProfileViewModel viewModel = locationProfileWebService.getLocationViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "location/show";
    }


}
