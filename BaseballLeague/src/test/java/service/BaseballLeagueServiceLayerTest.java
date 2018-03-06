package service;

import dto.Player;
import dto.Team;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class BaseballLeagueServiceLayerTest {

    private BaseballLeagueServiceLayer service;
    Team teamToTest;
    Player playerToTest;

    public BaseballLeagueServiceLayerTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", BaseballLeagueServiceLayer.class);

        teamToTest = new Team();
        teamToTest.setTeamId("1");
        teamToTest.setTeamName("Red Sox");
        teamToTest.setTeamLeague("American");
    }

    @Test
    public void createTeam() throws Exception{
        service.createTeam(teamToTest.getTeamName(), teamToTest.getTeamLeague());
        assertEquals(1, service.retrieveAllTeams().size());
    }

    @Test
    public void retrieveAllTeams() throws Exception{
        service.createTeam(teamToTest.getTeamName(), teamToTest.getTeamLeague());
        assertEquals(1, service.retrieveAllTeams().size());
    }

    @Test
    public void createPlayer() throws Exception {
        service.createPlayer(playerToTest);
        assertEquals(1, service.retrieveAllPlayers().size());
    }

    @Test
    public void retrieveAllPlayersWithTeamName() throws Exception{
        assertEquals(1, service.retrieveAllPlayersWithTeamName("Red Sox").size());

    }

    @Test
    public void tradePlayers() throws Exception {
    }

    @Test
    public void removePlayer() throws Exception {
    }

    @Test
    public void removeTeam() throws Exception {
    }

    @Test
    public void retrieveAllPlayers() throws Exception {
    }
}