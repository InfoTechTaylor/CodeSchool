package com.sg.controller;

import com.sg.commandmodel.power.createpower.CreatePowerCmdModel;
import com.sg.commandmodel.power.editpower.EditPowerCmdModel;
import com.sg.dto.Power;
import com.sg.service.PowerService;
import com.sg.viewmodel.power.createlistpower.CreateListPowerViewModel;
import com.sg.viewmodel.power.editpower.EditPowerViewModel;
import com.sg.webservices.exception.RelationshipFoundException;
import com.sg.webservices.power.CreateListPowerWebService;
import com.sg.webservices.power.EditPowerWebService;
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
@RequestMapping(value= "/power")
public class PowerController {

    @Inject
    CreateListPowerWebService createListPowerWebService;

    @Inject
    EditPowerWebService editPowerWebService;

    @Inject
    PowerService powerService;

    @RequestMapping(value= "/createList")
    public String listPowers(@RequestParam(required=false) Integer offset,
                             @RequestParam(required = false) String errorMessage, Model model){
        CreateListPowerViewModel createListPowerViewModel
                = createListPowerWebService.getCreateListPowerViewModel(5, offset, 5);
        model.addAttribute("powerList", createListPowerViewModel.getPowers());
        model.addAttribute("commandModel", createListPowerViewModel.getCommandModel());
        model.addAttribute("viewModel", createListPowerViewModel);
        model.addAttribute("errorMessage", errorMessage);
        return "power/createList";
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String createPower(@Valid @ModelAttribute("commandModel")CreatePowerCmdModel commandModel,
                              BindingResult bindingResult, @RequestParam(required=false) Integer offset,
                              @RequestParam(required=true) String name, Model model){

        if(bindingResult.hasErrors()){

            // reload edit form if there are errors
            CreateListPowerViewModel viewModel = createListPowerWebService.getCreateListPowerViewModel(5, 0, 5);

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "power/createList";
        }

        //CreatePowerCmdModel commandModel = new CreatePowerCmdModel();
        commandModel.setName(name);

        createListPowerWebService.saveCreatePowerCmdModel(commandModel);

        return "redirect:/power/createList";
    }

    @RequestMapping(value="/delete", method = RequestMethod.GET)
    public String deletePower(@RequestParam(required=true) Long id){

        try {
            createListPowerWebService.deletePowerCmdModel(id);
        } catch (RelationshipFoundException e){
            //model.addAttribute("errorMessage", e.getMessage());
            String message = e.getMessage();
            return "redirect:/power/createList?errorMessage=" + message;
        }

        return "redirect:/power/createList";
    }

    @RequestMapping(value="/edit")
    public String editPower(@RequestParam Long id, Model model){

        EditPowerViewModel editPowerViewModel =
                editPowerWebService.getEditPowerViewModel(id);

        model.addAttribute("viewModel", editPowerViewModel);
        model.addAttribute("commandModel", editPowerViewModel.getEditCommandModel());

        return "power/edit";
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public String saveEditPower(@Valid @ModelAttribute("commandModel")EditPowerCmdModel commandModel,
                                BindingResult bindingResult,
                                Model model){

        if(bindingResult.hasErrors()){

            // reload edit form if there are errors
            EditPowerViewModel viewModel = editPowerWebService.getEditPowerViewModel(commandModel.getId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "power/edit";
        }

        Power power = editPowerWebService.saveEditPowerCmdModel(commandModel);

        // did not design app to have a power profile page so no need to use power var
        return "redirect:/power/createList";
    }
}
