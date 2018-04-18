package com.sg.controller;

import com.sg.viewmodel.home.HomeViewModel;
import com.sg.webservices.home.HomePageWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller
@RequestMapping(value="/home")
public class HomeController {

    @Inject
    HomePageWebService homePageWebService;

    @RequestMapping(value="/latest")
    public String loadHomePage(Model model, @RequestParam(required=false) Integer offset){
        HomeViewModel homeViewModel = homePageWebService.getHomeViewModel(10, offset, 5);
        model.addAttribute("viewModel", homeViewModel);
        return "index";
    }
}
