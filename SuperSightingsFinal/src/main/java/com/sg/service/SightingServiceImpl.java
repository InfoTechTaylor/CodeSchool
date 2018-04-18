package com.sg.service;


import com.sg.dao.SightingDao;
import com.sg.dto.Location;
import com.sg.dto.Person;
import com.sg.dto.Sighting;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class SightingServiceImpl implements SightingService {

    SightingDao sightingDao;

    @Inject
    public SightingServiceImpl(SightingDao sightingDao) {
        this.sightingDao = sightingDao;
    }

    @Override
    public Sighting create(Sighting sighting) {
        return sightingDao.create(sighting);
    }

    @Override
    public Sighting read(Sighting sighting) {
        return sightingDao.read(sighting);
    }

    @Override
    public void update(Sighting sighting) {
        sightingDao.update(sighting);
    }

    @Override
    public void delete(Sighting sighting) {
        sightingDao.delete(sighting);
    }

    @Override
    public List<Sighting> retrieveAllSightings(Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return sightingDao.retrieveAllSightings(limit, offset);
    }

    @Override
    public List<Sighting> retrieveAllSightingsByPerson(Person person, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return sightingDao.retrieveAllSightingsByPerson(person, limit, offset);
    }

    @Override
    public List<Sighting> retrieveAllSightingsByLocation(Location location, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return sightingDao.retrieveAllSightingsByLocation(location, limit, offset);
    }

    @Override
    public List<Sighting> retrieveAllSightingsByDate(LocalDate localDate, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return sightingDao.retrieveAllSightingsByDate(localDate, limit, offset);
    }
}
