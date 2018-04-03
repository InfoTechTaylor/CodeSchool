package com.sg.dao;
import com.sg.dto.Location;
import com.sg.dto.Sighting;
import com.sg.service.LocationService;
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
public class SightingDaoImplTest {

    @Inject
    SightingDao sightingDao;
    
    @Inject
    LocationService locationService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        Sighting createdSighting = createTestSighting();
        assert createdSighting.getId() != null;
        assertNotNull(createdSighting.getLocation().getId());
        assertEquals(LocalDate.parse("2018-03-02"), createdSighting.getSightingDate());
        assert "It was really fast!".equals(createdSighting.getDescription());
    }

    @Test
    public void read() {
        //arrange
        Sighting createdSighting = createTestSighting();
        //act
        Sighting readSighting = sightingDao.read(createdSighting);
        //assert
        assert readSighting.getId() != null;
        assertNotNull(createdSighting.getLocation().getId());
        assertEquals(LocalDate.parse("2018-03-02"), readSighting.getSightingDate());
        assert "It was really fast!".equals(readSighting.getDescription());
    }

    @Test
    public void update() {
        //arrange
        Sighting createdSighting = createTestSighting();
        //act
        Sighting readSighting = sightingDao.read(createdSighting);
        //update details
        readSighting.setSightingDate(LocalDate.parse("2018-04-03"));
        readSighting.setDescription("Not as fast as the last one");
        sightingDao.update(readSighting);
        //assert
        Sighting updatedSighting = sightingDao.read(readSighting);
        assertEquals(createdSighting.getLocation().getId(), updatedSighting.getLocation().getId());
        assertEquals(LocalDate.parse("2018-04-03"), updatedSighting.getSightingDate());
        assert "Not as fast as the last one".equals(updatedSighting.getDescription());
    }

    @Test
    public void delete() {
        //arrange
        Sighting createdSighting = createTestSighting();
        //act
        sightingDao.delete(createdSighting);
        //assert
        Sighting readsighting = sightingDao.read(createdSighting);
        assert readsighting == null;
    }

    @Test
    public void retrieveAllSightings() {
        //arrange
        Sighting createdSighting = createTestSighting();
        Sighting createdSighting2 = createTestSighting();
        //act - 2 already created
        //assert
        assertEquals(2, sightingDao.retrieveAllSightings(Integer.MAX_VALUE, 0).size());
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
        return sightingDao.create(sighting);
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