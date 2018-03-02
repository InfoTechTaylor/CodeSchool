package dao;

import dto.Player;
import dto.Team;

import java.util.ArrayList;
import java.util.List;

public class BaseballLeagueDaoPlayerStubImpl implements BaseballLeagueDaoPlayer {


    Player onlyPlayer;
    List<Player> allPlayers = new ArrayList<>();
    Team onlyTeam;

    public BaseballLeagueDaoPlayerStubImpl(){
        onlyPlayer = new Player();
        onlyPlayer.setPlayerId("1");
        onlyPlayer.setPlayerFirstName("Taylor");
        onlyPlayer.setPlayerLastName("Lapointe");
        onlyTeam = new Team();
        onlyTeam.setTeamId("1");
        onlyTeam.setTeamName("Red Sox");
        onlyTeam.setTeamLeague("American");
        onlyPlayer.setPlayersTeam(onlyTeam);

        allPlayers.add(onlyPlayer);
    }

    @Override
    public Player createPlayer(Player newPlayer) throws BaseballLeaguePersistenceException {
        if(newPlayer.getPlayerId().equals(onlyPlayer.getPlayerId())){
            return onlyPlayer;
        } else {
            return null;
        }
    }

    @Override
    public Player retrievePlayerById(String playerId) throws BaseballLeaguePersistenceException {
        if(playerId.equals(onlyPlayer.getPlayerId())){
            return onlyPlayer;
        } else {
            return null;
        }
    }

    @Override
    public List<Player> retrieveAllPlayers() throws BaseballLeaguePersistenceException {
        return allPlayers;
    }

    @Override
    public List<Player> retrieveAllPlayersOnTeam(String teamName) throws BaseballLeaguePersistenceException {
        if(onlyPlayer.getPlayersTeam().getTeamName().equals(teamName)){
            return allPlayers;
        } else {
            return null;
        }
    }

    @Override
    public Player updatePlayer(Player updatedPlayer) throws BaseballLeaguePersistenceException {
        if(updatedPlayer.getPlayerId().equals(onlyPlayer.getPlayerId())){
            return onlyPlayer;
        } else {
            return null;
        }
    }

    @Override
    public Player removePlayer(String playerId) throws BaseballLeaguePersistenceException {
        if(playerId.equals(onlyPlayer.getPlayerId())){
            return onlyPlayer;
        } else {
            return null;
        }
    }
}
