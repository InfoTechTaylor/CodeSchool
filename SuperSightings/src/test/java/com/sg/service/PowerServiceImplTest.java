package com.sg.service;
import com.sg.dto.Person;
import com.sg.dto.PersonPower;
import com.sg.dto.Power;
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
public class PowerServiceImplTest {

    @Inject
    private PowerService powerService;

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

    private PersonPower createTestPersonPower(Person person, Power power1) {
        PersonPower personPower1 = new PersonPower();
        personPower1.setPerson(person);
        personPower1.setPower(power1);
        return personPowerService.create(personPower1);
    }

    private Power createTestPower(String powerName) {
        Power power1 = new Power();
        power1.setName(powerName);
        Power power = powerService.create(power1);
        return power1;
    }

    @Test
    public void create() {
        //Arrange
        Power power = new Power();
        power.setName("Flying");
        //Act
        Power createdPower = powerService.create(power);
        //Assert
        assertNotNull(createdPower.getId());
        assertEquals("Flying", createdPower.getName());
    }

    @Test
    public void read() {
        //Arrange
        Power power = new Power();
        power.setName("Flying");
        Power createdPower = powerService.create(power);
        //Act
        Power readPower = powerService.read(createdPower);
        //Assert
        assertEquals("Flying", readPower.getName());
    }

    @Test
    public void update() {
        //Arrange
        Power power = new Power();
        power.setName("Flying");
        Power createdPower = powerService.create(power);
        Power readPower = powerService.read(createdPower);
        readPower.setName("Speed");
        //Act
        powerService.update(readPower);
        //Assert
        Power updatePower = powerService.read(readPower);
        assertEquals("Speed", updatePower.getName());
    }

    @Test
    public void delete() {
        //Arrange
        Power power = new Power();
        power.setName("Flying");
        Power createdPower = powerService.create(power);
        assertNotNull(createdPower.getId());
        //Act
        powerService.delete(createdPower);
        //Assert
        Power readPower = powerService.read(createdPower);
        assertNull(readPower);

    }

    @Test
    public void retrieveAllPowers() {
        Power power1 = new Power();
        power1.setName("Flying");
        powerService.create(power1);
        Power power2 = new Power();
        power2.setName("Speed");
        powerService.create(power2);
        assertEquals(2, powerService.retrieveAllPowers(Integer.MAX_VALUE, Integer.valueOf(0)).size());
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
        List<Power> powerList = powerService.retrieveAllPowersByPerson(person, Integer.MAX_VALUE, Integer.valueOf(0));
        List<Power> powerList1 = powerService.retrieveAllPowersByPerson(person1, Integer.MAX_VALUE, Integer.valueOf(0));

        //Assert
        assertEquals(3, powerList.size());
        assertEquals(1, powerList1.size());
    }

    @Test
    public void retrieveAllPowersPagination() {
        Power power1 = new Power();
        power1.setName("Flying");
        powerService.create(power1);
        Power power2 = new Power();
        power2.setName("Speed");
        powerService.create(power2);
        assertEquals(1, powerService.retrieveAllPowers(Integer.valueOf(1),Integer.valueOf(0)).size());
        assertEquals(1, powerService.retrieveAllPowers(Integer.valueOf(1),Integer.valueOf(1)).size());
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
        List<Power> powerList = powerService.retrieveAllPowersByPerson(person, Integer.valueOf(2), Integer.valueOf(0));
        List<Power> powerList1 = powerService.retrieveAllPowersByPerson(person, Integer.valueOf(2), Integer.valueOf(2));

        //Assert
        assertEquals(2, powerList.size());
        assertEquals(1, powerList1.size());
    }
}