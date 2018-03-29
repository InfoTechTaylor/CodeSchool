package baseball.dao;

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
public class teamDaoDBImplTest {

    @Inject
    TeamDao dao;

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
        Team createdTeam = dao.create(team);

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
        Team createdTeam = dao.create(team);

        // act
        Team readTeam = dao.read(createdTeam.getId());

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
        Team createdTeam = dao.create(team);
        Team readTeam = dao.read(createdTeam.getId());

        readTeam.setNickname("The Red Team");
        readTeam.setCity("Boston");

        // act
        dao.update(readTeam);

        // assert
        Team updateTeam = dao.read(readTeam.getId());
        assertEquals("The Red Team", updateTeam.getNickname());
        assertEquals("Boston", updateTeam.getCity());
    }

    @Test
    public void delete() {
        // arrange
        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");
        Team createdTeam = dao.create(team);
        assertNotNull(createdTeam.getId());

        // act
        dao.delete(createdTeam);

        // assert
        assertNull(dao.read(createdTeam.getId()));
    }
}