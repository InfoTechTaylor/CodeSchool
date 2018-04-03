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
    public List<Person> retrieveAllPersons(int limit, int offset) {
        return personDao.retrieveAllPersons(limit, offset);
    }

    @Override
    public List<Person> retrieveAllPersonsByOrg(Organization organization, int limit, int offset) {
        return personDao.retrieveAllPersonsByOrg(organization, limit, offset);
    }

    @Override
    public List<Person> retrieveAllPersonsByPower(Power power, int limit, int offset) {
        return personDao.retrieveAllPersonsByPower(power, limit, offset);
    }

    @Override
    public List<Person> retrieveAllPersonsBySighting(Sighting sighting, int limit, int offset) {
        return personDao.retrieveAllPersonsBySighting(sighting, limit, offset);
    }

    @Override
    public List<Person> retrieveAllPersonsByLocation(Location location, int limit, int offset) {
        return personDao.retrieveAllPersonsByLocation(location, limit, offset);
    }
}
