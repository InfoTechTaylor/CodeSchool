package com.sg.dao;
import com.sg.dto.Organization;
import com.sg.dto.Person;
import com.sg.dto.PersonOrganization;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.inject.Inject;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonOrganizationDaoImpl implements PersonOrganizationDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PersonOrganizationDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }



    private static final String CREATE_QUERY =
            "insert into person_organization(person_id, organization_id, start_date, end_date) " +
                    "values (?,?,?,?)";

    private static final String UPDATE_QUERY =
            "update person_organization set person_id = ?, organization_id = ?, start_date = ?, end_date = ? " +
                    "where id = ?";

    private static final String DELETE_QUERY =
            "delete from person_organization where id = ?";

    private static final String READ_QUERY =
            "select * from person_organization where id = ?";

    private static final String READ_ALL_QUERY =
            "select * from person_organization limit ? offset ?";



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
                org_id,
                Date.valueOf(personOrganization.getStartDate()),
                Date.valueOf(personOrganization.getEndDate())
        );

        long id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);
        personOrganization.setId(id);
        return personOrganization;
    }

    @Override
    public PersonOrganization read(PersonOrganization personOrganization) {

        return jdbcTemplate.queryForObject(READ_QUERY, new PersonOrgMapper(), personOrganization.getId());
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
                Date.valueOf(personOrganization.getStartDate()),
                Date.valueOf(personOrganization.getEndDate()),
                personOrganization.getId()
        );
    }

    @Override
    public void delete(PersonOrganization personOrganization) {

        jdbcTemplate.update(DELETE_QUERY, personOrganization.getId());
    }

    @Override
    public List<PersonOrganization> retrieveAllPersonOrganization(int i, int i1) {

        return jdbcTemplate.query(READ_ALL_QUERY, new PersonOrgMapper(), i, i1);
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

            personOrganization.setStartDate(rs.getDate("start_date").toLocalDate());
            personOrganization.setEndDate(rs.getDate("end_date").toLocalDate());
            return personOrganization;
        }
    }
}
