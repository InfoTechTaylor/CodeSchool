package com.sg.service;


import com.sg.dto.Location;
import com.sg.dto.Person;
import com.sg.dto.Sighting;

import java.time.LocalDate;
import java.util.List;

public class SightingServiceImpl implements SightingService {


    @Override
    public Sighting create(Sighting sighting) {
        return null;
    }

    @Override
    public Sighting read(Sighting sighting) {
        return null;
    }

    @Override
    public void update(Sighting sighting) {

    }

    @Override
    public void delete(Sighting sighting) {

    }

    @Override
    public List<Sighting> retrieveAllSightings(int i, int i1) {
        return null;
    }

    @Override
    public List<Sighting> retrieveAllSightingsByPerson(Person person, int i, int i1) {
        return null;
    }

    @Override
    public List<Sighting> retrieveAllSightingsByLocation(Location location, int i, int i1) {
        return null;
    }

    @Override
    public List<Sighting> retrieveAllSightingsByDate(LocalDate localDate, int i, int i1) {
        return null;
    }
}
