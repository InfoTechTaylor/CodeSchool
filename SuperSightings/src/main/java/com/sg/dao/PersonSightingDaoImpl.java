package com.sg.dao;

import com.sg.dto.PersonSighting;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import java.util.List;

public class PersonSightingDaoImpl implements PersonSightingDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PersonSightingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PersonSighting create(PersonSighting personSighting) {
        return null;
    }

    @Override
    public PersonSighting read(PersonSighting personSighting) {
        return null;
    }

    @Override
    public void update(PersonSighting personSighting) {

    }

    @Override
    public void delete(PersonSighting personSighting) {

    }

    @Override
    public List<PersonSighting> retrieveAllPersonSightings(int i, int i1) {
        return null;
    }
}
