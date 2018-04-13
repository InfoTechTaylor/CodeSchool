package com.sg.service;


import com.sg.dao.OrganizationDao;
import com.sg.dto.Organization;
import com.sg.dto.Person;

import javax.inject.Inject;
import java.util.List;

public class OrganizationServiceImpl implements OrganizationService {

    OrganizationDao organizationDao;

    @Inject
    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Override
    public Organization create(Organization organization) {
        return organizationDao.create(organization);
    }

    @Override
    public Organization read(Organization organization) {
        return organizationDao.read(organization);
    }

    @Override
    public void update(Organization organization) {
        organizationDao.update(organization);
    }

    @Override
    public void delete(Organization organization) {
        organizationDao.delete(organization);
    }

    @Override
    public List<Organization> retrieveAllOrganizations(Integer limit, Integer offset) {
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;

        return organizationDao.retrieveAllOrganizations(limit, offset);
    }

    @Override
    public List<Organization> retrieveAllOrganizationsByPerson(Person person, Integer limit, Integer offset) {
        return organizationDao.retrieveAllOrganizationsByPerson(person, limit, offset);
    }
}
