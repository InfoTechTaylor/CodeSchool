package dao;

import dto.Player;
import dto.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class BaseballLeagueDaoPlayerTest {

    BaseballLeagueDaoPlayer dao;
    Player playerToTest;

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

        playerToTest = new Player();
        playerToTest.setPlayerId("1");
        playerToTest.setPlayerFirstName("Taylor");
        playerToTest.setPlayerLastName("Lapointe");
        Team newTeam = new Team();
        newTeam.setTeamId("1");
//        newTeam.setTeamName("Red Sox");
//        newTeam.setTeamLeague("American");
        playerToTest.setPlayersTeam(newTeam);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createPlayer() throws Exception{
        dao.createPlayer(playerToTest);
        Player playerFromDao = dao.retrievePlayerById("1");
        assertEquals(playerToTest, playerFromDao);
    }

    @Test
    public void retrievePlayerById() throws Exception{
        dao.createPlayer(playerToTest);
        Player playerFromDao = dao.retrievePlayerById("1");
        assertEquals(playerToTest, playerFromDao);
    }

    @Test
    public void retrieveAllPlayers() throws Exception{
        dao.createPlayer(playerToTest);
        List<Player> allPlayers = dao.retrieveAllPlayers();
        assertEquals(1, allPlayers.size());
    }

    @Test
    public void retrieveAllPlayersOnTeam() throws Exception {
        dao.createPlayer(playerToTest);
        List<Player> allPlayersOnTeam = dao.retrieveAllPlayersOnTeam(playerToTest.getPlayersTeam().getTeamId());
        assertEquals(1, allPlayersOnTeam.size());
    }

    @Test
    public void updatePlayer() throws Exception{
        dao.createPlayer(playerToTest);
        playerToTest.setPlayerLastName("Hicklin");
        dao.updatePlayer(playerToTest);
        assertEquals("Hicklin", dao.retrievePlayerById("1").getPlayerLastName());
    }

    @Test
    public void removePlayer() throws Exception{
        dao.createPlayer(playerToTest);
        dao.removePlayer(playerToTest.getPlayerId());
        assertEquals(0, dao.retrieveAllPlayers().size());
    }
}