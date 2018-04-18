package com.sg.service;


import com.sg.dao.PersonDao;
import com.sg.dto.*;

import javax.inject.Inject;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    PersonDao personDao;

    @Inject
    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public Person create(Person person) {
        return personDao.create(person);
    }

    @Override
    public Person read(Person person) {
        return personDao.read(person);
    }

    @Override
    public void update(Person person) {
        personDao.update(person);
    }

    @Override
    public void delete(Person person) {
        personDao.delete(person);
    }

    @Override
    public List<Person> retrieveAllPersons(Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return personDao.retrieveAllPersons(limit, offset);
    }

    @Override
    public List<Person> retrieveAllPersonsByOrg(Organization organization, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return personDao.retrieveAllPersonsByOrg(organization, limit, offset);
    }

    @Override
    public List<Person> retrieveAllPersonsByPower(Power power, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return personDao.retrieveAllPersonsByPower(power, limit, offset);
    }

    @Override
    public List<Person> retrieveAllPersonsBySighting(Sighting sighting, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return personDao.retrieveAllPersonsBySighting(sighting, limit, offset);
    }

    @Override
    public List<Person> retrieveAllPersonsByLocation(Location location, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return personDao.retrieveAllPersonsByLocation(location, limit, offset);
    }
}
