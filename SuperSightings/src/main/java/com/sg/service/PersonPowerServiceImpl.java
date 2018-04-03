package com.sg.service;


import com.sg.dao.PersonPowerDao;
import com.sg.dto.PersonPower;

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
    public List<PersonPower> retrieveAllPersonPowers(int limit, int offset) {
        return personPowerDao.retrieveAllPersonPowers(limit, offset);
    }
}
