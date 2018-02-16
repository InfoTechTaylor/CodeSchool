package dao;

import dto.Player;

import java.util.List;

public class BaseballLeagueDaoPlayerFileImpl implements BaseballLeagueDaoPlayer {

    private static String filename;

    public BaseballLeagueDaoPlayerFileImpl(String filename) {
        this.filename = filename;
    }


    @Override
    public Player createPlayer() {
        return null;
    }

    @Override
    public Player retrievePlayerById(String playerId) {
        return null;
    }

    @Override
    public List<Player> retrieveAllPlayers() {
        return null;
    }

    @Override
    public Player updatePlayer(Player updatedPlayer) {
        return null;
    }

    @Override
    public Player removePlayer(String playerId) {
        return null;
    }


}
