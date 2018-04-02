package baseball.service;

import baseball.dao.PlayerDao;
import baseball.dto.Player;
import baseball.dto.Team;
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
public class PlayerServiceImplTest {

    @Inject
    PlayerService playerService;

    @Inject
    TeamService teamService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void create() {
        Team createdTeam = createTestTeam();
        Player createdPlayer = createTestPlayer(0, createdTeam);

        // assert
        Player readPlayer = playerService.read(createdPlayer.getId());
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
        return playerService.create(player);
    }

    private Team createTestTeam() {
        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");
        return teamService.create(team);
    }


    @Test
    public void read() {
        // arrange
        Team createdTeam = createTestTeam();
        Player player = createTestPlayer(0, createdTeam);

        player.setTeam(createdTeam);
        Player createdPlayer = playerService.create(player);

        // act
        Player readPlayer = playerService.read(createdPlayer.getId());

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
        Player readPlayer = playerService.read(createdPlayer.getId());
        readPlayer.setFirstName("Taylor");
        readPlayer.setLastName("Lapointe");

        // act
        playerService.update(readPlayer);

        // assert
        Player updatePlayer = playerService.read(readPlayer.getId());
        assertEquals("Taylor", updatePlayer.getFirstName());
        assertEquals("Lapointe", updatePlayer.getLastName());
    }

    @Test
    public void delete() {
        // arrange
        Team createdTeam = createTestTeam();
        Player createdPlayer = createTestPlayer(0, createdTeam);

        // act
        playerService.delete(createdPlayer);

        // assert
        assertNull(playerService.read(createdPlayer.getId()));
    }

    @Test
    public void getPlayersByTeam() {
        // arrange
        Team team = createTestTeam();
        int numberOfPlayers = 15;

        createTestPlayers(team, numberOfPlayers);

        // act
        List<Player> allPlayersOnTeam = playerService.getPlayersByTeam(team, Integer.MAX_VALUE, 0);

        // assert
        assertPlayersOnTeam(numberOfPlayers, allPlayersOnTeam, team);

    }

    private void assertPlayersOnTeam(int numberOfPlayers, List<Player> allPlayersOnTeam, Team team) {
        assertEquals(numberOfPlayers, allPlayersOnTeam.size());
        for(Player currentPlayer : allPlayersOnTeam){
            assertEquals(team.getId(), currentPlayer.getTeam().getId());
        }
    }

    private void createTestPlayers(Team team, int numberOfPlayers) {
        for(int i=0; i < numberOfPlayers; i++){
            Player createdPlayer = createTestPlayer(i, team);
        }
    }

    @Test
    public void getPlayersByTeamPage() {
        // arrange
        Team team = createTestTeam();
        int numberOfPlayers = 15;

        createTestPlayers(team, numberOfPlayers);

        // act
        List<Player> allPlayersOnTeam = playerService.getPlayersByTeam(team, 5, 0);

        // assert
        assertPlayersOnTeam(5, allPlayersOnTeam, team);

    }
}