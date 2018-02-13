package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DvdLibraryDaoStubImpl implements DvdLibraryDao{

    Dvd onlyDvd;
    ArrayList<Dvd> dvdList = new ArrayList<>();

    public DvdLibraryDaoStubImpl(){
        onlyDvd = new Dvd("Batman", LocalDate.parse("2000-01-02"), "PG-13", "Joe", "Fox", "Great movie");
        dvdList.add(onlyDvd);
    }

    @Override
    public Dvd getDvd(String title) throws DvdLibraryPersistenceException {
        if(title.equals(onlyDvd.getTitle())){
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Dvd> getAllDvds() throws DvdLibraryPersistenceException {
        return dvdList;
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryPersistenceException {
        if(title.equals(onlyDvd.getTitle())){
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdLibraryPersistenceException {
        if(title.equals(onlyDvd.getTitle())){
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public Dvd editDvd(Dvd dvd) throws DvdLibraryPersistenceException {
        if(dvd.getTitle().equals(onlyDvd.getTitle())){
            return onlyDvd;
        } else {
            return null;
        }
    }
}
