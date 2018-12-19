package com.sg.dao;

import com.sg.dto.*;

import java.util.List;

public interface PersonDao {

    Person create(Person person);

    Person read(Person person);

    void update(Person person);

    void delete(Person person);

    List<Person> retrieveAllPersons(Integer limit, Integer offset);

    List<Person> retrieveAllPersonsByOrg(Organization organization, Integer limit, Integer offset);

    List<Person> retrieveAllPersonsByPower(Power power, Integer limit, Integer offset);

    List<Person> retrieveAllPersonsBySighting(Sighting sighting, Integer limit, Integer offset);

    List<Person> retrieveAllPersonsByLocation(Location location, Integer limit, Integer offset);
}
