package service;

import dao.BaseballLeaguePersistenceException;
import dto.Player;
import dto.Team;

import java.util.List;

public interface BaseballLeagueServiceLayer {

    Team createTeam(String teamName, String teamLeague) throws BaseballLeaguePersistenceException;

    List<Team> retrieveAllTeams() throws BaseballLeaguePersistenceException;

    Player createPlayer(Player newPlayer) throws BaseballLeaguePersistenceException, TeamNotFoundException;

    List<Player> retrieveAllPlayersWithTeamName(String teamName) throws TeamNotFoundException, BaseballLeaguePersistenceException;

    void tradePlayers(String playerOneId, String PlayerTwoId) throws BaseballLeaguePersistenceException, PlayerNotFoundException;

    Player removePlayer(String playerToRemoveId) throws PlayerNotFoundException, BaseballLeaguePersistenceException;

    Team removeTeam(String teamName) throws TeamNotFoundException, BaseballLeaguePersistenceException, PlayersExistOnTeamException;

    List<Player> retrieveAllPlayers() throws BaseballLeaguePersistenceException;

}
