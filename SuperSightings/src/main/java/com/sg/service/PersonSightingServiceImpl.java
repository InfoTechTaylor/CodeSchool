package com.sg.service;


import com.sg.dao.PersonSightingDao;
import com.sg.dto.PersonSighting;

import javax.inject.Inject;
import java.util.List;

public class PersonSightingServiceImpl implements PersonSightingService{

    PersonSightingDao personSightingDao;

    @Inject
    public PersonSightingServiceImpl(PersonSightingDao personSightingDao) {
        this.personSightingDao = personSightingDao;
    }

    @Override
    public PersonSighting create(PersonSighting personSighting) {
        return personSightingDao.create(personSighting);
    }

    @Override
    public PersonSighting read(PersonSighting personSighting) {
        return personSightingDao.read(personSighting);
    }

    @Override
    public void update(PersonSighting personSighting) {
        personSightingDao.update(personSighting);
    }

    @Override
    public void delete(PersonSighting personSighting) {
        personSightingDao.delete(personSighting);
    }

    @Override
    public List<PersonSighting> retrieveAllPersonSightings(int limit, int offset) {
        return personSightingDao.retrieveAllPersonSightings(limit, offset);
    }
}
