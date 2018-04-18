package com.sg.dao;

import com.sg.dto.Location;
import com.sg.dto.Person;
import com.sg.dto.PersonSighting;
import com.sg.dto.Sighting;
import com.sg.service.PersonService;
import com.sg.service.PersonSightingService;
import com.sg.service.SightingService;
import com.sg.service.SightingServiceImpl;
import org.junit.After;
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
public class LocationDaoImplTest {


    @Inject
    private LocationDao locationDao;

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
        return locationDao.create(location);
    }

    private void assertTestFields(Location location) {
        assertEquals(40.779287, location.getLatitude(), 0);
        assertEquals(-73.969326, location.getLongitude(), 0);
        assertEquals("Central Park", location.getName());
        assertEquals("near Belvedere Castle", location.getDescription());
        assertEquals("79th Street", location.getStreet());
        assertEquals("New York", location.getCity());
        assertEquals("NY", location.getState());
        assertEquals("10021", location.getZip());
        assertEquals("USA", location.getCountry());
    }

    @Test
    public void create() {

        //Arrange & Act
        Location location = createTestLocation();

        //Assert
        assertNotNull(location.getId());
        assertTestFields(location);
    }

    @Test
    public void read() {
        //Arrange
        Location location = createTestLocation();

        //Act
        Location readLocation = locationDao.read(location);
        assertEquals(location.getId(), readLocation.getId());
        assertTestFields(readLocation);
    }

    @Test
    public void update() {
        //Arrange
        Location location = createTestLocation();
        Location readLocation = locationDao.read(location);
        readLocation.setLatitude(51.503157);
        readLocation.setLongitude(-0.119715);
        readLocation.setName("London Eye");
        readLocation.setDescription("The Ferris Wheel");
        readLocation.setStreet("The Queen's Walk");
        readLocation.setCity("London");
        readLocation.setState("Lambeth");
        readLocation.setZip("SE1 7PB");
        readLocation.setCountry("UK");

        //Act
        locationDao.update(readLocation);

        //Assert
        Location updatedLocation = locationDao.read(readLocation);
        assertEquals(readLocation.getId(), updatedLocation.getId());
        assertEquals(51.503157, updatedLocation.getLatitude(), 0.001);
        assertEquals(-0.119715, updatedLocation.getLongitude(), 0.001);
        assertEquals("London Eye", updatedLocation.getName());
        assertEquals("The Ferris Wheel", updatedLocation.getDescription());
        assertEquals("The Queen's Walk", updatedLocation.getStreet());
        assertEquals("London", updatedLocation.getCity());
        assertEquals("Lambeth", updatedLocation.getState());
        assertEquals("SE1 7PB", updatedLocation.getZip());
        assertEquals("UK", updatedLocation.getCountry());
    }

    @Test
    public void delete() {
        //Arrange
        Location location = createTestLocation();
        assertNotNull(location);

        //Act
        locationDao.delete(location);

        //Assert
        Location readLocation = locationDao.read(location);
        assertNull(readLocation);
    }

    @Test
    public void retrieveAllLocations() {
        // arrange
        Location location = createTestLocation();
        Location location2 = createTestLocation();

        // act
        List<Location> locationList = locationDao.retrieveAllLocations(Integer.MAX_VALUE, Integer.valueOf(0));

        // assert
        assertEquals(2, locationList.size());
    }

    @Test
    public void retrieveAllLocationsPagination() {
        // arrange
        Location location = createTestLocation();
        Location location2 = createTestLocation();

        // act
        List<Location> locationList = locationDao.retrieveAllLocations(Integer.valueOf(1), Integer.valueOf(0));
        List<Location> locationList1 = locationDao.retrieveAllLocations(Integer.valueOf(1), Integer.valueOf(1));

        // assert
        assertEquals(1, locationList.size());
        assertEquals(1, locationList1.size());
    }

    @Test
    public void retrieveAllLocationsByPerson() {
        // arrange
        Location location = createTestLocation();
        Location location2 = createTestLocation();

        Person person = createTestPerson();
        Person person2 = createTestPerson();

        Sighting sighting = createTestSighting(location);
        Sighting sighting2= createTestSighting(location);
        Sighting sighting3 = createTestSighting(location2);
        Sighting sighting4 = createTestSighting(location2);
        Sighting sighting5 = createTestSighting(location);

        createTestPersonSighting(person, sighting);
        createTestPersonSighting(person, sighting2);
        createTestPersonSighting(person2, sighting3);
        createTestPersonSighting(person2, sighting4);
        createTestPersonSighting(person2, sighting5);

        // act
        List<Location> allLocationsByPerson =
                locationDao.retrieveAllLocationsByPerson(person, Integer.MAX_VALUE, Integer.valueOf(0));
        List<Location> allLocationsByPerson2 =
                locationDao.retrieveAllLocationsByPerson(person2, Integer.MAX_VALUE, Integer.valueOf(0));

        // assert
        assertEquals(1, allLocationsByPerson.size());
        assertEquals(location.getId(), allLocationsByPerson.get(0).getId());
        assertEquals(2, allLocationsByPerson2.size());

    }

    @Test
    public void retrieveAllLocationsByPersonPagination() {
        // arrange
        Location location = createTestLocation();
        Location location2 = createTestLocation();
        Location location3 = createTestLocation();

        Person person = createTestPerson();
        Person person2 = createTestPerson();

        Sighting sighting = createTestSighting(location);
        Sighting sighting2= createTestSighting(location);
        Sighting sighting3 = createTestSighting(location);
        Sighting sighting4 = createTestSighting(location2);
        Sighting sighting5 = createTestSighting(location3);

        createTestPersonSighting(person, sighting);
        createTestPersonSighting(person, sighting2);
        createTestPersonSighting(person2, sighting3);
        createTestPersonSighting(person2, sighting4);
        createTestPersonSighting(person2, sighting5);

        // act
        List<Location> allLocationsByPerson =
                locationDao.retrieveAllLocationsByPerson(person2, Integer.valueOf(2), Integer.valueOf(0));
        List<Location> allLocationsByPerson2 =
                locationDao.retrieveAllLocationsByPerson(person2, Integer.valueOf(2), Integer.valueOf(2));

        // assert
        assertEquals(2, allLocationsByPerson.size());
        assertEquals(1, allLocationsByPerson2.size());

    }




}