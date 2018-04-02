package baseball.dao;

import baseball.dto.Player;
import baseball.dto.PlayerPosition;
import baseball.dto.Position;
import baseball.dto.Team;
import baseball.service.PlayerPositionService;
import baseball.service.PlayerService;
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
public class PositionDaoDBImplTest {

    @Inject
    private PositionDao positionDao;

    @Inject
    private PlayerService playerService;

    @Inject
    private PositionService positionService;

    @Inject
    private PlayerPositionService playerPositionService;

    @Inject
    private TeamService teamService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void create() {
        Position createdPosition = createTestPosition();


        // assert
        assertNotNull(createdPosition.getId());
        assertEquals("P", createdPosition.getName());
    }

    @Test
    public void read() {
        // arrange
        Position createdPosition = createTestPosition();

        // act
        Position readPosition = positionDao.read(createdPosition.getId());

        // assert
        assertNotNull(readPosition);
        assertEquals("P", readPosition.getName());
    }

    @Test
    public void update() {
        // arrange
        Position createdPosition = createTestPosition();
        Position readPosition = positionDao.read(createdPosition.getId());

        readPosition.setName("2B");

        // act
        positionDao.update(readPosition);

        // assert
        Position position = positionDao.read(readPosition.getId());
        assertEquals("2B", position.getName());

    }

    @Test
    public void delete() {
        // arrange
        Position createdPosition = createTestPosition();
        assertNotNull(createdPosition.getId());

        // act
        positionDao.delete(createdPosition);

        // assert
        assertNull(positionDao.read(createdPosition.getId()));
    }

    @Test
    public void getPositionsByPlayer(){
        Team team = createTestTeam();
        Player player = createTestPlayer(0, team);

        Position position1 = createTestPosition();
        Position position2 = createTestPosition2();

        PlayerPosition playerPosition1 = new PlayerPosition();
        playerPosition1.setPosition(position1);
        playerPosition1.setPlayer(player);

        PlayerPosition playerPosition2 = new PlayerPosition();
        playerPosition2.setPosition(position2);
        playerPosition2.setPlayer(player);
        playerPositionService.create(playerPosition1);
        playerPositionService.create(playerPosition2);

        List<Position> playerPositions = positionDao.getPositionsByPlayer(player, Integer.MAX_VALUE, 0);

        assertEquals(2, playerPositions.size());
        assert playerPositions.get(0).getName().equals("P") || playerPositions.get(0).getName().equals("2B");
        assert playerPositions.get(1).getName().equals("P") || playerPositions.get(1).getName().equals("2B");
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

    private Position createTestPosition(){
        Position position = new Position();
        position.setName("P");
        return positionService.create(position);
    }

    private Position createTestPosition2(){
        Position position = new Position();
        position.setName("2B");
        return positionService.create(position);
    }
}