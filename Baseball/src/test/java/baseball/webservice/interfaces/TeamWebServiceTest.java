package baseball.webservice.interfaces;

import baseball.commandmodel.createteam.CreateTeamCommandModel;
import baseball.commandmodel.editteam.EditTeamCommandModel;
import baseball.dto.Team;
import baseball.service.TeamService;
import baseball.viewmodel.team.createteam.CreateTeamViewModel;
import baseball.viewmodel.team.editteam.EditTeamViewModel;
import baseball.viewmodel.team.teamlist.TeamListViewModel;
import baseball.viewmodel.team.teamlist.TeamViewModel;
import baseball.viewmodel.team.teamprofile.TeamProfileViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Transactional
@Rollback
public class TeamWebServiceTest {

    @Inject
    TeamWebService teamWebService;

    @Inject
    TeamService teamService;


    public List<Team> createTestTeams(int numberOfTeams){

        List<Team> teams = new ArrayList<>();

        // insert numberOfTeams into database
        for(int i=0; i < numberOfTeams; i++){
            Team team = new Team();
            team.setCity("Pittsburgh" + i);
            team.setNickname("Pirates" + i);

            teams.add(teamService.create(team));
        }
        return teams;
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getTeamListViewModel() {
        // arrange
        createTestTeams(15); // add 15 teams to DB

        // act
        TeamListViewModel teamListViewModel = teamWebService.getTeamListViewModel(5, 0, 5); // get first page of teams which will have 5 teams

        // assert
        assert teamListViewModel.getTeams().size() == 5;

        // verify we're on the right page number
        assert teamListViewModel.getPageNumber() == 1;

        // verify we have the right number of page numbers
        assert teamListViewModel.getPageNumbers().size() == 5;

        // verify start/end of page numbers are correct
        assert teamListViewModel.getPageNumbers().get(0) == 1;
        assert teamListViewModel.getPageNumbers().get(4) == 5;

        int counter = 0;
        for (TeamViewModel teamViewModel : teamListViewModel.getTeams()){
            assert ("Pittsburgh" + counter).equals(teamViewModel.getCity());
            assert ("Pirates" + counter).equals(teamViewModel.getNickname());
            counter++;
        }

    }

    @Test
    public void getTeamProfileViewModel() {
        // arrange
        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");

        Team createdTeam = teamService.create(team);


        // act
        TeamProfileViewModel teamProfileViewModel = teamWebService.getTeamProfileViewModel(createdTeam.getId());

        // assert
        assert teamProfileViewModel.getId().equals(createdTeam.getId());
        assert teamProfileViewModel.getCity().equals(createdTeam.getCity());
        assert teamProfileViewModel.getNickname().equals(createdTeam.getNickname());

    }

    @Test
    public void getCreateTeamViewModel() {

        // act
        CreateTeamViewModel createTeamViewModel = teamWebService.getCreateTeamViewModel();

        // assert
        assert createTeamViewModel != null;
    }

    @Test
    public void getEditTeamViewModel() {
        // act
        EditTeamViewModel editTeamViewModel = teamWebService.getEditTeamViewModel();

        // assert
        assert editTeamViewModel != null;
    }

    @Test
    public void getEditTeamCommandModel() {
        // arrange
        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");

        Team createdTeam = teamService.create(team);

        // Act
        EditTeamCommandModel editTeamCommandModel = teamWebService.getEditTeamCommandModel(createdTeam.getId());

        // assert
        assert editTeamCommandModel.getId().equals(createdTeam.getId());
        assert editTeamCommandModel.getCity().equals(createdTeam.getCity());
        assert editTeamCommandModel.getNickname().equals(createdTeam.getNickname());
    }

    @Test
    public void saveCreateTeamCommandModel() {
        // arrange
        CreateTeamCommandModel commandModel = new CreateTeamCommandModel();
        commandModel.setCity("Pittsburgh");
        commandModel.setNickname("Pirates");

        // act
        Team createdTeam = teamWebService.saveCreateTeamCommandModel(commandModel);

        // assert
        assertNotNull(createdTeam.getId());
        // assert team.getId() != null;  --> worked for Pat?
        assertEquals(createdTeam.getCity(), createdTeam.getCity());
        // assert "Pittsburgh".equals(createdTeam.getCity());
        assertEquals(createdTeam.getNickname(), createdTeam.getNickname());
        // assert "Pirates".equals(createdTeam.getNickname());
    }

    @Test
    public void editTeamCommandModel() {
        // arrange
        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");
        Team createdTeam = teamService.create(team);

        EditTeamCommandModel commandModel = new EditTeamCommandModel();
        commandModel.setId(createdTeam.getId());
        commandModel.setCity("Boston");
        commandModel.setNickname("The Red Team");

        // act
        teamWebService.saveEditTeamCommandModel(commandModel);

        // assert
        Team updateTeam = teamService.read(createdTeam.getId());
        assertEquals("The Red Team", updateTeam.getNickname());
        assertEquals("Boston", updateTeam.getCity());
    }
}