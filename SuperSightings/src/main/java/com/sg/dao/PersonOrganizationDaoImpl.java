package com.sg.dao;
import com.sg.dto.Organization;
import com.sg.dto.Person;
import com.sg.dto.PersonOrganization;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.inject.Inject;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PersonOrganizationDaoImpl implements PersonOrganizationDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PersonOrganizationDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }



    private static final String CREATE_QUERY =
            "insert into person_organization(person_id, organization_id) " +
                    "values (?,?)";

    private static final String UPDATE_QUERY =
            "update person_organization set person_id = ?, organization_id = ? " +
                    "where id = ?";

    private static final String DELETE_QUERY =
            "delete from person_organization where id = ?";

    private static final String READ_QUERY =
            "select * from person_organization where id = ?";

    private static final String READ_ALL_QUERY =
            "select * from person_organization limit ? offset ?";

    private static String READ_ALL_BY_PERSON_QUERY =
            "select * from person_organization where person_id = ? limit ? offset ?";

    private static String READ_ALL_BY_ORG_QUERY =
            "select * from person_organization where organization_id = ? limit ? offset ?";



    @Override
    public PersonOrganization create(PersonOrganization personOrganization) {

        Long person_id = null;
        Long org_id = null;

        if(personOrganization.getPerson() != null){
            person_id = personOrganization.getPerson().getId();
        }

        if(personOrganization.getOrganization() != null){
            org_id = personOrganization.getOrganization().getId();
        }

        jdbcTemplate.update(CREATE_QUERY,
                person_id,
                org_id);

        long id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);
        personOrganization.setId(id);
        return personOrganization;
    }

    @Override
    public PersonOrganization read(PersonOrganization personOrganization) {
        try {
            return jdbcTemplate.queryForObject(READ_QUERY, new PersonOrgMapper(), personOrganization.getId());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void update(PersonOrganization personOrganization) {
        Long person_id = null;
        Long org_id = null;

        if(personOrganization.getPerson() != null){
            person_id = personOrganization.getPerson().getId();
        }

        if(personOrganization.getOrganization() != null){
            org_id = personOrganization.getOrganization().getId();
        }

        jdbcTemplate.update(UPDATE_QUERY,
                person_id,
                org_id,
                personOrganization.getId()
        );
    }

    @Override
    public void delete(PersonOrganization personOrganization) {

        jdbcTemplate.update(DELETE_QUERY, personOrganization.getId());
    }

    @Override
    public List<PersonOrganization> retrieveAllPersonOrganization(Integer limit, Integer offset) {

        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return jdbcTemplate.query(READ_ALL_QUERY, new PersonOrgMapper(), limit, offset);
    }

    @Override
    public List<PersonOrganization> retrieveAllPersonOrganizationByPerson(Person person, Integer limit, Integer offset) {

        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return jdbcTemplate.query(READ_ALL_BY_PERSON_QUERY,
                new PersonOrgMapper(),
                person.getId(),
                limit,
                offset);
    }

    @Override
    public List<PersonOrganization> retrieveAllPersonOrganizationByOrg(Organization organization, Integer limit, Integer offset) {

        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return jdbcTemplate.query(READ_ALL_BY_ORG_QUERY,
                new PersonOrgMapper(),
                organization.getId(),
                limit,
                offset);
    }

    private static final class PersonOrgMapper implements RowMapper<PersonOrganization>{

        @Override
        public PersonOrganization mapRow(ResultSet rs, int rowNum) throws SQLException {

            PersonOrganization personOrganization = new PersonOrganization();
            personOrganization.setId(rs.getLong("id"));
            Long person_id = rs.getLong("person_id");

            if(person_id != null){
                Person person = new Person();
                person.setId(person_id);
                personOrganization.setPerson(person);
            }

            Long org_id = rs.getLong("organization_id");

            if(org_id != null){
                Organization org = new Organization();
                org.setId(org_id);
                personOrganization.setOrganization(org);
            }



            return personOrganization;
        }
    }
}
