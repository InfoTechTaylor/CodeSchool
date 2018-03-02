package dao;

import dto.Team;

import java.util.ArrayList;
import java.util.List;

public class BaseballLeagueDaoTeamStubImpl implements BaseballLeagueDaoTeam {

    Team onlyTeam;
    List<Team> allTeams = new ArrayList<>();

    public BaseballLeagueDaoTeamStubImpl(){

        onlyTeam = new Team();
        onlyTeam.setTeamId("1");
        onlyTeam.setTeamName("Red Sox");
        onlyTeam.setTeamLeague("American");

        allTeams.add(onlyTeam);
    }

    @Override
    public Team createTeam(Team newTeam) throws BaseballLeaguePersistenceException {
        if(newTeam.getTeamId().equals(onlyTeam.getTeamId())){
            return onlyTeam;
        } else {
            return null;
        }

    }

    @Override
    public Team retrieveTeamById(String teamId) throws BaseballLeaguePersistenceException {
        if(teamId.equals(onlyTeam.getTeamId())){
            return onlyTeam;
        } else {
            return null;
        }
    }

    @Override
    public List<Team> retrieveAllTeams() throws BaseballLeaguePersistenceException {
        return allTeams;
    }

    @Override
    public Team updateTeam(Team updatedTeam) throws BaseballLeaguePersistenceException {
        if(updatedTeam.getTeamId().equals(onlyTeam.getTeamId())){
            return onlyTeam;
        } else {
            return null;
        }
    }

    @Override
    public Team removeTeam(String teamId) throws BaseballLeaguePersistenceException {
        if(teamId.equals(onlyTeam.getTeamId())){
            return onlyTeam;
        } else {
            return null;
        }
    }
}
