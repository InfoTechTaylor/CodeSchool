package com.sg.dao;

import com.sg.dto.Person;
import com.sg.dto.PersonPower;
import com.sg.dto.Power;
import com.sg.service.PersonService;
import com.sg.service.PowerService;
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
public class PersonPowerDaoImplTest {

    @Inject
    private PersonPowerDao personPowerDao;

    @Inject
    private PersonService personService;

    @Inject
    private PowerService powerService;

    // HELPER METHODS *******************************************************************
    public PersonPower createTestPersonPower(Person person, Power power){
        PersonPower personPower = new PersonPower();
        personPower.setPerson(person);
        personPower.setPower(power);
        return personPowerDao.create(personPower);
    }

    public Power createTestPower(){
        Power power = new Power();
        power.setName("Wealth");
        return powerService.create(power);
    }

    public Person createTestPerson(){
        Person person = new Person();
        person.setName("Bruce Wayne");
        person.setDescription("Rich man that dresses like a bat.");
        person.setType("Person");
        return personService.create(person);
    }

    private Power createTestPower(String name){
        Power power = new Power();
        power.setName(name);
        return powerService.create(power);
    }


    // END HELPER METHODS ****************************************************************

    @Test
    public void create() {
        // assert
        Person person = createTestPerson();
        Power power = createTestPower();

        // act
        PersonPower createdPerson = createTestPersonPower(person, power);

        // assert
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getPerson());
        assertNotNull(createdPerson.getPower());
        assertEquals("Bruce Wayne", createdPerson.getPerson().getName());
        assertEquals("Rich man that dresses like a bat.", createdPerson.getPerson().getDescription());
        assertEquals("Person", createdPerson.getPerson().getType());
        assertEquals("Wealth", createdPerson.getPower().getName());
    }

    @Test
    public void read() {
        // arrange
        Person person = createTestPerson();
        Power power = createTestPower();
        PersonPower createdPersonPower = createTestPersonPower(person, power);

        // act
        PersonPower readPersonPower = personPowerDao.read(createdPersonPower);

        // assert
        assertNotNull(readPersonPower.getId());
        assertNotNull(readPersonPower.getPerson());
        assertNotNull(readPersonPower.getPower());
        assertEquals(createdPersonPower.getId(), readPersonPower.getId());
        assertEquals(createdPersonPower.getPerson().getId(), readPersonPower.getPerson().getId());
        assertEquals(createdPersonPower.getPower().getId(), readPersonPower.getPower().getId());
    }

    @Test
    public void update() {
        // arrange, original person, power to set personPower
        Person person = createTestPerson();
        Power power = createTestPower();
        PersonPower createdPersonPower = createTestPersonPower(person, power);
        PersonPower readPersonPower = personPowerDao.read(createdPersonPower);

        // create new power to update in personPower
        Power newPower = new Power();
        newPower.setName("Super Strength");
        Power newPowerCreated = powerService.create(power);
        assertNotNull(newPowerCreated.getId());

        // update new power
        readPersonPower.setPower(newPowerCreated);

        // act
        personPowerDao.update(readPersonPower);

        // assert
        PersonPower updatedPersonPower = personPowerDao.read(readPersonPower);
        assertEquals(newPowerCreated.getId(), updatedPersonPower.getPower().getId());
    }

    @Test
    public void delete() {
        // arrange, original person, power to set personPower
        Person person = createTestPerson();
        Power power = createTestPower();
        PersonPower createdPersonPower = createTestPersonPower(person, power);

        // act
        personPowerDao.delete(createdPersonPower);

        // assert
        assertNull(personPowerDao.read(createdPersonPower));
    }

    @Test
    public void retrieveAllPersonPowers() {
        // arrange, original person, power to set personPower
        Person person = createTestPerson();
        Power power = createTestPower();
        createTestPersonPower(person, power);

        Power newPower = new Power();
        newPower.setName("Super Strength");
        Power newPowerCreated = powerService.create(power);
        assertNotNull(newPowerCreated.getId());

        PersonPower personPower = new PersonPower();
        personPower.setPower(newPowerCreated);
        personPower.setPerson(person);
        personPowerDao.create(personPower);

        // act
        List<PersonPower> allPersonPowers = personPowerDao.retrieveAllPersonPowers(Integer.MAX_VALUE, Integer.valueOf(0));

        // assert
        assertEquals(2, allPersonPowers.size());

    }

    @Test
    public void retrieveAllPersonPowersPagination() {
        // arrange, original person, power to set personPower
        Person person = createTestPerson();
        Power power = createTestPower();
        createTestPersonPower(person, power);

        Power newPower = new Power();
        newPower.setName("Super Strength");
        Power newPowerCreated = powerService.create(power);
        assertNotNull(newPowerCreated.getId());

        PersonPower personPower = new PersonPower();
        personPower.setPower(newPowerCreated);
        personPower.setPerson(person);
        personPowerDao.create(personPower);

        // act
        List<PersonPower> allPersonPowers = personPowerDao.retrieveAllPersonPowers(1, 0);
        List<PersonPower> allPersonPowers1 = personPowerDao.retrieveAllPersonPowers(1, 1);

        // assert
        assertEquals(1, allPersonPowers.size());
        assertEquals(1, allPersonPowers1.size());

    }

    @Test
    public void retrieveAllPersonPowerByPerson() {
        Person person1 = createTestPerson();
        Person person2 = createTestPerson();
        Power power1 = createTestPower("Strength");
        Power power2 = createTestPower("Wealth");
        Power power3 = createTestPower("Flying");
        createTestPersonPower(person1, power1);
        createTestPersonPower(person1, power2);
        createTestPersonPower(person1, power3);
        createTestPersonPower(person2, power2);
        //Act
        List<PersonPower> person1Powers = personPowerDao.retrieveAllPersonPowerByPerson(person1, Integer.MAX_VALUE, 0);
        List<PersonPower> person2Powers = personPowerDao.retrieveAllPersonPowerByPerson(person2, Integer.MAX_VALUE, 0);
        //Assert
        assertEquals(3, person1Powers.size());
        assertEquals(1, person2Powers.size());
    }
    @Test
    public void retrieveAllPersonPowerByPower() {
        Person person1 = createTestPerson();
        Person person2 = createTestPerson();
        Power power1 = createTestPower("Strength");
        Power power2 = createTestPower("Wealth");
        createTestPersonPower(person1, power1);
        createTestPersonPower(person1, power2);
        createTestPersonPower(person2, power2);
        //Act
        List<PersonPower> power1Persons = personPowerDao.retrieveAllPersonPowerByPower(power1, Integer.MAX_VALUE, 0);
        List<PersonPower> power2Persons = personPowerDao.retrieveAllPersonPowerByPower(power2, Integer.MAX_VALUE, 0);
        //Assert
        assertEquals(1, power1Persons.size());
        assertEquals(2, power2Persons.size());
    }
}