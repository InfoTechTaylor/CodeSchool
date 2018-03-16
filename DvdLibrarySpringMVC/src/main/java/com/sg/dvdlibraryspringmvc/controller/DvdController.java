package com.sg.dvdlibraryspringmvc.controller;

import com.sg.dvdlibraryspringmvc.dao.DvdLibraryDao;
import com.sg.dvdlibraryspringmvc.model.Dvd;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class DvdController {
    DvdLibraryDao dao;

    @Inject
    public DvdController(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String displayDvdPage(Model model){
        List<Dvd> allDvds = dao.getAllDvds();
        model.addAttribute("allDvds", allDvds);
        return "index";
    }

    @RequestMapping(value="/displayCreateDvdForm", method=RequestMethod.GET)
    public String displayCreateDvdForm(){
        return "createDvdForm";
    }

    @RequestMapping(value="/addDvd", method=RequestMethod.POST)
    public String addDvd(HttpServletRequest request, Model model){
        Dvd newDvd = new Dvd();
        newDvd.setTitle(request.getParameter("title"));
        newDvd.setReleaseYear(request.getParameter("releaseYear"));
        newDvd.setDirector(request.getParameter("director"));
        newDvd.setRating(request.getParameter("rating"));
        newDvd.setNotes(request.getParameter("notes"));

        dao.addDvd(newDvd);
        model.addAttribute(newDvd);
        return "redirect:/";
    }

    @RequestMapping(value="/deleteDvd", method=RequestMethod.GET)
    public String deleteDvd(HttpServletRequest request) {
        int dvdId = Integer.parseInt(request.getParameter("dvdId"));
        dao.removeDvd(dvdId);
        return "redirect:/";
    }

    @RequestMapping(value="/displayEditDvdForm", method=RequestMethod.GET)
    public String displayEditDvdForm(HttpServletRequest request, Model model){
        Dvd dvd = dao.getDvd(Integer.parseInt(request.getParameter("dvdId")));
        model.addAttribute("dvd", dvd);
        return "editDvdForm";
    }

    @RequestMapping(value="/editDvd", method=RequestMethod.POST)
    public String editDvd(@Valid @ModelAttribute("dvd")Dvd dvd, BindingResult result){
        dao.editDvd(dvd);
        return "redirect:/";
    }

    @RequestMapping(value="/viewDvd", method=RequestMethod.GET)
    public String viewDvd(HttpServletRequest request, Model model){
        Dvd dvd = dao.getDvd(Integer.parseInt(request.getParameter("dvdId")));
        model.addAttribute("dvd", dvd);
        return "viewDvd";
    }

}
