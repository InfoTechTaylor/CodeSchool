package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DvdLibraryDao {


    Dvd getDvd(int dvdId);


    List<Dvd> getAllDvds();

    void removeDvd(int dvdId);

    Dvd addDvd(Dvd dvd);

    Dvd editDvd(Dvd dvd);

    List<Dvd> searchDvds(Map<SearchTerm, String> criteria);
}
