package service;

import dao.BaseballLeagueAuditDao;
import dao.BaseballLeagueDaoPlayer;
import dao.BaseballLeagueDaoTeam;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class BaseballLeagueServiceLayerTest {

    private BaseballLeagueServiceLayer service;

    public BaseballLeagueServiceLayerTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", BaseballLeagueServiceLayer.class);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createTeam() {
    }

    @Test
    public void retrieveAllTeams() {
    }

    @Test
    public void createPlayer() {
    }

    @Test
    public void retrieveAllPlayersWithTeamId() {
    }

    @Test
    public void tradePlayers() {
    }

    @Test
    public void removePlayer() {
    }
}