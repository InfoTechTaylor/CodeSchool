package com.sg.util;

import com.sg.dto.*;
import com.sg.service.*;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestHelperMethods {

    private LocationService locationService;
    private PowerService powerService;
    private OrganizationService organizationService;
    private SightingService sightingService;
    private PersonService personService;
    private PersonOrganizationService personOrganizationService;

    @Inject
    public TestHelperMethods(LocationService locationService,
                             PowerService powerService,
                             OrganizationService organizationService,
                             PersonService personService,
                             SightingService sightingService,
                             PersonOrganizationService personOrganizationService) {
        this.locationService = locationService;
        this.powerService = powerService;
        this.organizationService = organizationService;
        this.personService = personService;
        this.sightingService = sightingService;
        this.personOrganizationService = personOrganizationService;
    }

    public List<Location> createTestLocations(int numLocations){

        // create list to add locations to
        List<Location> locations = new ArrayList<>();

        for(int i=0; i < numLocations; i++){
            Location location = new Location();
            location.setLatitude(40.779287);
            location.setLongitude(-73.969326);
            location.setName("Central Park" + i);
            location.setDescription("near Belvedere Castle");
            location.setStreet("79th Street");
            location.setCity("New York");
            location.setState("NY");
            location.setZip("10021");
            location.setCountry("USA");
            Location createdLocation = locationService.create(location);

            locations.add(createdLocation);
        }

        return locations;
    }

    public List<Power> createTestPowers(int numPowers){
        List<Power> powers = new ArrayList<>();

        for(int i=0; i < numPowers; i++) {
            Power power = new Power();
            power.setName("Wealth" + i);
            Power createdPower = powerService.create(power);
            powers.add(createdPower);
        }

        return powers;
    }


    public List<Organization> createTestOrgs(int numOrgs, Location location){

        List<Organization> orgs = new ArrayList<>();

        for(int i=0; i < numOrgs; i++){
            Organization org = new Organization();
            org.setLocation(location);
            org.setName("The Avengers");
            org.setDescription("Earth's No. 1 Team");
            Organization createdOrg = organizationService.create(org);
            orgs.add(createdOrg);
        }

        return orgs;
    }


    public List<Sighting> createTestSightings(int numSightings, Location location){
        List<Sighting> sightings = new ArrayList<>();

        for(int i=0; i < numSightings; i++){
            Sighting sighting = new Sighting();
            sighting.setSightingDate(LocalDate.parse("2018-04-10"));
            sighting.setLocation(location);
            sighting.setDescription("Superman vs Batman showdown");
            Sighting sightingCreated = sightingService.create(sighting);
            sightings.add(sightingCreated);
        }
        return sightings;
    }

    public List<Sighting> createTestSightingsTwo(int numSightings, Location location){
        List<Sighting> sightings = new ArrayList<>();

        for(int i=0; i < numSightings; i++){
            Sighting sighting = new Sighting();
            sighting.setSightingDate(LocalDate.parse("2018-04-10"));
            sighting.setLocation(location);
            sighting.setDescription("Superman vs Batman showdown" + i);
            Sighting sightingCreated = sightingService.create(sighting);
            sightings.add(sightingCreated);
        }
        return sightings;
    }

    public List<Person> createTestPersons(int numPersons){
        List<Person> persons = new ArrayList<>();

        for(int i=0; i<numPersons; i++){
            Person person = new Person();
            person.setName("Bruce Wayne" + i);
            person.setDescription("Rich Man dresses like bat.");
            person.setType("Person");
            Person createdPerson = personService.create(person);
            persons.add(createdPerson);
        }

        return persons;
    }

    public List<PersonOrganization> createPersonOrganizations(int numPersonOrgs, Person person, Organization org){
        List<PersonOrganization> personOrgs = new ArrayList<>();

        for(int i=0; i<numPersonOrgs; i++){
            PersonOrganization personOrganization = new PersonOrganization();
            personOrganization.setPerson(person);
            personOrganization.setOrganization(org);
            personOrganizationService.create(personOrganization);
            personOrgs.add(personOrganization);
        }
        return personOrgs;
    }

}
