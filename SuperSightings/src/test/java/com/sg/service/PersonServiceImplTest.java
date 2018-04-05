package com.sg.service;

import com.sg.dto.*;
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
@Rollback
@Transactional
public class PersonServiceImplTest {


    @Inject
    private PersonService personService;

    @Inject
    private PowerService powerService;

    @Inject
    private PersonOrganizationService personOrganizationService;

    @Inject
    private PersonPowerService personPowerService;

    @Inject
    private PersonSightingService personSightingService;

    @Inject
    private SightingService sightingService;

    @Inject
    private OrganizationService organizationService;

    @Inject
    private LocationService locationService;


    // HELPER METHODS *********************************************************************
    private Person createTestPerson(int personIndex) {
        Person person = new Person();
        person.setType("Person" + personIndex);
        person.setName("Jaqwan" + personIndex);

        person.setDescription("Taxidermist by day. Protector of cable tv by night." + personIndex);

        return personService.create(person);
    }

    private Power createTestPower(int index){
        Power power = new Power();
        power.setName("Wealth" + index);
        return powerService.create(power);
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

    private Sighting createTestSighting(Location location){
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2018-04-04"));
        sighting.setLocation(location);
        sighting.setDescription("He was really fast.");
        return sightingService.create(sighting);
    }

    private PersonSighting createTestPersonSighting(Person person, Sighting sighting){
        PersonSighting personSighting = new PersonSighting();
        personSighting.setPerson(person);
        personSighting.setSighting(sighting);
        return personSightingService.create(personSighting);
    }

    public PersonPower createTestPersonPower(Person person, Power power){
        PersonPower personPower = new PersonPower();
        personPower.setPerson(person);
        personPower.setPower(power);
        return personPowerService.create(personPower);
    }

    // END HELPER METHODS *********************************************************************

    @Test
    public void create() {

        //Arrange
        Person person = createTestPerson(0);

        //Act
        Person createdPerson = personService.create(person);

        //Assert
        assertNotNull(person.getId());
        assertEquals("Person0", createdPerson.getType());
        assertEquals("Jaqwan0", createdPerson.getName());
        assertEquals("Taxidermist by day. Protector of cable tv by night.0", createdPerson.getDescription());
    }


    @Test
    public void read() {
        //Arrange
        Person createdPerson = createTestPerson(0);

        //Act
        Person readPerson = personService.read(createdPerson);

        //Assert
        assertEquals("Person0", readPerson.getType());
        assertEquals("Jaqwan0", readPerson.getName());
        assertEquals("Taxidermist by day. Protector of cable tv by night.0", readPerson.getDescription());

    }

    @Test
    public void update() {
        //Arrange
        Person createdPerson = createTestPerson(0);
        Person readPerson = personService.read(createdPerson);
        readPerson.setType("hero");
        readPerson.setName("Taylor");
        readPerson.setDescription("GoForCoder");

        //Act
        personService.update(readPerson);

        //Assert
        Person updatedPerson = personService.read(readPerson);
        assertEquals("hero", updatedPerson.getType());
        assertEquals("Taylor", updatedPerson.getName());
        assertEquals("GoForCoder", updatedPerson.getDescription());

    }

    @Test
    public void delete() {
        //Arrange
        Person createdPerson = createTestPerson(0);

        //Act
        personService.delete(createdPerson);

        //Assert
        Person readPerson = personService.read(createdPerson);
        assertNull(readPerson);
    }

    @Test
    public void retrieveAllPersons() {
        //Arrange
        createTestPerson(0);
        createTestPerson(1);
        createTestPerson(2);

        //Act
        List<Person> allPersons = personService.retrieveAllPersons(Integer.MAX_VALUE,0);

        //Assert
        assertEquals(3, allPersons.size());
    }

    @Test
    public void retrieveAllPersonsByOrg() {
        //Arrange
        Location location = createTestLocation();
        Organization organization = createTestOrganization(location);
        Organization organization1 = createTestOrganization(location);

        Person person = createTestPerson(0);
        Person person1 = createTestPerson(1);
        Person person2 = createTestPerson(2);
        Person person3 = createTestPerson(3);

        createTestPersonOrganization(person, organization);
        createTestPersonOrganization(person1, organization1);
        createTestPersonOrganization(person1, organization);
        createTestPersonOrganization(person2, organization);
        createTestPersonOrganization(person3, organization);

        //Act
        List<Person> allPersonsByOrg = personService.retrieveAllPersonsByOrg(organization, Integer.MAX_VALUE, 0);
        List<Person> allPersonsByOrg1 = personService.retrieveAllPersonsByOrg(organization1, Integer.MAX_VALUE, 0);

        //Assert
        assertEquals(4, allPersonsByOrg.size());
        assertEquals(1, allPersonsByOrg1.size());
    }

    @Test
    public void retrieveAllPersonsByPower() {
        //Arrange
        Person person = createTestPerson(0);
        Person person1 = createTestPerson(1);
        Person person2 = createTestPerson(2);
        Person person3 = createTestPerson(3);

        Power power = createTestPower(0);
        Power power1 = createTestPower(1);

        createTestPersonPower(person, power);
        createTestPersonPower(person, power1);
        createTestPersonPower(person1, power);
        createTestPersonPower(person2, power1);
        createTestPersonPower(person3, power1);

        //Act
        List<Person> allPersonsByPower = personService.retrieveAllPersonsByPower(power, Integer.MAX_VALUE, 0);
        List<Person> allPersonsByPower1 = personService.retrieveAllPersonsByPower(power1, Integer.MAX_VALUE, 0);

        //Assert
        assertEquals(2, allPersonsByPower.size());
        assertEquals(3, allPersonsByPower1.size());
    }

    @Test
    public void retrieveAllPersonsBySighting() {
        //Arrange
        Location location = createTestLocation();
        Location location1 = createTestLocation();

        Person person = createTestPerson(0);
        Person person1 = createTestPerson(1);
        Person person2 = createTestPerson(2);
        Person person3 = createTestPerson(3);

        Sighting sighting = createTestSighting(location);
        Sighting sighting1 = createTestSighting(location1);

        createTestPersonSighting(person, sighting);
        createTestPersonSighting(person, sighting1);
        createTestPersonSighting(person1, sighting);
        createTestPersonSighting(person2, sighting);
        createTestPersonSighting(person3, sighting1);

        //Act
        List<Person> allPersonsBySighting = personService.retrieveAllPersonsBySighting(sighting, Integer.MAX_VALUE, 0);
        List<Person> allPersonsBySighting1 = personService.retrieveAllPersonsBySighting(sighting1, Integer.MAX_VALUE, 0);

        //Assert
        assertEquals(3, allPersonsBySighting.size());
        assertEquals(2, allPersonsBySighting1.size());
    }

    @Test
    public void retrieveAllPersonsByLocation() {
        //Arrange
        Location location = createTestLocation();
        Location location1 = createTestLocation();
        Location location2 = createTestLocation();

        Person person = createTestPerson(0);
        Person person1 = createTestPerson(1);
        Person person2 = createTestPerson(2);
        Person person3 = createTestPerson(3);

        Sighting sighting = createTestSighting(location);
        Sighting sighting1 = createTestSighting(location1);
        Sighting sighting2 = createTestSighting(location2);
        Sighting sighting3 = createTestSighting(location);

        createTestPersonSighting(person, sighting);
        createTestPersonSighting(person, sighting3);
        createTestPersonSighting(person, sighting1);
        createTestPersonSighting(person1, sighting);
        createTestPersonSighting(person2, sighting2);
        createTestPersonSighting(person3, sighting1);

        //Act
        List<Person> allPersonsByLocation = personService.retrieveAllPersonsByLocation(location, Integer.MAX_VALUE, 0);
        List<Person> allPersonsByLocation1 = personService.retrieveAllPersonsByLocation(location1, Integer.MAX_VALUE, 0);
        List<Person> allPersonsByLocation2 = personService.retrieveAllPersonsByLocation(location2, Integer.MAX_VALUE, 0);

        //Assert
        assertEquals(2, allPersonsByLocation.size());
        assertEquals(2, allPersonsByLocation1.size());
        assertEquals(1, allPersonsByLocation2.size());

    }


}