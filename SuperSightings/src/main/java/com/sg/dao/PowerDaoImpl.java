package com.sg.dao;

import com.sg.dto.Person;
import com.sg.dto.Power;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PowerDaoImpl implements PowerDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PowerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String CREATE_QUERY
            = "insert into superpower (name) values (?)";

    private static final String READ_QUERY
            = "select * from superpower where id = ?";

    private static final String UPDATE_QUERY
            = "update superpower SET name = ? where id = ?";

    private static final String DELETE_QUERY
            = "delete from superpower where id = ?";

    private static final String READ_ALL_QUERY
            = "select * from superpower limit ? offset ?";

    private static final String GET_POWERS_BY_PERSON_QUERY = "SELECT * FROM superpower s " +
            "inner join person_power pp on s.id = pp.power_id " +
            "INNER JOIN person p on p.id = pp.person_id " +
            "where p.id = ? Limit ? OFFSET ?";



    @Override
    @Transactional
    public Power create(Power power) {
        jdbcTemplate.update(CREATE_QUERY,
                power.getName()
        );
        long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        power.setId(createdId);
        return power;
    }
    @Override
    public Power read(Power power) {
        try{
            return jdbcTemplate.queryForObject(READ_QUERY, new PowerMapper(), power.getId());
        } catch (EmptyResultDataAccessException ex) {}
        return null;
    }
    @Override
    public void update(Power power) {
        jdbcTemplate.update(UPDATE_QUERY,
                power.getName(),
                power.getId()
        );
    }
    @Override
    public void delete(Power power) {
        jdbcTemplate.update(DELETE_QUERY, power.getId());
    }
    @Override
    public List<Power> retrieveAllPowers(int limit, int offset) {
        return jdbcTemplate.query(READ_ALL_QUERY, new PowerMapper(), limit, offset);
    }
    @Override
    public List<Power> retrieveAllPowersByPerson(Person person, int limit, int offset) {
        return jdbcTemplate.query(GET_POWERS_BY_PERSON_QUERY,
                new PowerMapper(),
                person.getId(),
                limit,
                offset
        );
    }
    private class PowerMapper implements RowMapper<Power> {
        @Override
        public Power mapRow(ResultSet resultSet, int i) throws SQLException {
            Power power = new Power();
            power.setId(resultSet.getLong("id"));
            power.setName(resultSet.getString("name"));
            return power;
        }
    }
}
