package com.sg.service;


import com.sg.dao.PersonPowerDao;
import com.sg.dto.Person;
import com.sg.dto.PersonPower;
import com.sg.dto.Power;

import javax.inject.Inject;
import java.util.List;

public class PersonPowerServiceImpl implements PersonPowerService{

    PersonPowerDao personPowerDao;

    @Inject
    public PersonPowerServiceImpl(PersonPowerDao personPowerDao) {
        this.personPowerDao = personPowerDao;
    }

    @Override
    public PersonPower create(PersonPower personPower) {
        return personPowerDao.create(personPower);
    }

    @Override
    public PersonPower read(PersonPower personPower) {
        return personPowerDao.read(personPower);
    }

    @Override
    public void update(PersonPower personPower) {
        personPowerDao.update(personPower);
    }

    @Override
    public void delete(PersonPower personPower) {
        personPowerDao.delete(personPower);
    }

    @Override
    public List<PersonPower> retrieveAllPersonPowers(Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return personPowerDao.retrieveAllPersonPowers(limit, offset);
    }

    @Override
    public List<PersonPower> retrieveAllPersonPowerByPerson(Person person, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return personPowerDao.retrieveAllPersonPowerByPerson(person, limit, offset);
    }

    @Override
    public List<PersonPower> retrieveAllPersonPowerByPower(Power power, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;


        return personPowerDao.retrieveAllPersonPowerByPower(power, limit, offset);
    }
}
