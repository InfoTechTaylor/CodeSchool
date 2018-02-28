package dao;

import dto.Player;

import java.util.List;

public interface BaseballLeagueDaoPlayer {

    // C - CREATE
    Player createPlayer(Player newPlayer) throws BaseballLeaguePersistenceException;

    // R - READ
    Player retrievePlayerById(String playerId);

    // R - retrieve all objects
    List<Player> retrieveAllPlayers();

    // U - update an Object
    Player updatePlayer(Player updatedPlayer);

    // D - remove object
    Player removePlayer(String playerId);


}
