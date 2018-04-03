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
        return null;
    }

    @Override
    public Power read(Power power) {
        return null;
    }

    @Override
    public void update(Power power) {

    }

    @Override
    public void delete(Power power) {

    }

    @Override
    public List<Power> retrieveAllPowers(int i, int i1) {
        return null;
    }

    @Override
    public List<Power> retrieveAllPowersByPerson(Person person, int i, int i1) {
        return null;
    }
}
