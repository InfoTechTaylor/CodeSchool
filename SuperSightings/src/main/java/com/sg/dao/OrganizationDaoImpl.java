package com.sg.dao;

import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.dto.Person;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrganizationDaoImpl implements OrganizationDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public OrganizationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String CREATE_QUERY
            = "insert into organization (name, description, location_id) " +
            "values (?, ?, ?)";

    private final static String READ_QUERY
            = "select * from Organization where id = ?";

    private final static String UPDATE_QUERY
            = "update organization set name = ?, description = ?, location_id = ?";

    private final static String DELETE_QUERY
            = "delete from organization where id = ?";

    private final static String RETRIEVE_ALL_ORGS_QUERY = "select * from organization limit ? offset ?";

    private final static String RETRIEVE_ALL_ORGS_BY_PERSON_QUERY = "select * from organization o " +
            "inner join person_organization po ON o.id = po.organization_id " +
            "inner join person p ON p.id = po.person_id where p.id = ? limit ? offset ?";


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization create(Organization organization) {
        jdbcTemplate.update(CREATE_QUERY,
                organization.getName(),
                organization.getDescription(),
                organization.getLocation().getId());

        long newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",
                Long.class);

        organization.setId(newId);

        return organization;
    }

    @Override
    public Organization read(Organization organization) {
        try {
            return jdbcTemplate.queryForObject(READ_QUERY,
                    new OrganizationMapper(),
                    organization.getId());
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void update(Organization organization) {
        jdbcTemplate.update(UPDATE_QUERY,
                organization.getName(),
                organization.getDescription(),
                organization.getLocation().getId());
    }

    @Override
    public void delete(Organization organization) {
        jdbcTemplate.update(DELETE_QUERY,
                organization.getId());
    }

    @Override
    public List<Organization> retrieveAllOrganizations(int limit, int offset) {
        return jdbcTemplate.query(RETRIEVE_ALL_ORGS_QUERY,
                new OrganizationMapper(),
                limit,
                offset);
    }

    @Override
    public List<Organization> retrieveAllOrganizationsByPerson(Person person, int limit, int offset) {
        return jdbcTemplate.query(RETRIEVE_ALL_ORGS_BY_PERSON_QUERY,
                new OrganizationMapper(),
                person.getId(),
                limit,
                offset);
    }

    private static final class OrganizationMapper implements RowMapper<Organization>{

        @Override
        public Organization mapRow(ResultSet resultSet, int i) throws SQLException {
            Organization org = new Organization();

            org.setId(resultSet.getLong("id"));
            org.setName(resultSet.getString("name"));
            org.setDescription(resultSet.getString("description"));
            Location location = new Location();
            location.setId(resultSet.getLong("location_id"));
            org.setLocation(location);
            return org;
        }
    }
}
