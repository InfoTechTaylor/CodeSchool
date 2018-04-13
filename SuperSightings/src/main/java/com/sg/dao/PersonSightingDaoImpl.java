package com.sg.dao;
import com.sg.dto.Person;
import com.sg.dto.PersonSighting;
import com.sg.dto.Sighting;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class PersonSightingDaoImpl implements PersonSightingDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PersonSightingDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String CREATE_QUERY =
            "insert into person_sighting(person_id, sighting_id) " +
                    "values (?,?)";

    private static final String UPDATE_QUERY =
            "update person_sighting set person_id = ?, sighting_id = ? " +
                    "where id = ?";

    private static final String DELETE_QUERY =
            "delete from person_sighting where id = ?";

    private static final String READ_QUERY =
            "select * from person_sighting where id = ?";

    private static final String READ_ALL_QUERY =
            "select * from person_sighting limit ? offset ?";

    private static final String READ_ALL_BY_PERSON_QUERY =
            "select * from person_sighting where person_id = ? limit ? offset ?";

    private static final String READ_ALL_BY_SIGHTING_QUERY =
            "select * from person_sighting where sighting_id = ? limit ? offset ?";



    @Override
    public PersonSighting create(PersonSighting personSighting) {
        Long person_id = null;
        Long sighting_id = null;
        if(personSighting.getPerson() != null){
            person_id = personSighting.getPerson().getId();
        }
        if(personSighting.getSighting() != null){
            sighting_id = personSighting.getSighting().getId();
        }
        jdbcTemplate.update(CREATE_QUERY,
                person_id,
                sighting_id
        );
        long id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);
        personSighting.setId(id);
        return personSighting;
    }

    @Override
    public PersonSighting read(PersonSighting personSighting) {
        try{
            return jdbcTemplate.queryForObject(READ_QUERY, new PersonSightingMapper(), personSighting.getId());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void update(PersonSighting personSighting) {
        Long person_id = null;
        Long sighting_id = null;
        if(personSighting.getPerson() != null){
            person_id = personSighting.getPerson().getId();
        }
        if(personSighting.getSighting() != null){
            sighting_id = personSighting.getSighting().getId();
        }
        jdbcTemplate.update(UPDATE_QUERY,
                person_id,
                sighting_id,
                personSighting.getId()
        );
    }

    @Override
    public void delete(PersonSighting personSighting) {
        jdbcTemplate.update(DELETE_QUERY, personSighting.getId());
    }

    @Override
    public List<PersonSighting> retrieveAllPersonSightings(Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return jdbcTemplate.query(READ_ALL_QUERY, new PersonSightingMapper(), limit, offset);
    }

    @Override
    public List<PersonSighting> retrieveAllPersonSightingsByPerson(Person person, Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return jdbcTemplate.query(READ_ALL_BY_PERSON_QUERY,
                new PersonSightingMapper(),
                person.getId(),
                limit,
                offset);
    }

    @Override
    public List<PersonSighting> retrieveAllPersonSightingsBySighting(Sighting sighting, Integer limit, Integer offset) {

        if(limit == null) limit = 5;
        if(offset == null) offset = 0;


        return jdbcTemplate.query(READ_ALL_BY_SIGHTING_QUERY,
                new PersonSightingMapper(),
                sighting.getId(),
                limit,
                offset);
    }

    private static final class PersonSightingMapper implements RowMapper<PersonSighting>{
        @Override
        public PersonSighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            PersonSighting personSighting = new PersonSighting();
            personSighting.setId(rs.getLong("id"));
            Long person_id = rs.getLong("person_id");
            if(person_id != null){
                Person person = new Person();
                person.setId(person_id);
                personSighting.setPerson(person);
            }
            Long sighting_id = rs.getLong("sighting_id");
            if(sighting_id != null){
                Sighting sighting = new Sighting();
                sighting.setId(sighting_id);
                personSighting.setSighting(sighting);
            }
            return personSighting;
        }
    }
}