package baseball.webservice;

import baseball.commandmodel.team.createteam.CreateTeamCommandModel;
import baseball.commandmodel.team.editteam.EditTeamCommandModel;
import baseball.dto.Team;
import baseball.service.TeamService;
import baseball.viewmodel.team.createteam.CreateTeamViewModel;
import baseball.viewmodel.team.editteam.EditTeamViewModel;
import baseball.viewmodel.team.teamlist.TeamListViewModel;
import baseball.viewmodel.team.teamlist.TeamViewModel;
import baseball.viewmodel.team.teamprofile.TeamProfileViewModel;
import baseball.webservice.interfaces.TeamWebService;
import baseball.webservice.util.PagingUtil;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TeamWebServiceImpl implements TeamWebService {


    TeamService teamService;

    @Inject
    public TeamWebServiceImpl(TeamService teamService) {
        this.teamService = teamService;
    }

    // translate a list
    private List<TeamViewModel> translate(List<Team> teams) {

        List<TeamViewModel> teamViewModels = new ArrayList<>();

        for(Team team : teams) {
            teamViewModels.add(translate(team));
        }

        return teamViewModels;
    }

    // translate an individual one
    private TeamViewModel translate(Team team){
        TeamViewModel teamViewModel = new TeamViewModel();

        teamViewModel.setId(team.getId());
        teamViewModel.setCity(team.getCity());
        teamViewModel.setNickname(team.getNickname());

        return teamViewModel;
    }

    @Override
    public TeamListViewModel getTeamListViewModel(int limit, int offset, int numPagesToShow) {

        // instantiate view model
        TeamListViewModel teamListViewModel = new TeamListViewModel();

        // figure out stuff to put into view model
        List<Team> teams = teamService.list(limit, offset);

        Integer currentPage = PagingUtil.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtil.getPageNumbers(currentPage, numPagesToShow);

        // put stuff into the view model
        teamListViewModel.setTeams(translate(teams));
        teamListViewModel.setPageNumber(currentPage);
        teamListViewModel.setPageNumbers(pages);

        return teamListViewModel;
    }

    @Override
    public TeamProfileViewModel getTeamProfileViewModel(Long id) {
        // instantiate view model object
        TeamProfileViewModel teamProfileViewModel = new TeamProfileViewModel();

        // look up team
        Team team = teamService.read(id);

        // set values on view model object
        teamProfileViewModel.setId(team.getId());
        teamProfileViewModel.setCity(team.getCity());
        teamProfileViewModel.setNickname(team.getNickname());

        return teamProfileViewModel;
    }

    @Override
    public CreateTeamViewModel getCreateTeamViewModel() {
        return new CreateTeamViewModel();
    }

    @Override
    public EditTeamViewModel getEditTeamViewModel() {
        return new EditTeamViewModel();
    }

    @Override
    public EditTeamCommandModel getEditTeamCommandModel(Long id) {
        // this method currently populates the edit form

        // instantiate command model object
        EditTeamCommandModel editTeamCommandModel = new EditTeamCommandModel();

        // lookup team stuff
        Team team = teamService.read(id);

        // set team attributes
        editTeamCommandModel.setId(team.getId());
        editTeamCommandModel.setCity(team.getCity());
        editTeamCommandModel.setNickname(team.getNickname());

        return editTeamCommandModel;
    }

    @Override
    public Team saveCreateTeamCommandModel(CreateTeamCommandModel createTeamCommandModel) {

        // translate onto domain object
        Team team = new Team();
        team.setCity(createTeamCommandModel.getCity());
        team.setNickname(createTeamCommandModel.getNickname());

        // Save
        Team createdTeam = teamService.create(team);

        return createdTeam;
    }

    @Override
    public void saveEditTeamCommandModel(EditTeamCommandModel editTeamCommandModel) {

        // lookup existing
        Team existingTeam = teamService.read(editTeamCommandModel.getId());

        // translate
        existingTeam.setCity(editTeamCommandModel.getCity());
        existingTeam.setNickname(editTeamCommandModel.getNickname());

        // save
        teamService.update(existingTeam);
    }
}
