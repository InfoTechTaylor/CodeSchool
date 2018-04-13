package com.sg.service;


import com.sg.dao.LocationDao;
import com.sg.dto.Location;
import com.sg.dto.Person;

import javax.inject.Inject;
import java.util.List;

public class LocationServiceImpl implements LocationService {

    LocationDao locationDao;

    @Inject
    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public Location create(Location location) {
        return locationDao.create(location);
    }

    @Override
    public Location read(Location location) {
        return locationDao.read(location);
    }

    @Override
    public void update(Location location) {
        locationDao.update(location);
    }

    @Override
    public void delete(Location location) {
        locationDao.delete(location);
    }

    @Override
    public List<Location> retrieveAllLocations(Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return locationDao.retrieveAllLocations(limit, offset);
    }

    @Override
    public List<Location> retrieveAllLocationsByPerson(Person person, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return locationDao.retrieveAllLocationsByPerson(person, limit, offset);
    }
}
