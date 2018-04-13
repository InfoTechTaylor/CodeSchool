package baseball.webservice.interfaces;

import baseball.commandmodel.team.createteam.CreateTeamCommandModel;
import baseball.commandmodel.team.editteam.EditTeamCommandModel;
import baseball.dto.Team;
import baseball.viewmodel.team.createteam.CreateTeamViewModel;
import baseball.viewmodel.team.editteam.EditTeamViewModel;
import baseball.viewmodel.team.teamlist.TeamListViewModel;
import baseball.viewmodel.team.teamprofile.TeamProfileViewModel;

public interface TeamWebService {

    TeamListViewModel getTeamListViewModel(Integer limit, Integer offset, Integer pageNumbers);

    TeamProfileViewModel getTeamProfileViewModel(Long id);

    CreateTeamViewModel getCreateTeamViewModel();

    EditTeamViewModel getEditTeamViewModel();

    EditTeamCommandModel getEditTeamCommandModel(Long id);

    // save command models
    Team saveCreateTeamCommandModel(CreateTeamCommandModel createTeamCommandModel);
    void saveEditTeamCommandModel(EditTeamCommandModel editTeamCommandModel);

}
