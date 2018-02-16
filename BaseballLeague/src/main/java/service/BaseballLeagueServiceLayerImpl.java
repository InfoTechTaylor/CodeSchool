package service;

import dao.BaseballLeagueAuditDao;
import dao.BaseballLeagueDaoPlayerFileImpl;
import dao.BaseballLeagueDaoTeamFileImpl;
import dto.Team;

public class BaseballLeagueServiceLayerImpl implements BaseballLeagueServiceLayer {

    private BaseballLeagueDaoTeamFileImpl teamDao;
    private BaseballLeagueDaoPlayerFileImpl playerDao;
    private BaseballLeagueAuditDao auditDao;

    public BaseballLeagueServiceLayerImpl(BaseballLeagueDaoTeamFileImpl teamDao,
                                          BaseballLeagueDaoPlayerFileImpl playerDao, BaseballLeagueAuditDao auditDao) {
        this.teamDao = teamDao;
        this.playerDao = playerDao;
        this.auditDao = auditDao;
    }

    public Team createTeam(String teamName){
        // retrieve all teams and get next available team ID
        String nextAvailableTeamId = "0001"; // update this to be dynamic
        Team newTeam = new Team(nextAvailableTeamId);
        return newTeam;
    }

    private boolean validateTeamExists(String teamName){
        // call to dao to get team
        // if it returns null, return false -> team doesn't exist
        // if not null return true--> team exists
        return false;
    }
}
