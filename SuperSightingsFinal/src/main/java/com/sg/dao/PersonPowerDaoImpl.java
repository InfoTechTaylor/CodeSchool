package com.sg.dao;

import com.sg.dto.Person;
import com.sg.dto.PersonPower;
import com.sg.dto.Power;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonPowerDaoImpl implements PersonPowerDao{

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PersonPowerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final String CREATE_QUERY = "insert into person_power (person_id, power_id) " +
            "values (?, ?)";

    private static final String READ_QUERY = "select * from person_power where id = ?";

    private static final String UPDATE_QUERY = "update person_power set person_id = ?, power_id = ? WHERE id = ?";

    private static final String DELETE_QUERY = "delete from person_power where id = ?";

    private static final String RETRIEVE_ALL_QUERY
            = "select * from person_power limit ? offset ?";

    private static final String RETRIEVE_ALL_BY_PERSON_QUERY
            = "select * from person_power where person_id = ? limit ? offset ?";

    private static final String RETRIEVE_ALL_BY_POWER_QUERY
            = "select * from person_power where power_id = ? limit ? offset ?";

    @Override
    @Transactional
    public PersonPower create(PersonPower personPower) {

        Long personId = null;
        Long powerId = null;

        if(personPower.getPerson() != null){
            personId = personPower.getPerson().getId();
        }

        if(personPower.getPower() != null){
            powerId = personPower.getPower().getId();
        }

        jdbcTemplate.update(CREATE_QUERY,
                personId,
                powerId);

        long newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        personPower.setId(newId);

        return personPower;
    }

    @Override
    public PersonPower read(PersonPower personPower) {

        try{
            return jdbcTemplate.queryForObject(READ_QUERY,
                    new PersonPowerMapper(),
                    personPower.getId());
        } catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    @Override
    public void update(PersonPower personPower) {

        Long personId = null;
        Long powerId = null;

        if(personPower.getPerson() != null){
            personId = personPower.getPerson().getId();
        }

        if(personPower.getPower() != null){
            powerId = personPower.getPower().getId();
        }

        jdbcTemplate.update(UPDATE_QUERY,
                personId,
                powerId,
                personPower.getId());
    }

    @Override
    public void delete(PersonPower personPower) {
        jdbcTemplate.update(DELETE_QUERY,
                personPower.getId());
    }

    @Override
    public List<PersonPower> retrieveAllPersonPowers(Integer limit, Integer offset) {
        return jdbcTemplate.query(RETRIEVE_ALL_QUERY,
                new PersonPowerMapper(),
                limit,
                offset);
    }

    @Override
    public List<PersonPower> retrieveAllPersonPowerByPerson(Person person, Integer limit, Integer offset) {
        return jdbcTemplate.query(RETRIEVE_ALL_BY_PERSON_QUERY,
                new PersonPowerMapper(),
                person.getId(),
                limit,
                offset);
    }

    @Override
    public List<PersonPower> retrieveAllPersonPowerByPower(Power power, Integer limit, Integer offset) {
        return jdbcTemplate.query(RETRIEVE_ALL_BY_POWER_QUERY,
                new PersonPowerMapper(),
                power.getId(),
                limit,
                offset);
    }


    private static final class PersonPowerMapper implements RowMapper<PersonPower> {

        @Override
        public PersonPower mapRow(ResultSet resultSet, int i) throws SQLException {
            PersonPower personPower = new PersonPower();

            Long personId = resultSet.getLong("person_id");

            if(personId != null) {
                Person person = new Person();
                person.setId(personId);
                personPower.setPerson(person);
            }

            Long powerId = resultSet.getLong("power_id");
            if(powerId != null) {
                Power power = new Power();
                power.setId(powerId);
                personPower.setPower(power);
            }

            personPower.setId(resultSet.getLong("id"));
            return personPower;
        }
    }
}
