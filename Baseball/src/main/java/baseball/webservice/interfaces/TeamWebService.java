package baseball.webservice.interfaces;

import baseball.commandmodel.createteam.CreateTeamCommandModel;
import baseball.commandmodel.editteam.EditTeamCommandModel;
import baseball.dto.Team;
import baseball.viewmodel.team.createteam.CreateTeamViewModel;
import baseball.viewmodel.team.editteam.EditTeamViewModel;
import baseball.viewmodel.team.teamlist.TeamListViewModel;
import baseball.viewmodel.team.teamprofile.TeamProfileViewModel;

public interface TeamWebService {

    TeamListViewModel getTeamListViewModel(int limit, int offset, int pageNumbers);

    TeamProfileViewModel getTeamProfileViewModel(Long id);

    CreateTeamViewModel getCreateTeamViewModel();

    EditTeamViewModel getEditTeamViewModel();

    EditTeamCommandModel getEditTeamCommandModel(Long id);

    // save command models
    Team saveCreateTeamCommandModel(CreateTeamCommandModel createTeamCommandModel);
    void saveEditTeamCommandModel(EditTeamCommandModel editTeamCommandModel);

}
