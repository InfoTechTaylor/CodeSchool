package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;

import java.util.ArrayList;

public interface DvdLibraryDao {


    Dvd getDvd(int dvdId);

    ArrayList<Dvd> getAllDvds();

    Dvd removeDvd(int dvdId);

    Dvd addDvd(Dvd dvd);

    Dvd editDvd(Dvd dvd);
}
