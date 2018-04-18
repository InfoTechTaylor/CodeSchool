package com.sg.dao;
import com.sg.dto.Person;
import com.sg.dto.PersonPower;
import com.sg.dto.Power;
import com.sg.service.PersonPowerService;
import com.sg.service.PersonService;
import com.sg.service.PowerService;
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
import java.io.PipedWriter;
import java.util.List;
import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback(true)
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PowerDaoImplTest {

    @Inject
    private PowerDao powerDao;

    @Inject
    private PersonService personService;

    @Inject
    private PersonPowerService personPowerService;


    private Person createTestPerson() {
        Person person = new Person();
        person.setName("Taylor");
        person.setType("Super hero");
        person.setDescription("Super Awesome");
        Person personCreated = personService.create(person);
        return person;
    }

    private void createTestPersonPower(Person person, Power power1) {
        PersonPower personPower1 = new PersonPower();
        personPower1.setPerson(person);
        personPower1.setPower(power1);
        PersonPower personPower = personPowerService.create(personPower1);
    }

    private Power createTestPower(String powerName) {
        Power power1 = new Power();
        power1.setName(powerName);
        Power power = powerDao.create(power1);
        return power1;
    }

    @Test
    public void create() {
        //Arrange
        Power power = new Power();
        power.setName("Flying");
        //Act
        Power createdPower = powerDao.create(power);
        //Assert
        assertNotNull(createdPower.getId());
        assertEquals("Flying", createdPower.getName());
    }

    @Test
    public void read() {
        //Arrange
        Power power = new Power();
        power.setName("Flying");
        Power createdPower = powerDao.create(power);
        //Act
        Power readPower = powerDao.read(createdPower);
        //Assert
        assertEquals("Flying", readPower.getName());
    }

    @Test
    public void update() {
        //Arrange
        Power power = new Power();
        power.setName("Flying");
        Power createdPower = powerDao.create(power);
        Power readPower = powerDao.read(createdPower);
        readPower.setName("Speed");
        //Act
        powerDao.update(readPower);
        //Assert
        Power updatePower = powerDao.read(readPower);
        assertEquals("Speed", updatePower.getName());
    }

    @Test
    public void delete() {
        //Arrange
        Power power = new Power();
        power.setName("Flying");
        Power createdPower = powerDao.create(power);
        assertNotNull(createdPower.getId());
        //Act
        powerDao.delete(createdPower);
        //Assert
        Power readPower = powerDao.read(createdPower);
        assertNull(readPower);

    }

    @Test
    public void retrieveAllPowers() {
        Power power1 = new Power();
        power1.setName("Flying");
        powerDao.create(power1);
        Power power2 = new Power();
        power2.setName("Speed");
        powerDao.create(power2);
        assertEquals(2, powerDao.retrieveAllPowers(Integer.MAX_VALUE, 0).size());
    }

    @Test
    public void retrieveAllPowersByPerson() {
        //Arrange
        Person person = createTestPerson();
        Person person1 = createTestPerson();

        Power power1 = createTestPower("Flying");
        Power power2 = createTestPower("Speed");
        Power power3 = createTestPower("Strength");

        createTestPersonPower(person, power1);
        createTestPersonPower(person, power2);
        createTestPersonPower(person, power3);
        createTestPersonPower(person1, power1);

        //Act
        List<Power> powerList = powerDao.retrieveAllPowersByPerson(person, Integer.MAX_VALUE, 0);
        List<Power> powerList1 = powerDao.retrieveAllPowersByPerson(person1, Integer.MAX_VALUE, 0);

        //Assert
        assertEquals(3, powerList.size());
        assertEquals(1, powerList1.size());
    }

    @Test
    public void retrieveAllPowersPagination() {
        Power power1 = new Power();
        power1.setName("Flying");
        powerDao.create(power1);
        Power power2 = new Power();
        power2.setName("Speed");
        powerDao.create(power2);
        assertEquals(1, powerDao.retrieveAllPowers(Integer.valueOf(1),Integer.valueOf(0)).size());
        assertEquals(1, powerDao.retrieveAllPowers(Integer.valueOf(1),Integer.valueOf(1)).size());
    }

    @Test
    public void retrieveAllPowersByPersonPagination() {
        //Arrange
        Person person = createTestPerson();
        Person person1 = createTestPerson();

        Power power1 = createTestPower("Flying");
        Power power2 = createTestPower("Speed");
        Power power3 = createTestPower("Strength");

        createTestPersonPower(person, power1);
        createTestPersonPower(person, power2);
        createTestPersonPower(person, power3);
        createTestPersonPower(person1, power1);

        //Act
        List<Power> powerList = powerDao.retrieveAllPowersByPerson(person, 2, 0);
        List<Power> powerList1 = powerDao.retrieveAllPowersByPerson(person, 2, 2);

        //Assert
        assertEquals(2, powerList.size());
        assertEquals(1, powerList1.size());
    }


}