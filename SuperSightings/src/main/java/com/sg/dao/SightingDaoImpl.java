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

    static String CREATE_QUERY
            = "insert into sighting (location_id, sighting_date, description) Values (?,?,?)";

    static String READ_QUERY
            = "select * from sighting where id = ?";

    static String DELETE_QUERY
            = "delete from sighting where id = ?";

    static String UPDATE_QUERY
            = "update sighting set location_id = ?, sighting_date = ?, description = ? where id = ?";

    static String READ_ALL_QUERY
            = "select * from sighting limit ? offset ?";

    //    static String RETRIEVE_SIGHTINGS_BY_PERSON = "select * from player p inner join player_position pp on p.id = pp.player_id " +
//            "where pp.position_id = ? LIMIT ? OFFSET ?";

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
    public List<Sighting> retrieveAllSightings(int limit, int offset) {
        return jdbcTemplate.query(READ_ALL_QUERY, new SightingMapper(),
                limit,
                offset);
    }

    @Override
    public List<Sighting> retrieveAllSightingsByPerson(Person person, int limit, int offset) {
        return null;
    }

    @Override
    public List<Sighting> retrieveAllSightingsByLocation(Location location, int i, int i1) {
        return null;
    }

    @Override
    public List<Sighting> retrieveAllSightingsByDate(LocalDate localDate, int i, int i1) {
        return null;
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
