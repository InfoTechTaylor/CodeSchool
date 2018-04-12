package baseball.viewmodel.player.editplayer;


import baseball.commandmodel.player.createplayer.CreatePlayerCommandModel;
import baseball.commandmodel.player.editplayer.EditPlayerCommandModel;

import java.util.List;

public class EditPlayerViewModel {

    private List<EditTeamViewModel> teams;
    private List<EditPositionViewModel> positions;

    // specifically to handle redisplaying data when validation errors happen
    private EditPlayerCommandModel editPlayerCommandModel;

    public List<EditTeamViewModel> getTeams() {
        return teams;
    }

    public void setTeams(List<EditTeamViewModel> teams) {
        this.teams = teams;
    }

    public List<EditPositionViewModel> getPositions() {
        return positions;
    }

    public void setPositions(List<EditPositionViewModel> positions) {
        this.positions = positions;
    }

    public EditPlayerCommandModel getEditPlayerCommandModel() {
        return editPlayerCommandModel;
    }

    public void setEditPlayerCommandModel(EditPlayerCommandModel editPlayerCommandModel) {
        this.editPlayerCommandModel = editPlayerCommandModel;
    }
}
