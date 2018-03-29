package baseball.dao;

import baseball.dto.Player;
import baseball.dto.Team;
import baseball.service.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

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

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void create() {
        // arrange
        Player player = new Player();
        player.setFirstName("Pat");
        player.setLastName("Toner");
        player.setHomeTown("Australia");

        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");
        Team createdTeam = teamService.create(team);

        player.setTeam(createdTeam);

        // act
        Player createdPlayer = playerDao.create(player);

        // assert
        Player readPlayer = playerDao.read(createdPlayer.getId());
        assertNotNull(createdPlayer.getId());
        assertEquals("Pat", createdPlayer.getFirstName());
        assertEquals("Toner", createdPlayer.getLastName());
        assertEquals("Australia", createdPlayer.getHomeTown());
//        assertEquals(player.getTeam().getId(), readPlayer.getTeam().getId());
    }

    @Test
    public void read() {
        // arrange
        Player player = new Player();
        player.setFirstName("Pat");
        player.setLastName("Toner");
        player.setHomeTown("Australia");

        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");
        Team createdTeam = teamService.create(team);

        player.setTeam(createdTeam);
        Player createdPlayer = playerDao.create(player);

        // act
        Player readPlayer = playerDao.read(createdPlayer.getId());

        // assert
        assertNotNull(readPlayer);
        assertEquals("Pat", createdPlayer.getFirstName());
        assertEquals("Toner", createdPlayer.getLastName());
        assertEquals("Australia", createdPlayer.getHomeTown());
        assertEquals(createdPlayer.getTeam().getId(), createdTeam.getId());
    }

    @Test
    public void update() {
        // arrange
        Player player = new Player();
        player.setFirstName("Pat");
        player.setLastName("Toner");
        player.setHomeTown("Australia");

        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");
        Team createdTeam = teamService.create(team);

        player.setTeam(createdTeam);
        Player createdPlayer = playerDao.create(player);
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
        Player player = new Player();
        player.setFirstName("Pat");
        player.setLastName("Toner");
        player.setHomeTown("Australia");

        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");
        Team createdTeam = teamService.create(team);

        player.setTeam(createdTeam);
        Player createdPlayer = playerDao.create(player);

        // act
        playerDao.delete(createdPlayer);

        // assert
        assertNull(playerDao.read(createdPlayer.getId()));
    }
}