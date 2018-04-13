package com.sg.dao;

import com.sg.dto.Location;
import com.sg.dto.Person;
import com.sg.dto.Sighting;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

public class SightingDaoImpl implements SightingDao {

    JdbcTemplate jdbcTemplate;
    @Inject
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String CREATE_QUERY
            = "insert into sighting (location_id, sighting_date, description) Values (?,?,?)";

    private static final String READ_QUERY
            = "select * from sighting where id = ?";

    private static final String DELETE_QUERY
            = "delete from sighting where id = ?";

    private static final String UPDATE_QUERY
            = "update sighting set location_id = ?, sighting_date = ?, description = ? where id = ?";

    private static final String READ_ALL_QUERY
            = "select * from sighting order by sighting_date desc limit ? offset ?";

    private static final String RETRIEVE_SIGHTINGS_BY_PERSON = "select * from sighting s " +
            "inner join person_sighting ps on ps.sighting_id = s.id " +
            "inner join person p on ps.person_id = p.id where p.id = ? limit ? offset ?";

    private static final String RETRIEVE_SIGHTINGS_BY_LOCATION = "select * from sighting s " +
            "inner join location l on l.id = s.location_id " +
            "where l.id = ? limit ? offset ?";

    private static final String RETRIEVE_SIGHTINGS_BY_DATE = "select * from sighting s " +
            "where sighting_date = ? limit ? offset ?";

    @Override
    @Transactional
    public Sighting create(Sighting sighting) {
        Date date = Date.valueOf(sighting.getSightingDate());
        jdbcTemplate.update(CREATE_QUERY,
                sighting.getLocation().getId(),
                date,
                sighting.getDescription());
        Long newID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);
        sighting.setId(newID);
        return sighting;
    }

    @Override
    public Sighting read(Sighting sighting) {
        try {
            return jdbcTemplate.queryForObject(READ_QUERY, new SightingMapper(), sighting.getId());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Sighting sighting) {
        Date date = Date.valueOf(sighting.getSightingDate());
        jdbcTemplate.update(UPDATE_QUERY,
                sighting.getLocation().getId(),
                date,
                sighting.getDescription(),
                sighting.getId());
    }

    @Override
    public void delete(Sighting sighting) {
        jdbcTemplate.update(DELETE_QUERY, sighting.getId());
    }

    @Override
    public List<Sighting> retrieveAllSightings(Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return jdbcTemplate.query(READ_ALL_QUERY, new SightingMapper(),
                limit,
                offset);
    }

    @Override
    public List<Sighting> retrieveAllSightingsByPerson(Person person, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return jdbcTemplate.query(RETRIEVE_SIGHTINGS_BY_PERSON,
                new SightingMapper(),
                person.getId(),
                limit,
                offset);
    }
    @Override
    public List<Sighting> retrieveAllSightingsByLocation(Location location, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return jdbcTemplate.query(RETRIEVE_SIGHTINGS_BY_LOCATION,
                new SightingMapper(),
                location.getId(),
                limit,
                offset);
    }
    @Override
    public List<Sighting> retrieveAllSightingsByDate(LocalDate localDate, Integer limit, Integer offset) {
        Date date = Date.valueOf(localDate);
        return jdbcTemplate.query(RETRIEVE_SIGHTINGS_BY_DATE,
                new SightingMapper(),
                date,
                limit,
                offset);
    }

    private static final class SightingMapper implements RowMapper<Sighting> {
        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingDate(rs.getTimestamp("sighting_date").toLocalDateTime().toLocalDate());
            sighting.setDescription(rs.getString("description"));
            sighting.setId(rs.getLong("id"));
            Long locationID = rs.getLong("location_id");
            Location location = new Location();
            location.setId(locationID);
            sighting.setLocation(location);
            return sighting;
        }
    }
}
