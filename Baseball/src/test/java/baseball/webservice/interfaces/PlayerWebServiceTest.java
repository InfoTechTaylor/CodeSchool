package baseball.webservice.interfaces;

import baseball.commandmodel.player.createplayer.CreatePlayerCommandModel;
import baseball.commandmodel.player.editplayer.EditPlayerCommandModel;
import baseball.dto.Player;
import baseball.dto.PlayerPosition;
import baseball.dto.Position;
import baseball.dto.Team;
import baseball.service.PlayerPositionService;
import baseball.service.PlayerService;
import baseball.service.PositionService;
import baseball.service.TeamService;
import baseball.viewmodel.player.createplayer.CreatePlayerViewModel;
import baseball.viewmodel.player.createplayer.CreatePositionViewModel;
import baseball.viewmodel.player.createplayer.CreateTeamViewModel;
import baseball.viewmodel.player.editplayer.EditPlayerViewModel;
import baseball.viewmodel.player.editplayer.EditPositionViewModel;
import baseball.viewmodel.player.editplayer.EditTeamViewModel;
import baseball.viewmodel.player.playerlist.PlayerListViewModel;
import baseball.viewmodel.player.playerlist.PlayerViewModel;
import baseball.viewmodel.player.playerprofile.PlayerProfileViewModel;
import baseball.viewmodel.player.playerprofile.PositionViewModel;

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

    @Inject
    PositionService positionService;

    @Inject
    PlayerPositionService playerPositionService;

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

    public List<Team> createTestTeams(int numberOfTeams){
        List<Team> teams = new ArrayList<>();

        for (int i=0; i < numberOfTeams; i++){
            Team team = new Team();
            team.setCity("Pittsburgh" + i);
            team.setNickname("Pirates" + i);
            Team createdTeam = teamService.create(team);
            teams.add(createdTeam);
        }

        return teams;
    }

    public List<Position> createTestPositions(int numberOfPositions){
        List<Position> positions = new ArrayList<>();

        for (int i=0; i < numberOfPositions; i++){
            Position position = new Position();
            position.setName("P" + i);
            Position createdPosition = positionService.create(position);
            positions.add(createdPosition);
        }

        return positions;
    }

    public List<Position> createSecondTestPositions(int numberOfPositions){
        List<Position> positions = new ArrayList<>();

        for (int i=0; i < numberOfPositions; i++){
            Position position = new Position();
            position.setName("1B" + i);
            Position createdPosition = positionService.create(position);
            positions.add(createdPosition);
        }

        return positions;
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

    @Test
    public void getPlayerProfileViewModel() {
        // arrange
        Player player = new Player();
        player.setFirstName("Pat");
        player.setLastName("Toner");
        player.setHomeTown("Arizona");

        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");
        Team createdTeam = teamService.create(team);

        player.setTeam(createdTeam);

        Player createdPlayer = playerService.create(player);

        Position position = new Position();
        position.setName("1B");

        Position position1 = new Position();
        position1.setName("2B");

        Position firstBase = positionService.create(position);
        Position secondBase = positionService.create(position1);

        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setPlayer(createdPlayer);
        playerPosition.setPosition(firstBase);
        playerPositionService.create(playerPosition);

        PlayerPosition playerPosition1 = new PlayerPosition();
        playerPosition1.setPlayer(createdPlayer);
        playerPosition1.setPosition(secondBase);
        playerPositionService.create(playerPosition1);

        // act
        PlayerProfileViewModel playerProfileViewModel = playerWebService.getPlayerProfileViewModel(createdPlayer.getId());

        // assert
        assert playerProfileViewModel.getId().equals(createdPlayer.getId());
        assert playerProfileViewModel.getFirst().equals(createdPlayer.getFirstName());
        assert playerProfileViewModel.getLast().equals(createdPlayer.getLastName());
        assert playerProfileViewModel.getTeamId().equals(team.getId());
        assert playerProfileViewModel.getTeamName().equals(createdTeam.getCity() + " " + team.getNickname());

        boolean containsFirstBase = false;
        boolean containsSecondBase = false;
        for (PositionViewModel currentPositionViewModel : playerProfileViewModel.getPositions()){
            if("1B".equals(currentPositionViewModel.getName())){
                containsFirstBase = true;
            }
            if("2B".equals(currentPositionViewModel.getName())){
                containsSecondBase = true;
            }
        }
        assert containsFirstBase == true;
        assert containsSecondBase == true;
    }

    @Test
    public void getCreatePlayerViewModel() {
        // Arrange
        List<Team> teams = createTestTeams(15);
        List<Position> positions = createTestPositions(15);

        // Act
        CreatePlayerViewModel createPlayerViewModel = playerWebService.getCreatePlayerViewModel();

        // Assert
        assert createPlayerViewModel.getPositions().size() == positions.size();
        assert createPlayerViewModel.getTeams().size() == teams.size();

        for(CreatePositionViewModel createPositionViewModel : createPlayerViewModel.getPositions()){
            assert createPositionViewModel.getId() != null;
            assert createPositionViewModel.getName() != null;
        }

        for(CreateTeamViewModel createTeamViewModel : createPlayerViewModel.getTeams()){
            assert createTeamViewModel.getId() != null;
            assert createTeamViewModel.getName() != null;
        }
    }

    @Test
    public void saveCreatePlayerCommandModel() {

        // arrange
        Team team = createTestTeam();
        List<Position> createdPositions = createTestPositions(2);

        CreatePlayerCommandModel createPlayerCommandModel = new CreatePlayerCommandModel();
        createPlayerCommandModel.setFirst("Pat");
        createPlayerCommandModel.setLast("Toner");
        createPlayerCommandModel.setHometown("Virginia");
        createPlayerCommandModel.setTeamId(team.getId());

        Long[] positionIds = new Long[2];
        positionIds[0] = createdPositions.get(0).getId();
        positionIds[1] = createdPositions.get(1).getId();

        createPlayerCommandModel.setPositionIds(positionIds);

        // act
        Player player = playerWebService.saveCreatePlayerCommandModel(createPlayerCommandModel);

        // assert
        assert player.getId() != null;
        assert "Pat".equals(player.getFirstName());
        assert "Toner".equals(player.getLastName());
        assert "Virginia".equals(player.getHomeTown());
        assert player.getTeam().getId() == (team.getId());

        List<Position> positions = positionService.getPositionsByPlayer(player, Integer.MAX_VALUE, 0);

        boolean savedFirstPosition = false;
        boolean savedSecondPosition = false;

        for (Position position : positions){
            if(position.getId() == (createdPositions.get(0).getId())) savedFirstPosition = true;
            if(position.getId() == (createdPositions.get(1).getId())) savedSecondPosition = true;
        }

        assert savedFirstPosition == true;
        assert savedSecondPosition == true;
    }

    private Team createTestTeam() {
        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");
        return teamService.create(team);
    }

    private Player createTestPlayer(Team team){
        Player player = new Player();
        player.setFirstName("Pat");
        player.setLastName("Toner");
        player.setHomeTown("Colorado");
        player.setTeam(team);

        return playerService.create(player);
    }

    @Test
    public void saveEditPlayerCommandModel() {
        // arrange
        Team team = createTestTeam();
        List<Position> createdPositions = createTestPositions(2);

        // set up test player with a team and a couple existing positions
        Player existingPlayer = createTestPlayerWithTeamAndPositions(team, createdPositions);

        EditPlayerCommandModel editPlayerCommandModel = new EditPlayerCommandModel();
        editPlayerCommandModel.setId(existingPlayer.getId());
        editPlayerCommandModel.setFirst("John");
        editPlayerCommandModel.setLast("Tally");
        editPlayerCommandModel.setHometown("Taco");

        // put on different team
        Team updateTeam = createTestTeam();
        editPlayerCommandModel.setTeamId(updateTeam.getId());

        // Player different positions
        List<Position> updatedPositions = createSecondTestPositions(3);

        Long[] positionIds = new Long[3];
        positionIds[0] = updatedPositions.get(0).getId();
        positionIds[1] = updatedPositions.get(1).getId();
        positionIds[2] = updatedPositions.get(2).getId();

        editPlayerCommandModel.setPositionIds(positionIds);

        // act
        Player editedPlayer = playerWebService.saveEditPlayerCommandModel(editPlayerCommandModel);

        // assert
        assert editedPlayer.getId() != null;
        assert "John".equals(editedPlayer.getFirstName());
        assert "Tally".equals(editedPlayer.getLastName());
        assert "Taco".equals(editedPlayer.getHomeTown());
        assert editedPlayer.getTeam().getId() == (updateTeam.getId());

        List<Position> positions = positionService.getPositionsByPlayer(editedPlayer, Integer.MAX_VALUE, 0);

        boolean savedFirstPosition = false;
        boolean savedSecondPosition = false;
        boolean savedThirdPosition = false;
        boolean deletedFirstExistingPosition = true;
        boolean deletedSecondExistingPosition = true;


        for (Position position : positions){
            // verify we have the new positions
            if(position.getId() == (updatedPositions.get(0).getId())) savedFirstPosition = true;
            if(position.getId() == (updatedPositions.get(1).getId())) savedSecondPosition = true;
            if(position.getId() == (updatedPositions.get(2).getId())) savedThirdPosition = true;

            // verify we no longer have the old positions
            if(position.getId() == (createdPositions.get(0).getId())) deletedFirstExistingPosition = true;
            if(position.getId() == (createdPositions.get(1).getId())) deletedSecondExistingPosition = true;
        }

        assert savedFirstPosition == true;
        assert savedSecondPosition == true;
        assert savedThirdPosition == true;
        assert deletedFirstExistingPosition == true;
        assert deletedSecondExistingPosition == true;

    }

    private Player createTestPlayerWithTeamAndPositions(Team team, List<Position> createdPositions) {
        Player existingPlayer = createTestPlayer(team);

        for (Position position : createdPositions) {
            PlayerPosition playerPosition = new PlayerPosition();
            playerPosition.setPlayer(existingPlayer);
            playerPosition.setPosition(position);
            playerPositionService.create(playerPosition);
        }
        return existingPlayer;
    }

    @Test
    public void getEditPlayerViewModel() {
        // Arrange
        List<Team> teams = createTestTeams(15);
        List<Position> positions = createTestPositions(15);

        Team createdTeam = teams.get(0);
        List<Position> selectedPositions = new ArrayList<>();
        selectedPositions.add(positions.get(0));
        selectedPositions.add(positions.get(1));
        Player existingPlayer = createTestPlayerWithTeamAndPositions(createdTeam, selectedPositions);

        // Act
        EditPlayerViewModel editPlayerViewModel = playerWebService.getEditPlayerViewModel(existingPlayer.getId());

        // Assert
        assert editPlayerViewModel.getPositions().size() == positions.size();
        assert editPlayerViewModel.getTeams().size() == teams.size();

        for(EditPositionViewModel editPositionViewModel : editPlayerViewModel.getPositions()){
            assert editPositionViewModel.getId() != null;
            assert editPositionViewModel.getName() != null;
        }

        for(EditTeamViewModel editTeamViewModel : editPlayerViewModel.getTeams()){
            assert editTeamViewModel.getId() != null;
            assert editTeamViewModel.getName() != null;
        }

        EditPlayerCommandModel commandModel = editPlayerViewModel.getEditPlayerCommandModel();
        assert commandModel.getId().equals(existingPlayer.getId());
        assert commandModel.getFirst().equals(existingPlayer.getFirstName());
        assert commandModel.getLast().equals(existingPlayer.getLastName());
        assert commandModel.getHometown().equals(existingPlayer.getHomeTown());

        assert commandModel.getTeamId().equals(createdTeam.getId());

        boolean containsFirstPosition = false;
        boolean containsSecondPosition = false;
        for (Long positionId : commandModel.getPositionIds()){
            if(positionId.equals(positions.get(0).getId())) containsFirstPosition = true;
            if(positionId.equals(positions.get(1).getId())) containsSecondPosition = true;
        }

        assert containsFirstPosition == true;
        assert containsSecondPosition == true;
    }
}