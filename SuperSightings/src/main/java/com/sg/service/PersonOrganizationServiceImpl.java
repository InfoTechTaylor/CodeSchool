package com.sg.service;


import com.sg.dao.PersonOrganizationDao;
import com.sg.dto.Organization;
import com.sg.dto.Person;
import com.sg.dto.PersonOrganization;

import javax.inject.Inject;
import java.util.List;

public class PersonOrganizationServiceImpl implements PersonOrganizationService {

    PersonOrganizationDao personOrganizationDao;

    @Inject
    public PersonOrganizationServiceImpl(PersonOrganizationDao personOrganizationDao) {
        this.personOrganizationDao = personOrganizationDao;
    }

    @Override
    public PersonOrganization create(PersonOrganization personOrganization) {
        return personOrganizationDao.create(personOrganization);
    }

    @Override
    public PersonOrganization read(PersonOrganization personOrganization) {
        return personOrganizationDao.read(personOrganization);
    }

    @Override
    public void update(PersonOrganization personOrganization) {
        personOrganizationDao.update(personOrganization);
    }

    @Override
    public void delete(PersonOrganization personOrganization) {
        personOrganizationDao.delete(personOrganization);
    }

    @Override
    public List<PersonOrganization> retrieveAllPersonOrganization(Integer limit, Integer offset) {
        return personOrganizationDao.retrieveAllPersonOrganization(limit, offset);
    }

    @Override
    public List<PersonOrganization> retrieveAllPersonOrganizationByPerson(Person person, Integer limit, Integer offset) {
        return personOrganizationDao.retrieveAllPersonOrganizationByPerson(person, limit, offset);
    }

    @Override
    public List<PersonOrganization> retrieveAllPersonOrganizationByOrg(Organization organization, Integer limit, Integer offset) {
        return personOrganizationDao.retrieveAllPersonOrganizationByOrg(organization, limit, offset);
    }
}
