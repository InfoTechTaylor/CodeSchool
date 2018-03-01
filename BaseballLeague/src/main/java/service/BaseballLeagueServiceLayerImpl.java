package service;

import dao.BaseballLeagueAuditDao;
import dao.BaseballLeagueDaoPlayerFileImpl;
import dao.BaseballLeagueDaoTeamFileImpl;
import dao.BaseballLeaguePersistenceException;
import dto.Player;
import dto.Team;

import java.util.ArrayList;
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
    public List<Player> retrieveAllPlayers() throws BaseballLeaguePersistenceException {
        List<Player> allPlayers = playerDao.retrieveAllPlayers();
        retrieveAndSetTeamObjects(allPlayers);
        return allPlayers;
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
    public List<Player> retrieveAllPlayersWithTeamName(String teamName) throws TeamNotFoundException, BaseballLeaguePersistenceException {
        Team teamObj = validateTeamExists(teamName);
        List<Player> allPlayersOnTeam = playerDao.retrieveAllPlayersOnTeam(teamObj.getTeamId());
        retrieveAndSetTeamObjects(allPlayersOnTeam);
        return allPlayersOnTeam;
    }

    @Override
    public void tradePlayers(String playerOneId, String playerTwoId) throws BaseballLeaguePersistenceException, PlayerNotFoundException {
        Player player1 = validatePlayerExists(playerOneId);
        Player player2 = validatePlayerExists(playerTwoId);
        List<Player> twoPlayers = new ArrayList<>();
        twoPlayers.add(player1);
        twoPlayers.add(player2);

        retrieveAndSetTeamObjects(twoPlayers);
        Team player1Team = player1.getPlayersTeam();
        Team player2Team = player2.getPlayersTeam();

        player1.setPlayersTeam(player2Team);
        player2.setPlayersTeam(player1Team);
        playerDao.updatePlayer(player1);
        playerDao.updatePlayer(player2);


    }

    @Override
    public Player removePlayer(String playerToRemoveId) throws PlayerNotFoundException, BaseballLeaguePersistenceException {
        validatePlayerExists(playerToRemoveId);
        return playerDao.removePlayer(playerToRemoveId);
    }

    @Override
    public Team removeTeam(String teamName) throws TeamNotFoundException, BaseballLeaguePersistenceException, PlayersExistOnTeamException {
        Team teamToRemove = validateTeamExists(teamName);
        // validate if there are players still on the team
        if(retrieveAllPlayersWithTeamName(teamToRemove.getTeamName()).size() != 0){
            throw new PlayersExistOnTeamException("Players still exist on team. \nPlease remove or reassign players before" +
                                            " removing this team.");
        }
        teamDao.removeTeam(teamToRemove.getTeamId());
        return teamToRemove;
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

    private Player validatePlayerExists(String playerId) throws PlayerNotFoundException, BaseballLeaguePersistenceException {
        if(playerDao.retrievePlayerById(playerId) == null){
            throw new PlayerNotFoundException("Unable to locate player with provided ID.");
        }
        return playerDao.retrievePlayerById(playerId);

    }

    private void retrieveAndSetTeamObjects(List<Player> playerList) throws BaseballLeaguePersistenceException {
        for(Player currentPlayer : playerList){
            Team teamObj = teamDao.retrieveTeamById(currentPlayer.getPlayersTeam().getTeamId());
            currentPlayer.setPlayersTeam(teamObj);
            playerDao.updatePlayer(currentPlayer);
        }
    }

}
