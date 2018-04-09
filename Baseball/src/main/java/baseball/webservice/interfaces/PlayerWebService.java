package baseball.webservice.interfaces;

import baseball.viewmodel.player.playerlist.PlayerListViewModel;

public interface PlayerWebService {

    public PlayerListViewModel getPlayerListViewModel(int limit, int offset, int pageNumbers);

}
