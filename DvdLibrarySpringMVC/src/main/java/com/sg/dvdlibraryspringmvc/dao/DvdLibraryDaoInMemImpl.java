package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;

import java.time.LocalDate;
import java.util.*;

public class DvdLibraryDaoInMemImpl implements DvdLibraryDao {

    private Map<Integer, Dvd> dvdMap = new HashMap<>();
    int dvdIdCounter = 2;

    public DvdLibraryDaoInMemImpl(){
        Dvd newDvd1 = new Dvd();
        newDvd1.setDvdId(1);
        newDvd1.setTitle("Harry Potter and the Sorcerer's Stone");
        newDvd1.setReleaseDate(LocalDate.parse("2000-01-01"));
        newDvd1.setRating("PG");
        newDvd1.setDirector("unknown");
        newDvd1.setNotes("Great movie");
        dvdMap.put(newDvd1.getDvdId(), newDvd1);
    }

    @Override
    public Dvd getDvd(int dvdId) {
        return dvdMap.get(dvdId);
    }

    @Override
    public ArrayList<Dvd> getAllDvds() {
        return new ArrayList<Dvd>(dvdMap.values());
    }

    @Override
    public Dvd removeDvd(int dvdId) {
        return dvdMap.remove(dvdId);
    }

    @Override
    public Dvd addDvd(Dvd dvd) {
        dvd.setDvdId(dvdIdCounter);
        dvdIdCounter++;
        return dvdMap.put(dvd.getDvdId(), dvd);
    }

    @Override
    public Dvd editDvd(Dvd dvd) {
        return dvdMap.replace(dvd.getDvdId(), dvd);
    }
}
