package com.sg.service;

import com.sg.dto.Organization;
import com.sg.dto.Person;
import com.sg.dto.PersonOrganization;

import java.util.List;

public interface PersonOrganizationService {

    PersonOrganization create(PersonOrganization personOrganization);

    PersonOrganization read(PersonOrganization personOrganization);

    void update(PersonOrganization personOrganization);

    void delete(PersonOrganization personOrganization);

    List<PersonOrganization> retrieveAllPersonOrganization(Integer limit, Integer offset);

    List<PersonOrganization> retrieveAllPersonOrganizationByPerson(Person person, Integer limit, Integer offset);

    List<PersonOrganization> retrieveAllPersonOrganizationByOrg(Organization organization, Integer limit, Integer offset);
}
