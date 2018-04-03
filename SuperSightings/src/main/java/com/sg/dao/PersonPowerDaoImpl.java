package com.sg.dao;

import com.sg.dto.PersonPower;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import java.util.List;

public class PersonPowerDaoImpl implements PersonPowerDao{

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PersonPowerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PersonPower create(PersonPower personPower) {
        return null;
    }

    @Override
    public PersonPower read(PersonPower personPower) {
        return null;
    }

    @Override
    public void update(PersonPower personPower) {

    }

    @Override
    public void delete(PersonPower personPower) {

    }

    @Override
    public List<PersonPower> retrieveAllPersonPowers(int i, int i1) {
        return null;
    }
}
