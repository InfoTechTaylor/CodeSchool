package com.sg.dao;

import com.sg.dto.PersonOrganization;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import java.util.List;

public class PersonOrganizationDaoImpl implements PersonOrganizationDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PersonOrganizationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PersonOrganization create(PersonOrganization personOrganization) {
        return null;
    }

    @Override
    public PersonOrganization read(PersonOrganization personOrganization) {
        return null;
    }

    @Override
    public void update(PersonOrganization personOrganization) {

    }

    @Override
    public void delete(PersonOrganization personOrganization) {

    }

    @Override
    public List<PersonOrganization> retrieveAllPersonOrganization(int i, int i1) {
        return null;
    }
}
