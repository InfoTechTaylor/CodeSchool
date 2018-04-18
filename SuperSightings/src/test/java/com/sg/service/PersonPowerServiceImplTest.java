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
public class PersonPowerServiceImplTest {

    @Inject
    private PersonPowerService personPowerService;

    @Inject
    private PersonService personService;

    @Inject
    private PowerService powerService;

    // HELPER METHODS *******************************************************************
    public PersonPower createTestPersonPower(Person person, Power power){
        PersonPower personPower = new PersonPower();
        personPower.setPerson(person);
        personPower.setPower(power);
        return personPowerService.create(personPower);
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
        PersonPower readPersonPower = personPowerService.read(createdPersonPower);

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
        PersonPower readPersonPower = personPowerService.read(createdPersonPower);

        // create new power to update in personPower
        Power newPower = new Power();
        newPower.setName("Super Strength");
        Power newPowerCreated = powerService.create(power);
        assertNotNull(newPowerCreated.getId());

        // update new power
        readPersonPower.setPower(newPowerCreated);

        // act
        personPowerService.update(readPersonPower);

        // assert
        PersonPower updatedPersonPower = personPowerService.read(readPersonPower);
        assertEquals(newPowerCreated.getId(), updatedPersonPower.getPower().getId());
    }

    @Test
    public void delete() {
        // arrange, original person, power to set personPower
        Person person = createTestPerson();
        Power power = createTestPower();
        PersonPower createdPersonPower = createTestPersonPower(person, power);

        // act
        personPowerService.delete(createdPersonPower);

        // assert
        assertNull(personPowerService.read(createdPersonPower));
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
        personPowerService.create(personPower);

        // act
        List<PersonPower> allPersonPowers = personPowerService.retrieveAllPersonPowers(Integer.MAX_VALUE, Integer.valueOf(0));

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
        personPowerService.create(personPower);

        // act
        List<PersonPower> allPersonPowers = personPowerService.retrieveAllPersonPowers(Integer.valueOf(1), Integer.valueOf(0));
        List<PersonPower> allPersonPowers1 = personPowerService.retrieveAllPersonPowers(Integer.valueOf(1), Integer.valueOf(1));

        // assert
        assertEquals(1, allPersonPowers.size());
        assertEquals(1, allPersonPowers1.size());

    }
}