package com.sg.service;


import com.sg.dao.PersonSightingDao;
import com.sg.dto.Person;
import com.sg.dto.PersonSighting;
import com.sg.dto.Sighting;

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
    public List<PersonSighting> retrieveAllPersonSightings(Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return personSightingDao.retrieveAllPersonSightings(limit, offset);
    }

    @Override
    public List<PersonSighting> retrieveAllPersonSightingsByPerson(Person person, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return personSightingDao.retrieveAllPersonSightingsByPerson(person, limit, offset);
    }

    @Override
    public List<PersonSighting> retrieveAllPersonSightingsBySighting(Sighting sighting, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return personSightingDao.retrieveAllPersonSightingsBySighting(sighting, limit, offset);
    }
}
