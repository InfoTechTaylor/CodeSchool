package com.sg.service;

import com.sg.dto.Location;
import com.sg.dto.Person;

import java.util.List;

public interface LocationService {

    Location create(Location location);

    Location read(Location location);

    void update(Location location);

    void delete(Location location);

    List<Location> retrieveAllLocations(Integer limit, Integer offset);

    List<Location> retrieveAllLocationsByPerson(Person person, Integer limit, Integer offset);

}
