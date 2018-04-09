package baseball.webservice;

import baseball.dto.Player;
import baseball.dto.Team;
import baseball.service.PlayerService;
import baseball.service.TeamService;
import baseball.viewmodel.player.playerlist.PlayerListViewModel;
import baseball.viewmodel.player.playerlist.PlayerViewModel;
import baseball.webservice.interfaces.PlayerWebService;
import baseball.webservice.util.PagingUtil;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PlayerWebServiceImpl implements PlayerWebService {

    PlayerService playerService;
    TeamService teamService;

    @Inject
    public PlayerWebServiceImpl(PlayerService playerService, TeamService teamService){
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @Override
    public PlayerListViewModel getPlayerListViewModel(int limit, int offset, int numPagesToShow) {

        // Instantiate
        PlayerListViewModel playerListViewModel = new PlayerListViewModel();

        // look stuff up
        List<Player> players = playerService.list(limit, offset);

        Integer currentPage = PagingUtil.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtil.getPageNumbers(currentPage, numPagesToShow);

        // put stuff in
        playerListViewModel.setPageNumber(currentPage);
        playerListViewModel.setPageNumbers(pages);
        playerListViewModel.setPlayers(translate(players));

        return playerListViewModel;
    }

    private List<PlayerViewModel> translate(List<Player> players) {
        List<PlayerViewModel> playerViewModels = new ArrayList<>();

        for (Player player : players) {
            playerViewModels.add(translate(player));
        }

        return playerViewModels;
    }

    private PlayerViewModel translate(Player player) {
        PlayerViewModel playerViewModel = new PlayerViewModel();

        playerViewModel.setId(player.getId());
        playerViewModel.setFirstName(player.getFirstName());
        playerViewModel.setLastName(player.getLastName());

        // Eager fetching
        if(player.getTeam() != null) {
//            Team team = teamService.read(player.getTeam().getId());
            playerViewModel.setTeamId(player.getTeam().getId());
            playerViewModel.setTeamName(player.getTeam().getNickname());
        }

        return playerViewModel;
    }
}
