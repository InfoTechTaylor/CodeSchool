package baseball.dao;

import baseball.dto.Player;
import baseball.dto.PlayerPosition;
import baseball.dto.Position;
import baseball.dto.Team;
import baseball.service.PlayerPositionService;
import baseball.service.PositionService;
import baseball.service.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Transactional
@Rollback
public class PlayerDaoDBImplTest {

    @Inject
    PlayerDao playerDao;

    @Inject
    TeamService teamService;

    @Inject
    PositionService positionService;

    @Inject
    PlayerPositionService playerPositionService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void create() {
        Team createdTeam = createTestTeam();
        Player createdPlayer = createTestPlayer(0, createdTeam);

        // assert
        Player readPlayer = playerDao.read(createdPlayer.getId());
        assertNotNull(createdPlayer.getId());
        assertEquals("Pat0", createdPlayer.getFirstName());
        assertEquals("Toner0", createdPlayer.getLastName());
        assertEquals("Australia0", createdPlayer.getHomeTown());
//        assertEquals(player.getTeam().getId(), readPlayer.getTeam().getId());
    }

    private Player createTestPlayer(int playerIndex, Team team) {
        // arrange
        Player player = new Player();
        player.setFirstName("Pat" + playerIndex);
        player.setLastName("Toner" + playerIndex);
        player.setHomeTown("Australia" + playerIndex);

        player.setTeam(team);

        // act
        return playerDao.create(player);
    }


    private Team createTestTeam() {
        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");
        return teamService.create(team);
    }

    private Position createTestPosition() {
        Position position = new Position();
        position.setName("p");
        return positionService.create(position);

    }

    @Test
    public void read() {
        // arrange
        Team createdTeam = createTestTeam();
        Player player = createTestPlayer(0, createdTeam);

        player.setTeam(createdTeam);
        Player createdPlayer = playerDao.create(player);

        // act
        Player readPlayer = playerDao.read(createdPlayer.getId());

        // assert
        assertNotNull(readPlayer);
        assertEquals("Pat0", createdPlayer.getFirstName());
        assertEquals("Toner0", createdPlayer.getLastName());
        assertEquals("Australia0", createdPlayer.getHomeTown());
        assertEquals(createdPlayer.getTeam().getId(), createdTeam.getId());
    }

    @Test
    public void update() {
        // arrange
        Team createdTeam = createTestTeam();
        Player createdPlayer = createTestPlayer(0, createdTeam);
        Player readPlayer = playerDao.read(createdPlayer.getId());
        readPlayer.setFirstName("Taylor");
        readPlayer.setLastName("Lapointe");

        // act
        playerDao.update(readPlayer);

        // assert
        Player updatePlayer = playerDao.read(readPlayer.getId());
        assertEquals("Taylor", updatePlayer.getFirstName());
        assertEquals("Lapointe", updatePlayer.getLastName());
    }

    @Test
    public void delete() {
        // arrange
        Team createdTeam = createTestTeam();
        Player createdPlayer = createTestPlayer(0, createdTeam);

        // act
        playerDao.delete(createdPlayer);

        // assert
        assertNull(playerDao.read(createdPlayer.getId()));
    }

    @Test
    public void getPlayersByTeam() {
        // arrange
        Team team = createTestTeam();
        int numberOfPlayers = 15;

        createTestPlayers(team, numberOfPlayers);

        // act
        List<Player> allPlayersOnTeam = playerDao.getPlayersByTeam(team, Integer.MAX_VALUE, 0);

        // assert
        assertPlayersOnTeam(numberOfPlayers, allPlayersOnTeam, team);

    }

    private void assertPlayersOnTeam(int numberOfPlayers, List<Player> allPlayersOnTeam, Team team) {
        assertEquals(numberOfPlayers, allPlayersOnTeam.size());
        for(Player currentPlayer : allPlayersOnTeam){
            assertEquals(team.getId(), currentPlayer.getTeam().getId());
        }
    }

    private void assertPlayersByPosition(int numberOfPlayers, List<Player> allPlayersOnTeam, Position position) {
        assertEquals(numberOfPlayers, allPlayersOnTeam.size());
        for(Player currentPlayer : allPlayersOnTeam){
            List<Position> playersPositions = positionService.getPositionsByPlayer(currentPlayer, Integer.MAX_VALUE, 0);

            // loop through player's position and verify it contains the one we added
            boolean containsPosition = false;
            for (Position pos : playersPositions) {
                if (pos.getId() == (position.getId())) containsPosition = true;
            }

            assert containsPosition = true;
        }
    }

    private void createTestPlayers(Team team, int numberOfPlayers) {
        for(int i=0; i < numberOfPlayers; i++){
            Player createdPlayer = createTestPlayer(i, team);
        }
    }

    private void createTestPlayers(Position createdPosition, int numberOfPlayers) {
        for(int i=0; i < numberOfPlayers; i++){
            Player createdPlayer = createTestPlayer(i, null);

            PlayerPosition playerPosition = new PlayerPosition();
            playerPosition.setPlayer(createdPlayer);
            playerPosition.setPosition(createdPosition);
            playerPositionService.create(playerPosition);
        }
    }

    @Test
    public void getPlayersByTeamPage() {
        // arrange
        Team team = createTestTeam();
        int numberOfPlayers = 15;

        createTestPlayers(team, numberOfPlayers);

        // act
        List<Player> allPlayersOnTeam = playerDao.getPlayersByTeam(team, 5, 0);

        // assert
        assertPlayersOnTeam(5, allPlayersOnTeam, team);

    }

    @Test
    public void getPlayersByPosition() {
        // arrange
        Position createPosition = createTestPosition();
        int numberOfPlayers = 15;

        createTestPlayers(createPosition, numberOfPlayers);

        // act
        List<Player> allPlayersOnTeam = playerDao.getPlayersByPosition(createPosition, Integer.MAX_VALUE, 0);

        // assert
        assertPlayersByPosition(numberOfPlayers, allPlayersOnTeam, createPosition);

    }


}