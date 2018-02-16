package dao;

import dto.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class BaseballLeagueDaoTeamTest {

    private BaseballLeagueDaoTeam dao;

    public BaseballLeagueDaoTeamTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = ctx.getBean("teamDao", BaseballLeagueDaoTeam.class);
    }



    @Before
    public void setUp() throws Exception {
        List<Team> teamList = dao.retrieveAllTeams();
        for(Team currentTeam : teamList){
            dao.removeTeam(currentTeam.getTeamId());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateAndRetrieveTeam() throws Exception{
        //arrange
        Team newTeam = new Team("01");
        newTeam.setTeamName("Boston Red Sox");
        newTeam.setTeamLeague("American League");

        // act
        dao.createTeam(newTeam);

        // assert
        assertEquals(1, dao.retrieveAllTeams().size());
        assertEquals(newTeam, dao.retrieveTeamById("01"));
    }

    @Test
    public void retrieveAllTeams() throws Exception{
        //arrange
        Team newTeam = new Team("01");
        newTeam.setTeamName("Boston Red Sox");
        newTeam.setTeamLeague("American League");
        dao.createTeam(newTeam);

        // act
        List<Team> allTeamList = dao.retrieveAllTeams();

        //assert
        assertEquals(1, allTeamList.size());

    }

    @Test
    public void updateTeam() throws Exception{
        //arrange
        Team originalTeam = new Team("01");
        originalTeam.setTeamName("Boston Red Sox");
        originalTeam.setTeamLeague("American League");
        dao.createTeam(originalTeam);

        Team teamToUpdate = dao.retrieveTeamById("01");


        //act
        teamToUpdate.setTeamLeague("National League");
        dao.updateTeam(teamToUpdate);
        Team updatedTeam = dao.retrieveTeamById(teamToUpdate.getTeamId());

        //assert
        assertNotEquals(updatedTeam.getTeamLeague(), originalTeam.getTeamLeague());
    }

    @Test
    public void removeTeam() throws Exception{
        //arrange
        Team newTeam = new Team("01");
        newTeam.setTeamName("Boston Red Sox");
        newTeam.setTeamLeague("American League");
        dao.createTeam(newTeam);

        // act
        dao.removeTeam(newTeam.getTeamId());

        // assert
        assertEquals(0, dao.retrieveAllTeams().size());
    }
}