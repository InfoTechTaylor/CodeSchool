package com.sg.dao;

import com.sg.dto.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PersonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final String CREATE_QUERY = "insert into person (type, name, description) " +
            "values (?, ?, ?)";

    private static final String READ_QUERY = "select * from person where id = ?";

    private static final String UPDATE_QUERY = "update person set type = ?, name = ?, description = ? WHERE id = ?";

    private static final String DELETE_QUERY = "delete from person where id = ?";

    private static final String GET_ALL_PERSONS_QUERY = "select * from person limit ? offset ?";

    private static final String GET_ALL_PERSONS_BY_ORG_QUERY = "select * from person p inner join personorganization po " +
            "on p.id = po.person_id inner join organization o on o.id = po.organization_id limit ? offset ?";

    private static final String GET_ALL_PERSONS_BY_POWER_QUERY = "select * from person p left join personpower pp on " +
            "p.id = pp.person_id inner join power pw on pp.power_id = pw.id " +
            "where pw.id = ? limit ? offest ?";

    private static final String GET_ALL_PERSONS_BY_SIGHTING_QUERY = "select * from person p inner join personsighting ps " +
            "on p.id = ps.person_id inner join sighting s on ps.sighting_id = s.id " +
            "where s.id = ? limit ? offset ?";

    private static final String GET_ALL_PERSONS_BY_LOCATION_QUERY = "select * from person p inner join personlocation pl " +
            "on p.id = pl.person_id inner join location l on l.id = pl.location_id " +
            "where l.id = ? limit ? offset ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Person create(Person person) {
        jdbcTemplate.update(CREATE_QUERY,
                person.getType(),
                person.getName(),
                person.getDescription());
        long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        person.setId(createdId);
        return person;
    }
    @Override
    public Person read(Person person) {
        try {
            return jdbcTemplate.queryForObject(READ_QUERY, new PersonMapper(), person.getId());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    @Override
    public void update(Person person) {

        jdbcTemplate.update(UPDATE_QUERY,
                person.getType(),
                person.getName(),
                person.getDescription(),
                person.getId());
    }

    @Override
    public void delete(Person person) {

        jdbcTemplate.update(DELETE_QUERY, person.getId());
    }

    @Override
    public List<Person> retrieveAllPersons(int limit, int offset) {

        List<Person> personList = jdbcTemplate.query(GET_ALL_PERSONS_QUERY,
                new PersonMapper(),
                limit,
                offset);

        return personList;
    }

    @Override
    public List<Person> retrieveAllPersonsByOrg(Organization organization, int limit, int offset) {

        List<Person> personList = jdbcTemplate.query(GET_ALL_PERSONS_BY_ORG_QUERY,
                new PersonMapper(),
                organization.getId(),
                limit,
                offset);

        return personList;
    }

    @Override
    public List<Person> retrieveAllPersonsByPower(Power power, int limit, int offset) {

        List<Person> personList = jdbcTemplate.query(GET_ALL_PERSONS_BY_POWER_QUERY,
                new PersonMapper(),
                power.getId(),
                limit,
                offset);

        return personList;
    }

    @Override
    public List<Person> retrieveAllPersonsBySighting(Sighting sighting, int limit, int offset) {

        List<Person> personList = jdbcTemplate.query(GET_ALL_PERSONS_BY_SIGHTING_QUERY,
                new PersonMapper(),
                sighting.getId(),
                limit,
                offset);

        return personList;
    }

    @Override
    public List<Person> retrieveAllPersonsByLocation(Location location, int limit, int offset) {

        List<Person> personList = jdbcTemplate.query(GET_ALL_PERSONS_BY_LOCATION_QUERY,
                new PersonMapper(),
                location.getId(),
                limit,
                offset);

        return personList;
    }




    private class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {

            Person person = new Person();

            person.setId(resultSet.getLong("id"));
            person.setType(resultSet.getString("type"));
            person.setName(resultSet.getString("name"));
            person.setDescription(resultSet.getString("description"));

            return person;
        }
    }
}
