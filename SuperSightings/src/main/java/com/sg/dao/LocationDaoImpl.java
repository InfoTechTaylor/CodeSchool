package com.sg.dao;


import com.sg.dto.Location;
import com.sg.dto.Person;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocationDaoImpl implements LocationDao{

    private JdbcTemplate jdbcTemplate;

    @Inject
    public LocationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String CREATE_QUERY
            = "INSERT INTO Location (latitude, longitude, name, description, street, city, state, zip, country) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String READ_QUERY =
            "select * from location where id = ?";

    private static final String READ_ALL_QUERY =
            "select * from location limit ? offset ?";

    private static final String DELETE_QUERY =
            "delete from location where id = ?";

    private static final String UPDATE_QUERY =
            "update location set latitude = ?, longitude = ?, name = ?, description = ?, street = ?, " +
                    "city = ?, state = ?, zip = ?, country = ? " +
                    "where id = ?";

    private static final String READ_ALL_QUERY_BY_PERSON =
            "select DISTINCT l.id, l.latitude, l.longitude, l.name, l.description, l.street, l.city, l.state, l.zip, l.country " +
                    "from location l " +
                    "inner join sighting s on s.location_id = l.id " +
                    "inner join person_sighting ps on ps.sighting_id = s.id " +
                    "inner join person p on p.id = ps.person_id " +
                    "where p.id = ? limit ? offset ?";


    @Override
    @Transactional
    public Location create(Location location) {

        jdbcTemplate.update(CREATE_QUERY,
                location.getLatitude(),
                location.getLongitude(),
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getCountry());

        long newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",
                Long.class);

        location.setId(newId);

        return location;
    }

    @Override
    public Location read(Location location) {
        try {
            return jdbcTemplate.queryForObject(READ_QUERY, new LocationMapper(), location.getId());

        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    @Override
    public void update(Location location) {

        jdbcTemplate.update(UPDATE_QUERY,
                location.getLatitude(),
                location.getLongitude(),
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getCountry(),
                location.getId());

    }

    @Override
    public void delete(Location location) {

        jdbcTemplate.update(DELETE_QUERY, location.getId());

    }

    @Override
    public List<Location> retrieveAllLocations(int i, int i1) {

        return jdbcTemplate.query(READ_ALL_QUERY, new LocationMapper(), i, i1);

    }

    @Override
    public List<Location> retrieveAllLocationsByPerson(Person person, int i, int i1) {

        return jdbcTemplate.query(READ_ALL_QUERY_BY_PERSON, new LocationMapper(), person.getId(), i, i1);

    }


    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet resultSet, int i) throws SQLException {
            Location location = new Location();

            location.setId(resultSet.getLong("id"));
            location.setLatitude(resultSet.getDouble("latitude"));
            location.setLongitude(resultSet.getDouble("longitude"));
            location.setName(resultSet.getString("name"));
            location.setDescription(resultSet.getString("description"));
            location.setStreet(resultSet.getString("street"));
            location.setCity(resultSet.getString("city"));
            location.setState(resultSet.getString("state"));
            location.setZip(resultSet.getString("zip"));
            location.setCountry(resultSet.getString("country"));
            return location;
        }
    }
}
