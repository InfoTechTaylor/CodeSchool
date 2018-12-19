package com.sg.service;

import com.sg.dto.Person;
import com.sg.dto.PersonSighting;
import com.sg.dto.Sighting;

import java.util.List;

public interface PersonSightingService {

    PersonSighting create(PersonSighting personSighting);

    PersonSighting read(PersonSighting personSighting);

    void update(PersonSighting personSighting);

    void delete(PersonSighting personSighting);

    List<PersonSighting> retrieveAllPersonSightings(Integer limit, Integer offset);

    List<PersonSighting> retrieveAllPersonSightingsByPerson(Person person, Integer limit, Integer offset);

    List<PersonSighting> retrieveAllPersonSightingsBySighting(Sighting sighting, Integer limit, Integer offset);
}
