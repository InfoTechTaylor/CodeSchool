package com.sg.service;

import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.dto.Person;
import com.sg.dto.PersonOrganization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Transactional
@Rollback
public class OrganizationServiceImplTest {

    @Inject
    private OrganizationService organizationService;

    @Inject
    private LocationService locationService;

    @Inject
    private PersonService personService;

    @Inject
    private PersonOrganizationService personOrganizationService;

    private Location createTestLocation() {
        Location location = new Location();
        location.setLatitude(43.1979);
        location.setLongitude(70.8737);
        location.setName("Dover");
        location.setDescription("small NH city");
        location.setStreet("2 central ave");
        location.setState("NH");
        location.setCity("Dover");
        location.setZip("03820");
        location.setCountry("USA");
        Location createdLocation = locationService.create(location);
        return createdLocation;
    }

    private Person createTestPerson() {
        Person person = new Person();
        person.setType("person");
        person.setName("Batman");
        person.setDescription("Man dressed like bat");
        return personService.create(person);
    }

    private Organization createTestOrgAvengers() {
        Location location = createTestLocation();

        Organization organization = new Organization();
        organization.setName("The Avengers");
        organization.setDescription("Earth's No. 1 team.");
        organization.setLocation(location);
        organizationService.create(organization);

        return organization;
    }

    private PersonOrganization createTestPersonOrg(Organization org1, Person personFromDB) {
        PersonOrganization personOrg = new PersonOrganization();
        personOrg.setOrganization(org1);
        personOrg.setPerson(personFromDB);
        personOrg.setStartDate(LocalDate.parse("1990-01-01"));
        return personOrganizationService.create(personOrg);
    }

    private void assertEqualsOrganization(Organization org, Long locationId) {
        assertEquals("The Avengers", org.getName());
        assertEquals("Earth's No. 1 team.", org.getDescription());
        assertEquals(locationId, org.getLocation().getId());
}

    @Test
    public void create() {
        // arrange/act
        Organization org = createTestOrgAvengers();

        // assert
        assertNotNull(org.getId());
        assertEqualsOrganization(org, org.getLocation().getId());
    }




    @Test
    public void read() {
        // arrange
        Organization org = createTestOrgAvengers();

        // act
        Organization orgFromDB = organizationService.read(org);

        // assert
        assertNotNull(orgFromDB);
        assertNotNull(orgFromDB.getId());
        assertEqualsOrganization(orgFromDB, orgFromDB.getLocation().getId());
    }

    @Test
    public void update() {
        // arrange
        Location location = createTestLocation();
        Organization org = createTestOrgAvengers();
        Organization orgFromDB = organizationService.read(org);
        orgFromDB.setName("Justice League");
        orgFromDB.setDescription("The DC org.");
        orgFromDB.setLocation(location);

        // act
        organizationService.update(orgFromDB);

        // assert
        Organization updatedOrg = organizationService.read(orgFromDB);
        assertEquals("Justice League", updatedOrg.getName());
        assertEquals("The DC org.", updatedOrg.getDescription());
        assertEquals(location.getId(), updatedOrg.getLocation().getId());

    }

    @Test
    public void delete() {
        // arrange
        Organization org = createTestOrgAvengers();

        // act
        organizationService.delete(org);

        // assert
        assertNull(organizationService.read(org));
    }

    @Test
    public void retrieveAllOrganizations() {
        // arrange
        Organization org1 = createTestOrgAvengers();
        Organization org2 = createTestOrgAvengers();

        // act
        List<Organization> allOrgs = organizationService.retrieveAllOrganizations(Integer.MAX_VALUE, 0);

        // assert
        assertEquals(2, allOrgs.size());
    }

    @Test
    public void retrieveAllOrganizationsByPerson() {
        // arrange
        Organization org1 = createTestOrgAvengers();
        Organization org2 = createTestOrgAvengers();

        Person personFromDB = createTestPerson();
        Person personFromDB2 = createTestPerson();

        createTestPersonOrg(org1, personFromDB);
        createTestPersonOrg(org2, personFromDB2);
        createTestPersonOrg(org1, personFromDB2);

        // act
        List<Organization> allOrgsPerson1
                = organizationService.retrieveAllOrganizationsByPerson(personFromDB, Integer.MAX_VALUE, 0);
        List<Organization> allOrgsPerson2
                = organizationService.retrieveAllOrganizationsByPerson(personFromDB2, Integer.MAX_VALUE, 0);

        // assert
        assertEquals(1, allOrgsPerson1.size());
        assertEquals(2, allOrgsPerson2.size());
    }




}