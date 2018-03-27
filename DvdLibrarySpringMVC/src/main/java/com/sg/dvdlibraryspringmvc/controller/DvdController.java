package com.sg.dvdlibraryspringmvc.controller;

import com.sg.dvdlibraryspringmvc.dao.DvdLibraryDao;
import com.sg.dvdlibraryspringmvc.dao.SearchTerm;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public String displayCreateDvdForm(@Valid @ModelAttribute("dvd")Dvd dvd, BindingResult result){
        return "createDvdForm";
    }

    @RequestMapping(value="/addDvd", method=RequestMethod.POST)
    public String addDvd(@Valid @ModelAttribute("dvd")Dvd dvd, BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            return "editDvdForm";
        }

//        Dvd createDvd = new Dvd();
//        createDvd.setTitle(request.getParameter("title"));
//        createDvd.setReleaseDate(LocalDate.parse(request.getParameter("releaseYear")));
//        createDvd.setDirector(request.getParameter("director"));
//        createDvd.setRating(request.getParameter("rating"));
//        createDvd.setNotes(request.getParameter("notes"));

        dao.addDvd(dvd);
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

        if(result.hasErrors()){
            return "editDvdForm";
        }

        dao.editDvd(dvd);
        return "redirect:/";
    }

    @RequestMapping(value="/viewDvd", method=RequestMethod.GET)
    public String viewDvd(HttpServletRequest request, Model model){
        Dvd dvd = dao.getDvd(Integer.parseInt(request.getParameter("dvdId")));
        model.addAttribute("dvd", dvd);
        return "viewDvd";
    }

    @RequestMapping(value="/search", method=RequestMethod.GET)
    public String search(HttpServletRequest request, Model model) {
//        List<Dvd> allDvds = dao.getAllDvds();
//
//        String searchTerm = request.getParameter("searchBox");
//        String searchCategory = request.getParameter("searchCategory");
//
//        List<Dvd> filteredDvds = new ArrayList<>();
//        for(Dvd currentDvd : allDvds){
//            if(searchCategory.equals("title")){
//                if(currentDvd.getTitle().equals(searchTerm)) {
//                    filteredDvds.add(currentDvd);
//                }
//            } else if (searchCategory.equals("releaseYear")){
//                if(currentDvd.getReleaseDate().equals(searchTerm)) {
//                    filteredDvds.add(currentDvd);
//                }
//            } else if (searchCategory.equals("directorName")){
//                if(currentDvd.getDirector().equals(searchTerm)) {
//                    filteredDvds.add(currentDvd);
//                }
//            } else if (searchCategory.equals("rating")){
//                if(currentDvd.getRating().equals(searchTerm)) {
//                    filteredDvds.add(currentDvd);
//                }
//            }
//        }
        String searchTerm = request.getParameter("searchBox");
        SearchTerm searchCategory = SearchTerm.valueOf((request.getParameter("searchCategory")).toUpperCase());
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(searchCategory, searchTerm);
        List<Dvd> filteredDvds = dao.searchDvds(criteria);

        model.addAttribute("allDvds", filteredDvds);
        return "searchResults";
    }

}
