package dao;

import dto.Player;

import java.util.List;

public interface BaseballLeagueDaoPlayer {

    // C - CREATE
    Player createPlayer(Player newPlayer) throws BaseballLeaguePersistenceException;

    // R - READ
    Player retrievePlayerById(String playerId) throws BaseballLeaguePersistenceException;

    // R - retrieve all objects
    List<Player> retrieveAllPlayers() throws BaseballLeaguePersistenceException;

    List<Player> retrieveAllPlayersOnTeam(String teamName) throws BaseballLeaguePersistenceException;

    // U - update an Object
    Player updatePlayer(Player updatedPlayer);

    // D - remove object
    Player removePlayer(String playerId) throws BaseballLeaguePersistenceException;


}
