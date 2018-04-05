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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback(true)
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PersonOrganizationServiceImplTest {

    @Inject
    private PersonOrganizationService personOrganizationService;

    @Inject
    private PersonService personService;

    @Inject
    private OrganizationService organizationService;

    @Inject
    private LocationService locationService;


    // HELPER METHODS *******************************************************************
    private Person createTestPerson(){
        Person person = new Person();
        person.setName("Bruce Wayne");
        person.setDescription("Rich man that dresses like a bat.");
        person.setType("Person");
        return personService.create(person);
    }

    private Location createTestLocation() {
        Location location = new Location();
        location.setLatitude(40.779287);
        location.setLongitude(-73.969326);
        location.setName("Central Park");
        location.setDescription("near Belvedere Castle");
        location.setStreet("79th Street");
        location.setCity("New York");
        location.setState("NY");
        location.setZip("10021");
        location.setCountry("USA");
        return locationService.create(location);
    }

    private Organization createTestOrganization(Location location){
        Organization org = new Organization();
        org.setName("The Justice League");
        org.setDescription("DC's group");
        org.setLocation(location);
        return organizationService.create(org);
    }

    private PersonOrganization createTestPersonOrganization(Person person, Organization organization){
        PersonOrganization personOrg = new PersonOrganization();
        personOrg.setPerson(person);
        personOrg.setOrganization(organization);
        personOrg.setStartDate(LocalDate.parse("2000-01-01"));
        personOrg.setEndDate(null);
        return personOrganizationService.create(personOrg);
    }

    @Test
    public void create() {
        // arrange
        Location location = createTestLocation();
        Organization org = createTestOrganization(location);
        Person person = createTestPerson();

        // act
        PersonOrganization personOrg = createTestPersonOrganization(person, org);

        // assert
        assertNotNull(personOrg.getId());
        assertNotNull(personOrg.getPerson());
        assertNotNull(personOrg.getOrganization());
        assertEquals("Bruce Wayne", personOrg.getPerson().getName());
        assertEquals("Rich man that dresses like a bat.", personOrg.getPerson().getDescription());
        assertEquals("Person", personOrg.getPerson().getType());
        assertEquals("The Justice League", personOrg.getOrganization().getName());
        assertEquals("DC's group", personOrg.getOrganization().getDescription());
        assertEquals(LocalDate.parse("2000-01-01"), personOrg.getStartDate());
        assertNull(personOrg.getEndDate());
        assertEquals("Central Park", personOrg.getOrganization().getLocation().getName());


    }

    @Test
    public void read() {
        // arrange
        Location location = createTestLocation();
        Organization org = createTestOrganization(location);
        Person person = createTestPerson();
        PersonOrganization personOrg = createTestPersonOrganization(person, org);

        // act
        PersonOrganization personOrgFromDB = personOrganizationService.read(personOrg);

        // assert
        assertNotNull(personOrgFromDB);
        assertNotNull(personOrg.getPerson());
        assertNotNull(personOrg.getOrganization());
        assertEquals(personOrg.getPerson().getId(), personOrgFromDB.getPerson().getId());
        assertEquals(personOrg.getOrganization().getId(), personOrgFromDB.getOrganization().getId());
        assertEquals(LocalDate.parse("2000-01-01"), personOrg.getStartDate());
        assertNull(personOrg.getEndDate());

    }

    @Test
    public void update() {
        // arrange
        Location location = createTestLocation();
        Organization org = createTestOrganization(location);
        Person person = createTestPerson();
        PersonOrganization personOrg = createTestPersonOrganization(person, org);

        Organization newOrg = new Organization();
        newOrg.setName("The Avengers");
        newOrg.setDescription("Earth's No. 1 Team");
        newOrg.setLocation(location);
        Organization createdOrg = organizationService.create(newOrg);
        assertNotNull(createdOrg.getId());

        personOrg.setOrganization(newOrg);

        // act
        personOrganizationService.update(personOrg);

        // assert
        PersonOrganization updatedPerOrg = personOrganizationService.read(personOrg);
        assertEquals(newOrg.getId(), updatedPerOrg.getOrganization().getId());
    }

    @Test
    public void delete() {
        // arrange
        Location location = createTestLocation();
        Organization org = createTestOrganization(location);
        Person person = createTestPerson();
        PersonOrganization personOrg = createTestPersonOrganization(person, org);

        // act
        personOrganizationService.delete(personOrg);

        // assert
        assertNull(personOrganizationService.read(personOrg));
    }

    @Test
    public void retrieveAllPersonOrganization() {
        // arrange
        Location location = createTestLocation();
        Organization org = createTestOrganization(location);
        Person person = createTestPerson();
        createTestPersonOrganization(person, org);  // create first PersonOrganization object

        // create a different org for second object
        Organization newOrg = new Organization();
        newOrg.setName("The Avengers");
        newOrg.setDescription("Earth's No. 1 Team");
        newOrg.setLocation(location);
        Organization createdOrg = organizationService.create(newOrg);
        assertNotNull(createdOrg.getId());

        PersonOrganization newPersonOrg = new PersonOrganization();
        newPersonOrg.setOrganization(newOrg);
        newPersonOrg.setPerson(person);
        newPersonOrg.setStartDate(LocalDate.parse("2001-01-01"));
        newPersonOrg.setEndDate(null);

        personOrganizationService.create(newPersonOrg); // create second PersonOrganization object

        // act
        List<PersonOrganization> allPersonOrgs = personOrganizationService.retrieveAllPersonOrganization(Integer.MAX_VALUE, 0);

        // assert
        assertEquals(2, allPersonOrgs.size());


    }

    @Test
    public void retrieveAllPersonOrganizationPagination() {
        // arrange
        Location location = createTestLocation();
        Organization org = createTestOrganization(location);
        Person person = createTestPerson();
        createTestPersonOrganization(person, org);  // create first PersonOrganization object

        // create a different org for second object
        Organization newOrg = new Organization();
        newOrg.setName("The Avengers");
        newOrg.setDescription("Earth's No. 1 Team");
        newOrg.setLocation(location);
        Organization createdOrg = organizationService.create(newOrg);
        assertNotNull(createdOrg.getId());

        PersonOrganization newPersonOrg = new PersonOrganization();
        newPersonOrg.setOrganization(newOrg);
        newPersonOrg.setPerson(person);
        newPersonOrg.setStartDate(LocalDate.parse("2001-01-01"));
        newPersonOrg.setEndDate(null);

        personOrganizationService.create(newPersonOrg); // create second PersonOrganization object

        // act
        List<PersonOrganization> allPersonOrgs = personOrganizationService.retrieveAllPersonOrganization(1, 0);
        List<PersonOrganization> allPersonOrgs1 = personOrganizationService.retrieveAllPersonOrganization(1, 1);

        // assert
        assertEquals(1, allPersonOrgs.size());
        assertEquals(1, allPersonOrgs1.size());


    }
}