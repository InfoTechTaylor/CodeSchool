package com.sg.dao;

import com.sg.dto.Organization;
import com.sg.dto.Person;

import java.util.List;

public interface OrganizationDao {

    Organization create(Organization organization);

    Organization read(Organization organization);

    void update(Organization organization);

    void delete(Organization organization);

    List<Organization> retrieveAllOrganizations(Integer limit, Integer offset);

    List<Organization> retrieveAllOrganizationsByPerson(Person person, Integer limit, Integer offset);

}
