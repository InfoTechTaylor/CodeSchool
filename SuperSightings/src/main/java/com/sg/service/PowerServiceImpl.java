package com.sg.service;


import com.sg.dao.PowerDao;
import com.sg.dto.Person;
import com.sg.dto.Power;

import javax.inject.Inject;
import java.util.List;

public class PowerServiceImpl implements PowerService {

    PowerDao powerServiceDao;

    @Inject
    public PowerServiceImpl(PowerDao powerServiceDao) {
        this.powerServiceDao = powerServiceDao;
    }

    @Override
    public Power create(Power power) {
        return powerServiceDao.create(power);
    }

    @Override
    public Power read(Power power) {
        return powerServiceDao.read(power);
    }

    @Override
    public void update(Power power) {
        powerServiceDao.update(power);
    }

    @Override
    public void delete(Power power) {
        powerServiceDao.delete(power);
    }

    @Override
    public List<Power> retrieveAllPowers(Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return powerServiceDao.retrieveAllPowers(limit, offset);
    }

    @Override
    public List<Power> retrieveAllPowersByPerson(Person person, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return powerServiceDao.retrieveAllPowersByPerson(person, limit, offset);
    }
}
