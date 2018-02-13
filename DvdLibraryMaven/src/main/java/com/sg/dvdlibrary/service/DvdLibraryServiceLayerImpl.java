package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryAuditDao;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

public class DvdLibraryServiceLayerImpl implements DvdLibraryServiceLayer {
    DvdLibraryDao dao;
    private DvdLibraryAuditDao auditDao;


    public DvdLibraryServiceLayerImpl(DvdLibraryDao dao, DvdLibraryAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }


    @Override
    public void createDvd(Dvd dvd) throws
            DvdLibraryDuplicateIdException,
            DvdLibraryDataValidationException,
            DvdLibraryPersistenceException {

        // first check to see if there is already a Dvd associated with the given title
        // if so, throw a DvdLibraryDuplicateIdException
        if(dao.getDvd(dvd.getTitle()) != null) {
            throw new DvdLibraryDuplicateIdException("ERROR: Could not create DVD. Title " +
                    dvd.getTitle() + " already exists");
        }

        // validate all the fields on the given dvd object
        // this throws an exception if any of the validation rules are violated
        validateDvdData(dvd);

        // we passed the validation/business rules, persist the Dvd object
        dao.addDvd(dvd.getTitle(), dvd);

        // the student was successfully created, now write to the audit file
        auditDao.writeAuditEntry("Dvd " + dvd.getTitle() + " CREATED.");
    }


    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryPersistenceException {
        return dao.getAllDvds();
    }


    @Override
    public Dvd getDvd(String title) throws DvdLibraryPersistenceException {
        return dao.getDvd(title);
    }


    @Override
    public Dvd removeDvd(String title) throws DvdLibraryPersistenceException {
        Dvd removedDvd = dao.removeDvd(title);
        //write to audit log
        auditDao.writeAuditEntry("Dvd " + title + " REMOVED.");
        return removedDvd;
    }


    @Override
    public Dvd editDvd(Dvd dvd) throws DvdLibraryPersistenceException {
        return dao.editDvd(dvd);
    }


    @Override
    public boolean titleExists(String title) throws DvdLibraryPersistenceException {
        Dvd dvd = dao.getDvd(title);
        if (dvd != null) {
            return true;
        } else {
            return false;
        }
    }


    private void validateDvdData(Dvd dvd) throws DvdLibraryDataValidationException {

        if (dvd.getTitle() == null
                || dvd.getTitle().trim().length() == 0
                || dvd.getUserRating() == null
                || dvd.getUserRating().trim().length() == 0
                || dvd.getStudio() == null
                || dvd.getStudio().trim().length() == 0
                || dvd.getDirectorName() == null
                || dvd.getDirectorName().trim().length() == 0
                || dvd.getRatingMPAA() == null
                || dvd.getRatingMPAA().trim().length() == 0
                || dvd.getReleaseDate() == null
                || dvd.getReleaseDate().toString().trim().length() == 0) {
            throw new DvdLibraryDataValidationException(
                    "ERROR: All fields [title, user rating, studio, director name, MPAA rating, release date] are required.");
        }
    }



}
