package baseball.webservice.interfaces;

import baseball.dto.Player;
import baseball.dto.Team;
import baseball.service.PlayerService;
import baseball.service.TeamService;
import baseball.viewmodel.player.playerlist.PlayerListViewModel;
import baseball.viewmodel.player.playerlist.PlayerViewModel;
import baseball.viewmodel.team.teamlist.TeamListViewModel;
import baseball.viewmodel.team.teamlist.TeamViewModel;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Transactional
@Rollback
public class PlayerWebServiceTest {

    @Inject
    PlayerWebService playerWebService;

    @Inject
    PlayerService playerService;

    @Inject
    TeamService teamService;

    @Before
    public void setUp() throws Exception {
    }

    public List<Player> createTestPlayers(int numberOfPlayers){

        List<Player> players = new ArrayList<>();

        // insert numberOfTeams into database
        for(int i=0; i < numberOfPlayers; i++){
            Player player = new Player();
            player.setFirstName("Pat" + i);
            player.setLastName("Toner" + i);
            player.setHomeTown("Arizona" + i);

            Team team = new Team();
            team.setCity("Pittsburgh" + i);
            team.setNickname("Pirates" + i);
            Team createdTeam = teamService.create(team);
            player.setTeam(createdTeam);

            players.add(playerService.create(player));
        }
        return players;
    }

    @Test
    public void getPlayerListViewModel() {
        // arrange
        List<Player> players = createTestPlayers(15); // add 15 teams to DB

        // act
        PlayerListViewModel playerListViewModel = playerWebService.getPlayerListViewModel(5, 0, 5); // get first page of players which will have 5 players

        // assert
        assert playerListViewModel.getPlayers().size() == 5;

        // verify we're on the right page number
        assert playerListViewModel.getPageNumber() == 1;

        // verify we have the right number of page numbers
        assert playerListViewModel.getPageNumbers().size() == 5;

        // verify start/end of page numbers are correct
        assert playerListViewModel.getPageNumbers().get(0) == 1;
        assert playerListViewModel.getPageNumbers().get(4) == 5;

        int counter = 0;
        for (PlayerViewModel playerViewModel : playerListViewModel.getPlayers()){
            assert ("Pat" + counter).equals(playerViewModel.getFirstName());
            assert ("Toner" + counter).equals(playerViewModel.getLastName());

            Player matchingPlayer = players.get(counter);
            // get team from team service, doing this cause team is currently lazy loaded
            // so test will pass even if in future it becomes not lazy loaded
            Team team = teamService.read(matchingPlayer.getTeam().getId());

            assert team.getNickname().equals(playerViewModel.getTeamName());
            assert team.getId() == (playerViewModel.getTeamId());


            counter++;
        }
    }
}