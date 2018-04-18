package com.sg.dao;

import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.dto.Person;
import com.sg.dto.PersonOrganization;
import com.sg.service.LocationService;
import com.sg.service.OrganizationService;
import com.sg.service.PersonService;
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
public class PersonOrganizationDaoImplTest {

    @Inject
    private PersonOrganizationDao personOrganizationDao;

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
        return personOrganizationDao.create(personOrg);
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
        PersonOrganization personOrgFromDB = personOrganizationDao.read(personOrg);

        // assert
        assertNotNull(personOrgFromDB);
        assertNotNull(personOrg.getPerson());
        assertNotNull(personOrg.getOrganization());
        assertEquals(personOrg.getPerson().getId(), personOrgFromDB.getPerson().getId());
        assertEquals(personOrg.getOrganization().getId(), personOrgFromDB.getOrganization().getId());

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
        personOrganizationDao.update(personOrg);

        // assert
        PersonOrganization updatedPerOrg = personOrganizationDao.read(personOrg);
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
        personOrganizationDao.delete(personOrg);

        // assert
        assertNull(personOrganizationDao.read(personOrg));
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


        personOrganizationDao.create(newPersonOrg); // create second PersonOrganization object

        // act
        List<PersonOrganization> allPersonOrgs = personOrganizationDao.retrieveAllPersonOrganization(Integer.MAX_VALUE, Integer.valueOf(0));

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

        personOrganizationDao.create(newPersonOrg); // create second PersonOrganization object

        // act
        List<PersonOrganization> allPersonOrgs = personOrganizationDao.retrieveAllPersonOrganization(1, 0);
        List<PersonOrganization> allPersonOrgs1 = personOrganizationDao.retrieveAllPersonOrganization(1, 1);

        // assert
        assertEquals(1, allPersonOrgs.size());
        assertEquals(1, allPersonOrgs1.size());


    }

    @Test
    public void retrieveAllPersonOrganizationByPerson() {
        //Arrange
        Person person = createTestPerson();
        Person person2 = createTestPerson();
        Location location = createTestLocation();
        Organization org = createTestOrganization(location);
        createTestPersonOrganization(person, org);
        //association 2
        Organization org2 = createTestOrganization(location);
        createTestPersonOrganization(person, org2);
        //association 3
        Organization org3 = createTestOrganization(location);
        createTestPersonOrganization(person2, org3);

        //Act
        List<PersonOrganization> person1Association = personOrganizationDao.retrieveAllPersonOrganizationByPerson(person, Integer.MAX_VALUE, 0);
        List<PersonOrganization> person2Association = personOrganizationDao.retrieveAllPersonOrganizationByPerson(person2, Integer.MAX_VALUE, 0);

        //Assert
        assertEquals(2, person1Association.size());
        assertEquals(1, person2Association.size());

    }

    @Test
    public void retrieveAllPersonOrganizationByOrg() {
        //Arrange
        Person person1 = createTestPerson();
        Person person2 = createTestPerson();
        Location location = createTestLocation();

        Organization org1 = createTestOrganization(location);
        createTestPersonOrganization(person1, org1);
        createTestPersonOrganization(person2, org1);

        Organization org2 = createTestOrganization(location);
        createTestPersonOrganization(person1, org2);

        //Act
        List<PersonOrganization> org1Association = personOrganizationDao.retrieveAllPersonOrganizationByOrg(org1, Integer.MAX_VALUE, 0);
        List<PersonOrganization> org2Association = personOrganizationDao.retrieveAllPersonOrganizationByOrg(org2, Integer.MAX_VALUE, 0);

        //Assert
        assertEquals(2, org1Association.size());
        assertEquals(1, org2Association.size());
    }
}