package service;

import dao.BaseballLeagueAuditDao;
import dao.BaseballLeagueDaoPlayerFileImpl;
import dao.BaseballLeagueDaoTeamFileImpl;
import dao.BaseballLeaguePersistenceException;
import dto.Player;
import dto.Team;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseballLeagueServiceLayerImpl implements BaseballLeagueServiceLayer {

    private BaseballLeagueDaoTeamFileImpl teamDao;
    private BaseballLeagueDaoPlayerFileImpl playerDao;
    private BaseballLeagueAuditDao auditDao;
//    private static AtomicInteger uniqueId=new AtomicInteger();

    public BaseballLeagueServiceLayerImpl(BaseballLeagueDaoTeamFileImpl teamDao,
                                          BaseballLeagueDaoPlayerFileImpl playerDao, BaseballLeagueAuditDao auditDao) {
        this.teamDao = teamDao;
        this.playerDao = playerDao;
        this.auditDao = auditDao;
    }


    @Override
    public Team createTeam(String teamName, String teamLeague) throws BaseballLeaguePersistenceException{
        // get next available team ID
        String teamId = retrieveNextAvailableTeamID();
        Team newTeam = new Team(teamId);
        newTeam.setTeamName(teamName);
        newTeam.setTeamLeague(teamLeague);

        teamDao.createTeam(newTeam);
        // create new team object
        return newTeam;
    }

    private String retrieveNextAvailableTeamID() throws BaseballLeaguePersistenceException{
//        List<Team> allTeams = teamDao.retrieveAllTeams();
//        int indexOfLastTeam = allTeams.size() -1;
//        String lastUsedTeamId = allTeams.get(indexOfLastTeam).getTeamId();
//        int nextAvailableIdAsInt = Integer.parseInt(lastUsedTeamId) + 1;
//        // this will not work for when a team is removed
//        return Integer.toString(nextAvailableIdAsInt);
        List<Team> allTeams = teamDao.retrieveAllTeams();
        int highestIdValue = 0;
        for(Team currentTeam : allTeams){
            if(Integer.parseInt(currentTeam.getTeamId()) > highestIdValue){
                highestIdValue = Integer.parseInt(currentTeam.getTeamId());
            }
        }
        highestIdValue++;
        return Integer.toString(highestIdValue);

    }

    private String retrieveNextAvailablePlayerId() throws BaseballLeaguePersistenceException {
        List<Player> allPlayers = playerDao.retrieveAllPlayers();
        int highestIdValue = 0;
        for(Player currentPlayer : allPlayers){
            if(Integer.parseInt(currentPlayer.getPlayerId()) > highestIdValue){
                highestIdValue = Integer.parseInt(currentPlayer.getPlayerId());
            }
        }
        highestIdValue++;
        return Integer.toString(highestIdValue);
    }

    @Override
    public List<Team> retrieveAllTeams() throws BaseballLeaguePersistenceException{
        return teamDao.retrieveAllTeams();
    }

    @Override
    public Player createPlayer(Player newPlayer) throws BaseballLeaguePersistenceException, TeamNotFoundException {
        Team teamObj = validateTeamExists(newPlayer.getPlayersTeam().getTeamName());
        newPlayer.setPlayersTeam(teamObj);
        String playerId = retrieveNextAvailablePlayerId();
        newPlayer.setPlayerId(playerId);
        return playerDao.createPlayer(newPlayer);
    }

    @Override
    public List<Player> retrieveAllPlayersWithTeamId(String teamId) {
        return null;
    }

    @Override
    public void tradePlayers(String playerOneId, String PlayerTwoId) {

    }

    @Override
    public Player removePlayer(String playerToRemoveId) {
        return null;
    }

    private Team validateTeamExists(String teamName) throws TeamNotFoundException {
        boolean doesTeamExist = false;
        Team validatedTeam = null;
        try{
            List<Team> allTeams = retrieveAllTeams();
            for(Team currentTeam : allTeams){
                if(currentTeam.getTeamName().toUpperCase().equals(teamName.toUpperCase())) {
                    doesTeamExist = true;
                    validatedTeam = currentTeam;
                    break;
                }
            }
            if (!doesTeamExist) {
                throw new TeamNotFoundException("Unable to find team with provided name.");
            }
        } catch(BaseballLeaguePersistenceException e){
            throw new TeamNotFoundException(e.getMessage());
        }
        return validatedTeam;
    }

    private boolean validatePlayerExists(String playerId){
        return false;
    }

}
