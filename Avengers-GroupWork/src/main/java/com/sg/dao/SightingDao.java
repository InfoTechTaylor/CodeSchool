package com.sg.dao;

import com.sg.dto.Location;
import com.sg.dto.Person;
import com.sg.dto.Sighting;

import java.time.LocalDate;
import java.util.List;

public interface SightingDao {

    Sighting create(Sighting sighting);

    Sighting read(Sighting sighting);

    void update(Sighting sighting);

    void delete(Sighting sighting);

    List<Sighting> retrieveAllSightings(Integer limit, Integer offset);

    List<Sighting> retrieveAllSightingsByPerson(Person person, Integer limit, Integer offset);

    List<Sighting> retrieveAllSightingsByLocation(Location location, Integer limit, Integer offset);

    List<Sighting> retrieveAllSightingsByDate(LocalDate date, Integer limit, Integer offset);
}
