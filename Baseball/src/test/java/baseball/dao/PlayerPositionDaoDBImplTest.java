package baseball.dao;

import baseball.dto.Player;
import baseball.dto.PlayerPosition;
import baseball.dto.Position;
import baseball.dto.Team;
import baseball.service.PlayerService;
import baseball.service.PositionService;
import javafx.geometry.Pos;
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
public class PlayerPositionDaoDBImplTest {

    @Inject
    PlayerPositionDao playerPositionDao;
    @Inject
    PlayerService playerService;
    @Inject
    PositionService positionService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void create() {
        // arrange
        Player player = createTestPlayer();
        Position position = createTestPosition();

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setPlayer(player);
        playerPosition.setPosition(position);

        // act
        PlayerPosition createdPlayerPosition = playerPositionDao.create(playerPosition);

        // assert
        assertNotNull(createdPlayerPosition.getId());
        assertEquals(player.getId(), createdPlayerPosition.getPlayer().getId());
        assertEquals(position.getId(), createdPlayerPosition.getPosition().getId());
    }

    @Test
    public void read() {
        // arrange
        Player player = createTestPlayer();
        Position position = createTestPosition();

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setPlayer(player);
        playerPosition.setPosition(position);

        PlayerPosition createdPlayerPosition = playerPositionDao.create(playerPosition);

        // act
        PlayerPosition readPlayerPosition = playerPositionDao.read(createdPlayerPosition.getId());

        // assert
        assertEquals(player.getId(), readPlayerPosition.getPlayer().getId());
        assertEquals(position.getId(), readPlayerPosition.getPosition().getId());

    }

    @Test
    public void update() {
        // arrange
        Player player = createTestPlayer();
        Position position = createTestPosition();

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setPlayer(player);
        playerPosition.setPosition(position);

        PlayerPosition createdPlayerPosition = playerPositionDao.create(playerPosition);
        PlayerPosition readPlayerPosition = playerPositionDao.read(createdPlayerPosition.getId());

        // update to different player/position
        Player secondPlayer = createTestPlayer();
        Position secondPosition = createTestPosition2();


        readPlayerPosition.setPlayer(secondPlayer);
        readPlayerPosition.setPosition(secondPosition);

        // act
        playerPositionDao.update(readPlayerPosition);

        // assert
        PlayerPosition updatedPlayerPosition = playerPositionDao.read(readPlayerPosition.getId());
        assertEquals(secondPlayer.getId(), updatedPlayerPosition.getPlayer().getId());
        assertEquals(secondPosition.getId(), updatedPlayerPosition.getPosition().getId());
    }

    @Test
    public void delete() {
        // arrange
        Player player = createTestPlayer();
        Position position = createTestPosition();

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setPlayer(player);
        playerPosition.setPosition(position);

        PlayerPosition createdPlayerPosition = playerPositionDao.create(playerPosition);

        // act
        playerPositionDao.delete(createdPlayerPosition);

        // assert
        assertNull(playerPositionDao.read(createdPlayerPosition.getId()));
    }


    private Player createTestPlayer() {
        Player player = new Player();
        player.setFirstName("pat");
        player.setLastName("toner");
        return playerService.create(player);
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