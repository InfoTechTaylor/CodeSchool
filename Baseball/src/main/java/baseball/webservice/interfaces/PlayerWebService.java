package baseball.webservice.interfaces;

import baseball.commandmodel.player.createplayer.CreatePlayerCommandModel;
import baseball.commandmodel.player.editplayer.EditPlayerCommandModel;
import baseball.dto.Player;
import baseball.viewmodel.player.createplayer.CreatePlayerViewModel;
import baseball.viewmodel.player.editplayer.EditPlayerViewModel;
import baseball.viewmodel.player.playerlist.PlayerListViewModel;
import baseball.viewmodel.player.playerprofile.PlayerProfileViewModel;

public interface PlayerWebService {

    public PlayerListViewModel getPlayerListViewModel(int limit, int offset, int pageNumbers);

    public PlayerProfileViewModel getPlayerProfileViewModel(Long id);

    public CreatePlayerViewModel getCreatePlayerViewModel();

    public Player saveCreatePlayerCommandModel(CreatePlayerCommandModel createPlayerCommandModel);

    public Player saveEditPlayerCommandModel(EditPlayerCommandModel editPlayerCommandModel);

    public EditPlayerViewModel getEditPlayerViewModel(Long id);
}
