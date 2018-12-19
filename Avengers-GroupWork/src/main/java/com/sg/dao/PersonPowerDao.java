package com.sg.dao;

import com.sg.dto.Person;
import com.sg.dto.PersonPower;
import com.sg.dto.Power;

import java.util.List;

public interface PersonPowerDao {

    PersonPower create(PersonPower personPower);

    PersonPower read(PersonPower personPower);

    void update(PersonPower personPower);

    void delete(PersonPower personPower);

    List<PersonPower> retrieveAllPersonPowers(Integer limit, Integer offset);

    List<PersonPower> retrieveAllPersonPowerByPerson(Person person, Integer limit, Integer offset);

    List<PersonPower> retrieveAllPersonPowerByPower(Power power, Integer limit, Integer offset);


}
