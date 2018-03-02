package dao;

import dto.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class BaseballLeagueDaoPlayerTest {

    BaseballLeagueDaoPlayer dao;

    public BaseballLeagueDaoPlayerTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = ctx.getBean("playerDao", BaseballLeagueDaoPlayer.class);
    }

    @Before
    public void setUp() throws Exception {
        List<Player> allPlayers = dao.retrieveAllPlayers();
        for(Player currentPlayer : allPlayers){
            dao.removePlayer(currentPlayer.getPlayerId());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createPlayer() {
    }

    @Test
    public void retrievePlayerById() {
    }

    @Test
    public void retrieveAllPlayers() {
    }

    @Test
    public void retrieveAllPlayersOnTeam() {
    }

    @Test
    public void updatePlayer() {
    }

    @Test
    public void removePlayer() {
    }
}