package com.sg.service;
import com.sg.dto.Location;
import com.sg.dto.Sighting;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import java.time.LocalDate;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class SightingServiceImplTest {

    @Inject
    private SightingService sightingService;

    @Inject
    private LocationService locationService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        // arrange & act
        Sighting createdSighting = createTestSighting();

        //assert
        assertNotNull(createdSighting.getId());
        assertNotNull(createdSighting.getLocation().getId());
        assertEquals(LocalDate.parse("2018-03-02"), createdSighting.getSightingDate());
        assertEquals("It was really fast!", createdSighting.getDescription());
    }

    @Test
    public void read() {
        //arrange
        Sighting createdSighting = createTestSighting();
        //act
        Sighting readSighting = sightingService.read(createdSighting);
        //assert
        assertNotNull(readSighting.getId());
        assertNotNull(createdSighting.getLocation().getId());
        assertEquals(LocalDate.parse("2018-03-02"), readSighting.getSightingDate());
        assertEquals("It was really fast!", readSighting.getDescription());
    }

    @Test
    public void update() {
        //arrange
        Sighting createdSighting = createTestSighting();

        Sighting readSighting = sightingService.read(createdSighting);

        readSighting.setSightingDate(LocalDate.parse("2018-04-03"));
        readSighting.setDescription("Not as fast as the last one");

        // act
        sightingService.update(readSighting);

        //assert
        Sighting updatedSighting = sightingService.read(readSighting);
        assertEquals(createdSighting.getLocation().getId(), updatedSighting.getLocation().getId());
        assertEquals(LocalDate.parse("2018-04-03"), updatedSighting.getSightingDate());
        assertEquals("Not as fast as the last one", updatedSighting.getDescription());

    }

    @Test
    public void delete() {
        //arrange
        Sighting createdSighting = createTestSighting();

        //act
        sightingService.delete(createdSighting);

        //assert
        Sighting readsighting = sightingService.read(createdSighting);
        assertNull(readsighting);

    }

    @Test
    public void retrieveAllSightings() {
        //arrange
        createTestSighting();
        createTestSighting();

        //act & assert
        assertEquals(2, sightingService.retrieveAllSightings(Integer.MAX_VALUE, 0).size());
    }

    @Test
    public void retrieveAllSightingsByPerson() {
    }

    private Sighting createTestSighting() {
        Sighting sighting = new Sighting();
        Location newLocation = createTestLocation();
        sighting.setLocation(newLocation);
        sighting.setSightingDate(LocalDate.parse("2018-03-02"));
        sighting.setDescription("It was really fast!");
        return sightingService.create(sighting);
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
}