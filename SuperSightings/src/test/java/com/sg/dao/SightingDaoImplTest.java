package com.sg.dao;
import com.sg.dto.Location;
import com.sg.dto.Person;
import com.sg.dto.PersonSighting;
import com.sg.dto.Sighting;
import com.sg.service.LocationService;
import com.sg.service.PersonService;
import com.sg.service.PersonSightingService;
import com.sg.service.SightingService;
import org.junit.Before;
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
public class SightingDaoImplTest {

    @Inject
    private SightingDao sightingDao;

    @Inject
    private LocationService locationService;

    @Inject
    private PersonService personService;

    @Inject
    private SightingService sightingService;

    @Inject
    private PersonSightingService personSightingService;

    private Person createTestPerson(){
        Person person = new Person();
        person.setName("Bruce Wayne");
        person.setDescription("Rich man that dresses like a bat.");
        person.setType("Person");
        return personService.create(person);
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

    @Test
    public void create() {
        Location location = createTestLocation();
        // arrange & act
        Sighting createdSighting = createTestSighting(location);

        //assert
        assertNotNull(createdSighting.getId());
        assertNotNull(createdSighting.getLocation().getId());
        assertEquals(LocalDate.parse("2018-04-04"), createdSighting.getSightingDate());
        assertEquals("He was really fast.", createdSighting.getDescription());
    }

    @Test
    public void read() {
        //arrange
        Location location = createTestLocation();
        Sighting createdSighting = createTestSighting(location);
        //act
        Sighting readSighting = sightingDao.read(createdSighting);
        //assert
        assertNotNull(readSighting.getId());
        assertNotNull(createdSighting.getLocation().getId());
        assertEquals(LocalDate.parse("2018-04-04"), readSighting.getSightingDate());
        assertEquals("He was really fast.", readSighting.getDescription());
    }

    @Test
    public void update() {
        //arrange
        Location location = createTestLocation();
        Sighting createdSighting = createTestSighting(location);

        Sighting readSighting = sightingDao.read(createdSighting);

        readSighting.setSightingDate(LocalDate.parse("2018-04-03"));
        readSighting.setDescription("Not as fast as the last one");

        // act
        sightingDao.update(readSighting);

        //assert
        Sighting updatedSighting = sightingDao.read(readSighting);
        assertEquals(createdSighting.getLocation().getId(), updatedSighting.getLocation().getId());
        assertEquals(LocalDate.parse("2018-04-03"), updatedSighting.getSightingDate());
        assertEquals("Not as fast as the last one", updatedSighting.getDescription());

    }

    @Test
    public void delete() {
        //arrange
        Location location = createTestLocation();
        Sighting createdSighting = createTestSighting(location);

        //act
        sightingDao.delete(createdSighting);

        //assert
        Sighting readsighting = sightingDao.read(createdSighting);
        assertNull(readsighting);

    }

    @Test
    public void retrieveAllSightings() {
        //arrange
        Location location = createTestLocation();
        createTestSighting(location);
        createTestSighting(location);

        //act & assert
        assertEquals(2, sightingDao.retrieveAllSightings(Integer.MAX_VALUE, 0).size());
    }

    @Test
    public void retrieveAllSightingsByPerson() {
        // arrange
        Location location = createTestLocation();
        Person person = createTestPerson();
        Person person1 = createTestPerson();

        Sighting sighting = createTestSighting(location);
        Sighting sighting1 = createTestSighting(location);
        Sighting sighting2 = createTestSighting(location);
        Sighting sighting3 = createTestSighting(location);

        createTestPersonSighting(person, sighting);
        createTestPersonSighting(person, sighting1);
        createTestPersonSighting(person1, sighting2);
        createTestPersonSighting(person, sighting3);

        // act
        List<Sighting> allSightingsByPerson = sightingDao.retrieveAllSightingsByPerson(person, Integer.MAX_VALUE, 0);
        List<Sighting> allSightingsByPerson1 = sightingDao.retrieveAllSightingsByPerson(person1, Integer.MAX_VALUE, 0);

        // assert
        assertEquals(3, allSightingsByPerson.size());
        assertEquals(1, allSightingsByPerson1.size());


    }

    @Test
    public void retrieveAllSightingsByLocation() {
        // arrange
        Location location = createTestLocation();
        Location location1 = createTestLocation();

        createTestSighting(location);
        createTestSighting(location1);
        createTestSighting(location);
        createTestSighting(location);

        // act
        List<Sighting> allSightingsByLocation = sightingDao.retrieveAllSightingsByLocation(location, Integer.MAX_VALUE,Integer.valueOf(0));
        List<Sighting> allSightingsByLocation1 = sightingDao.retrieveAllSightingsByLocation(location1, Integer.MAX_VALUE, Integer.valueOf(0));

        // assert
        assertEquals(3, allSightingsByLocation.size());
        assertEquals(1, allSightingsByLocation1.size());
    }

    @Test
    public void retrieveAllSightingsByDate() {
        // arrange
        Location location = createTestLocation();
        Location location1 = createTestLocation();

        createTestSighting(location);
        createTestSighting(location1);
        Sighting sighting2 = createTestSighting(location);
        sighting2.setSightingDate(LocalDate.parse("2018-04-05"));
        sightingService.update(sighting2);
        createTestSighting(location);

        // act
        List<Sighting> allSightingsOntheFourth = sightingDao.retrieveAllSightingsByDate(LocalDate.parse("2018-04-04"), Integer.MAX_VALUE, 0);
        List<Sighting> allSightingsOnTheFifth = sightingDao.retrieveAllSightingsByDate(LocalDate.parse("2018-04-05"), Integer.MAX_VALUE,0);

        // assert
        assertEquals(3, allSightingsOntheFourth.size());
        assertEquals(1, allSightingsOnTheFifth.size());


    }

    @Test
    public void retrieveAllSightingsPagination() {
        //arrange
        Location location = createTestLocation();
        createTestSighting(location);
        createTestSighting(location);

        //act & assert
        assertEquals(1, sightingDao.retrieveAllSightings(1, 0).size());
        assertEquals(1, sightingDao.retrieveAllSightings(1, 1).size());
    }

    @Test
    public void retrieveAllSightingsByPersonPagination() {
        // arrange
        Location location = createTestLocation();
        Person person = createTestPerson();
        Person person1 = createTestPerson();

        Sighting sighting = createTestSighting(location);
        Sighting sighting1 = createTestSighting(location);
        Sighting sighting2 = createTestSighting(location);
        Sighting sighting3 = createTestSighting(location);

        createTestPersonSighting(person, sighting);
        createTestPersonSighting(person, sighting1);
        createTestPersonSighting(person1, sighting2);
        createTestPersonSighting(person, sighting3);

        // act
        List<Sighting> allSightingsByPerson = sightingDao.retrieveAllSightingsByPerson(person, 2, 0);
        List<Sighting> allSightingsByPerson1 = sightingDao.retrieveAllSightingsByPerson(person, 2, 2);

        // assert
        assertEquals(2, allSightingsByPerson.size());
        assertEquals(1, allSightingsByPerson1.size());


    }

    @Test
    public void retrieveAllSightingsByLocationPagination() {
        // arrange
        Location location = createTestLocation();
        Location location1 = createTestLocation();

        createTestSighting(location);
        createTestSighting(location1);
        createTestSighting(location);
        createTestSighting(location);

        // act
        List<Sighting> allSightingsByLocation = sightingDao.retrieveAllSightingsByLocation(location, 2,0);
        List<Sighting> allSightingsByLocation1 = sightingDao.retrieveAllSightingsByLocation(location, 2, 2);

        // assert
        assertEquals(2, allSightingsByLocation.size());
        assertEquals(1, allSightingsByLocation1.size());
    }

    @Test
    public void retrieveAllSightingsByDatePagination() {
        // arrange
        Location location = createTestLocation();
        Location location1 = createTestLocation();

        createTestSighting(location);
        createTestSighting(location1);
        Sighting sighting2 = createTestSighting(location);
        sighting2.setSightingDate(LocalDate.parse("2018-04-05"));
        sightingService.update(sighting2);
        createTestSighting(location);

        // act
        List<Sighting> allSightingsOntheFourth = sightingDao.retrieveAllSightingsByDate(LocalDate.parse("2018-04-04"), 2, 0);
        List<Sighting> allSightingsOnTheFifth = sightingDao.retrieveAllSightingsByDate(LocalDate.parse("2018-04-04"), 2,2);

        // assert
        assertEquals(2, allSightingsOntheFourth.size());
        assertEquals(1, allSightingsOnTheFifth.size());


    }


}