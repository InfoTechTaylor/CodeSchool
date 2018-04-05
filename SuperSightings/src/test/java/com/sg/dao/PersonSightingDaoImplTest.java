package com.sg.dao;

import com.sg.dto.Location;
import com.sg.dto.Person;
import com.sg.dto.PersonSighting;
import com.sg.dto.Sighting;
import com.sg.service.LocationService;
import com.sg.service.PersonService;
import com.sg.service.SightingService;
import com.sg.service.SightingServiceImpl;
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
public class PersonSightingDaoImplTest {


    @Inject
    private PersonSightingDao personSightingDao;

    @Inject
    private PersonService personService;

    @Inject
    private SightingService sightingService;

    @Inject
    private LocationService locationService;

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
        return personSightingDao.create(personSighting);
    }

    @Test
    public void create() {
        // arrange
        Location location = createTestLocation();
        Person person = createTestPerson();
        Sighting sighting = createTestSighting(location);

        // act
        PersonSighting personSighting = createTestPersonSighting(person, sighting);

        // assert
        assertNotNull(personSighting.getId());
        assertNotNull(personSighting.getPerson().getId());
        assertNotNull(personSighting.getSighting().getId());
        assertEquals("He was really fast.", personSighting.getSighting().getDescription());
        assertEquals(LocalDate.parse("2018-04-04"), personSighting.getSighting().getSightingDate());
        assertEquals(location.getId(), personSighting.getSighting().getLocation().getId());
        assertEquals("Bruce Wayne", personSighting.getPerson().getName());
        assertEquals("Rich man that dresses like a bat.", personSighting.getPerson().getDescription());
        assertEquals("Person", personSighting.getPerson().getType());
    }

    @Test
    public void read() {
        // arrange
        Location location = createTestLocation();
        Person person = createTestPerson();
        Sighting sighting = createTestSighting(location);
        PersonSighting personSighting = createTestPersonSighting(person, sighting);

        // act
        PersonSighting personSightingFromDB = personSightingDao.read(personSighting);

        // assert
        assertNotNull(personSightingFromDB);
        assertNotNull(personSightingFromDB.getPerson().getId());
        assertNotNull(personSightingFromDB.getSighting().getId());

    }

    @Test
    public void update() {
        // arrange
        Location location = createTestLocation();
        Person person = createTestPerson();
        Sighting sighting = createTestSighting(location);
        PersonSighting personSighting = createTestPersonSighting(person, sighting);
        PersonSighting personSightingFromDB = personSightingDao.read(personSighting);

        Sighting newSighting = new Sighting();
        newSighting.setDescription("He beat the Joker.");
        newSighting.setLocation(location);
        newSighting.setSightingDate(LocalDate.parse("2018-04-01"));
        Sighting createdNewSighting = sightingService.create(newSighting);

        personSightingFromDB.setSighting(createdNewSighting);

        // act
        personSightingDao.update(personSightingFromDB);

        // assert
        PersonSighting updatedPersonSighting = personSightingDao.read(personSightingFromDB);
        assertEquals(createdNewSighting.getId(), updatedPersonSighting.getSighting().getId());
        assertEquals(person.getId(), updatedPersonSighting.getPerson().getId());

    }

    @Test
    public void delete() {
        // arrange
        Location location = createTestLocation();
        Person person = createTestPerson();
        Sighting sighting = createTestSighting(location);
        PersonSighting personSighting = createTestPersonSighting(person, sighting);

        // act
        personSightingDao.delete(personSighting);

        // assert
        assertNull(personSightingDao.read(personSighting));
    }

    @Test
    public void retrieveAllPersonSightings() {
        // arrange
        Location location = createTestLocation();
        Person person = createTestPerson();
        Sighting sighting = createTestSighting(location);
        createTestPersonSighting(person, sighting);

        Sighting newSighting = new Sighting();
        newSighting.setDescription("He beat the Joker.");
        newSighting.setLocation(location);
        newSighting.setSightingDate(LocalDate.parse("2018-04-01"));
        sightingService.create(newSighting);

        PersonSighting newPersonSighting = new PersonSighting();
        newPersonSighting.setSighting(newSighting);
        newPersonSighting.setPerson(person);
        personSightingDao.create(newPersonSighting);

        // act
        List<PersonSighting> personSightList = personSightingDao.retrieveAllPersonSightings(Integer.MAX_VALUE, 0);

        // assert
        assertEquals(2, personSightList.size());

    }

    @Test
    public void retrieveAllPersonSightingsPagination() {
        // arrange
        Location location = createTestLocation();
        Person person = createTestPerson();
        Sighting sighting = createTestSighting(location);
        createTestPersonSighting(person, sighting);

        Sighting newSighting = new Sighting();
        newSighting.setDescription("He beat the Joker.");
        newSighting.setLocation(location);
        newSighting.setSightingDate(LocalDate.parse("2018-04-01"));
        sightingService.create(newSighting);

        PersonSighting newPersonSighting = new PersonSighting();
        newPersonSighting.setSighting(newSighting);
        newPersonSighting.setPerson(person);
        personSightingDao.create(newPersonSighting);

        // act
        List<PersonSighting> personSightList = personSightingDao.retrieveAllPersonSightings(1, 0);
        List<PersonSighting> personSightList1 = personSightingDao.retrieveAllPersonSightings(1, 1);

        // assert
        assertEquals(1, personSightList.size());
        assertEquals(1, personSightList1.size());

    }
}