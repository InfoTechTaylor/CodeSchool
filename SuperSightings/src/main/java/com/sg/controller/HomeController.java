package com.sg.controller;

import com.sg.webservices.home.HomePageWebService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
@RequestMapping(value="/home")
public class HomeController {

    @Inject
    HomePageWebService homePageWebService;

    @RequestMapping(value="/")
    public String loadHomePage(){
        return "index";
    }
}
