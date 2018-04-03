package com.sg.dao;

import com.sg.dto.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class PersonDaoImplTest {


    @Inject
    PersonDao personDao;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        Person person = createTestPerson(0);

        //Act
        Person createdPerson = personDao.create(person);

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
        Person readPerson = personDao.read(createdPerson);

        //Assert
        assertEquals("Person0", readPerson.getType());
        assertEquals("Jaqwan0", readPerson.getName());
        assertEquals("Taxidermist by day. Protector of cable tv by night.0", readPerson.getDescription());

    }

    @Test
    public void update() {
        //Arrange
        Person createdPerson = createTestPerson(0);
        Person readPerson = personDao.read(createdPerson);
        readPerson.setType("hero");
        readPerson.setName("Taylor");
        readPerson.setDescription("GoForCoder");

        //Act
        personDao.update(readPerson);

        //Assert
        Person updatedPerson = personDao.read(readPerson);
        assertEquals("hero", updatedPerson.getType());
        assertEquals("Taylor", updatedPerson.getName());
        assertEquals("GoForCoder", updatedPerson.getDescription());

    }

    @Test
    public void delete() {
        //Arrange
        Person createdPerson = createTestPerson(0);

        //Act
        personDao.delete(createdPerson);

        //Assert
        Person readPerson = personDao.read(createdPerson);
        assertNull(readPerson);
    }

    @Test
    public void retrieveAllPersons() {
        //Arrange
        createTestPerson(0);
        createTestPerson(1);
        createTestPerson(2);

        //Act
        List<Person> allPersons = personDao.retrieveAllPersons(Integer.MAX_VALUE,0);

        //Assert
        assertEquals(3, allPersons.size());
    }

    @Test
    public void retrieveAllPersonsByOrg() {
        //Arrange
        //Act
        //Assert
    }

    @Test
    public void retrieveAllPersonsByPower() {
        //Arrange
        //Act
        //Assert
    }

    @Test
    public void retrieveAllPersonsBySighting() {
        //Arrange
        //Act
        //Assert
    }

    private Person createTestPerson(int personIndex) {
        Person person = new Person();
        person.setType("Person" + personIndex);
        person.setName("Jaqwan" + personIndex);

        person.setDescription("Taxidermist by day. Protector of cable tv by night." + personIndex);

        return personDao.create(person);
    }
}