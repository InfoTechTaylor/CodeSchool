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
        assertEquals(2, powerService.retrieveAllPowers(Integer.MAX_VALUE, 0).size());
    }

    @Test
    public void retrieveAllPowersByPerson() {
        //Arrange
        Person person = new Person();
        person.setName("Taylor");
        person.setType("Super hero");
        person.setDescription("Super Awesome");
        personService.create(person);
        Power power1 = new Power();
        power1.setName("Flying");
        powerService.create(power1);
        Power power2 = new Power();
        power2.setName("Speed");
        powerService.create(power2);
        Power power3 = new Power();
        power3.setName("Strength");
        powerService.create(power3);
        PersonPower personPower1 = new PersonPower();
        personPower1.setPerson(person);
        personPower1.setPower(power1);
        personPowerService.create(personPower1);
        PersonPower personPower2 = new PersonPower();
        personPower2.setPerson(person);
        personPower2.setPower(power2);
        personPowerService.create(personPower2);
        PersonPower personPower3 = new PersonPower();
        personPower3.setPerson(person);
        personPower3.setPower(power3);
        personPowerService.create(personPower3);
        //Act
        List<Power> powerList = powerService.retrieveAllPowersByPerson(person, Integer.MAX_VALUE, 0);
        //Assert
        boolean power1Found = false;
        boolean power2Found = false;
        boolean power3Found = false;
        for (Power power : powerList){
            if (power.getName().equals(power1.getName())){
                power1Found = true;
            }
            if (power.getName().equals(power2.getName())){
                power2Found = true;
            }
            if (power.getName().equals(power3.getName())){
                power3Found = true;
            }
        }
        assert powerList.size() == 3;
        assert power1Found;
        assert power2Found;
        assert power3Found;
    }
}