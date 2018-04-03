package com.sg.dao;

import com.sg.dto.Location;
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

import java.util.List;

import static org.junit.Assert.*;



    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations = {"/test-applicationContext.xml"})
    @Rollback(true)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public class LocationDaoImplTest {


        @Inject
        LocationDao locationDao;

        @Before
        public void setUp() throws Exception {
        }

        @After
        public void tearDown() throws Exception {
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
            Location location = createTestLocation();
            Location location2 = createTestLocation();
            List<Location> locationList = locationDao.retrieveAllLocations(Integer.MAX_VALUE, 0);
            assertEquals(2, locationList.size());
        }

        @Test
        public void retrieveAllLocationsByPerson() {
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

    }