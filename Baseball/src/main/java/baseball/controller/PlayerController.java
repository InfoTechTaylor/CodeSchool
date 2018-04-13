package baseball.controller;

import baseball.commandmodel.player.createplayer.CreatePlayerCommandModel;
import baseball.commandmodel.player.editplayer.EditPlayerCommandModel;
import baseball.dto.Player;
import baseball.viewmodel.player.createplayer.CreatePlayerViewModel;
import baseball.viewmodel.player.editplayer.EditPlayerViewModel;
import baseball.viewmodel.player.playerlist.PlayerListViewModel;
import baseball.viewmodel.player.playerprofile.PlayerProfileViewModel;
import baseball.webservice.interfaces.PlayerWebService;
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
@RequestMapping(value="/player")
public class PlayerController {

    @Inject
    private PlayerWebService playerWebService;

    @RequestMapping(value="/list")
    public String list(@RequestParam(required = false) Integer offset, Model model){
        // we hard code the limit because we did not put an option to choose the limit in the UI
        PlayerListViewModel viewModel = playerWebService.getPlayerListViewModel(5, offset, 5);

        model.addAttribute("viewModel", viewModel);

        return "player/list";
    }

    @RequestMapping(value = "/show")
    public String show(@RequestParam(required = true) Long id, Model model) {
        // required = true is default, added for clarity

        PlayerProfileViewModel viewModel = playerWebService.getPlayerProfileViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "player/show";
    }

    // show edit form with prefilled values
    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true) Long id,Model model){

        EditPlayerViewModel viewModel = playerWebService.getEditPlayerViewModel(id);

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getEditPlayerCommandModel());

        return "player/edit";
    }

    // handle form submission
    @RequestMapping(value="/edit", method= RequestMethod.POST)
    public String saveEdit(@Valid @ModelAttribute("commandModel")EditPlayerCommandModel commandModel, BindingResult bindingResult,
                           Model model){

        if(bindingResult.hasErrors()){

            // reload edit form if there are errors
            EditPlayerViewModel viewModel = playerWebService.getEditPlayerViewModel(commandModel.getId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "player/edit";
        }

        Player player = playerWebService.saveEditPlayerCommandModel(commandModel);

        return "redirect:/player/show?id=" + player.getId();
    }


    // show create form
    @RequestMapping(value = "/create")
    public String create(Model model){

        CreatePlayerViewModel viewModel = playerWebService.getCreatePlayerViewModel();

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getCreatePlayerCommandModel());

        return "player/create";
    }

    // handle form submission
    @RequestMapping(value="/create", method= RequestMethod.POST)
    public String saveCreate(@Valid @ModelAttribute("commandModel")CreatePlayerCommandModel commandModel, BindingResult bindingResult,
                             Model model){

        if(bindingResult.hasErrors()){

            // reload edit form if there are errors
            CreatePlayerViewModel viewModel = playerWebService.getCreatePlayerViewModel();

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "player/create";
        }

        Player player = playerWebService.saveCreatePlayerCommandModel(commandModel);

        return "redirect:/player/show?id=" + player.getId();
    }

}
