package baseball.service;

import baseball.dao.TeamDao;
import baseball.dto.Team;
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
public class TeamServiceImplTest {

    @Inject
    TeamService TeamService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void create() {
        // arrange
        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");

        // act
        Team createdTeam = TeamService.create(team);

        // assert
        assertNotNull(createdTeam.getId());
        // assert team.getId() != null;  --> worked for Pat?
        assertEquals(team.getCity(), createdTeam.getCity());
        // assert "Pittsburgh".equals(createdTeam.getCity());
        assertEquals(team.getNickname(), createdTeam.getNickname());
        // assert "Pirates".equals(createdTeam.getNickname());
    }

    @Test
    public void read() {
        // arrange
        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");
        Team createdTeam = TeamService.create(team);

        // act
        Team readTeam = TeamService.read(createdTeam.getId());

        // assert
        assertNotNull(readTeam);
        assertEquals("Pittsburgh", readTeam.getCity());
        assertEquals("Pirates", readTeam.getNickname());
    }

    @Test
    public void update() {
        // arrange
        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");
        Team createdTeam = TeamService.create(team);
        Team readTeam = TeamService.read(createdTeam.getId());

        readTeam.setNickname("The Red Team");
        readTeam.setCity("Boston");

        // act
        TeamService.update(readTeam);

        // assert
        Team updateTeam = TeamService.read(readTeam.getId());
        assertEquals("The Red Team", updateTeam.getNickname());
        assertEquals("Boston", updateTeam.getCity());
    }

    @Test
    public void delete() {
        // arrange
        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");
        Team createdTeam = TeamService.create(team);
        assertNotNull(createdTeam.getId());

        // act
        TeamService.delete(createdTeam);

        // assert
        assertNull(TeamService.read(createdTeam.getId()));
    }
}