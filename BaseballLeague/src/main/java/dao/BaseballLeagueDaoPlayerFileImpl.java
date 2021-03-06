package dao;

import dto.Player;
import dto.Team;

import java.io.*;
import java.util.*;

public class BaseballLeagueDaoPlayerFileImpl implements BaseballLeagueDaoPlayer {

    private String filename;
    private static final String STRING_DELIMITER = "::";
    private Map<String, Player> playerMap = new HashMap<>();

    public BaseballLeagueDaoPlayerFileImpl(String filename) {
        this.filename = filename;
    }


    @Override
    public Player createPlayer(Player newPlayer) throws BaseballLeaguePersistenceException {
        loadPlayers();
        playerMap.put(newPlayer.getPlayerId(), newPlayer);
        writePlayers();
        return newPlayer;
    }

    @Override
    public Player retrievePlayerById(String playerId) throws BaseballLeaguePersistenceException {
        loadPlayers();
        return playerMap.get(playerId);
    }

    @Override
    public List<Player> retrieveAllPlayers() throws BaseballLeaguePersistenceException {
        loadPlayers();
        return new ArrayList<>(playerMap.values());
    }

    @Override
    public List<Player> retrieveAllPlayersOnTeam(String teamId) throws BaseballLeaguePersistenceException {
        loadPlayers();
        List<Player> allPlayers = new ArrayList<>(playerMap.values());
        List<Player> allPlayersOnTeam = new ArrayList<>();
        for(Player currentPlayer : allPlayers){
            if(currentPlayer.getPlayersTeam().getTeamId().equals(teamId)){
                allPlayersOnTeam.add(currentPlayer);
            }
        }
        return allPlayersOnTeam;
    }

    @Override
    public Player updatePlayer(Player updatedPlayer) throws BaseballLeaguePersistenceException {
        Player playerObj = playerMap.replace(updatedPlayer.getPlayerId(), updatedPlayer);
        writePlayers();
        return playerObj;
    }

    @Override
    public Player removePlayer(String playerId) throws BaseballLeaguePersistenceException {
        Player playerToRemove = playerMap.remove(playerId);
        writePlayers();
        return playerToRemove;
    }

    private void loadPlayers() throws BaseballLeaguePersistenceException {
        Scanner scanner;

        try{
            scanner = new Scanner(new BufferedReader(new FileReader(filename)));
        } catch (FileNotFoundException e){
            throw new BaseballLeaguePersistenceException("Unable to load player file.");
        }

        String currentLine;
        String[] currentTokens;

        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(STRING_DELIMITER);

            Player currentPlayer = new Player();
            currentPlayer.setPlayerId(currentTokens[0]);
            currentPlayer.setPlayerFirstName(currentTokens[1]);
            currentPlayer.setPlayerLastName(currentTokens[2]);
            currentPlayer.setPlayersTeam(new Team(currentTokens[3]));
            playerMap.put(currentPlayer.getPlayerId(), currentPlayer);
        }
        scanner.close();
    }


    private void writePlayers() throws BaseballLeaguePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(filename));
        } catch(IOException e){
            throw new BaseballLeaguePersistenceException("Unable to save players to file.");
        }

        List<Player> allPlayers = this.retrieveAllPlayers();
        for(Player currentPlayer : allPlayers){
            out.println(currentPlayer.getPlayerId() + STRING_DELIMITER
                        + currentPlayer.getPlayerFirstName() + STRING_DELIMITER
                        + currentPlayer.getPlayerLastName() + STRING_DELIMITER
                        + currentPlayer.getPlayersTeam().getTeamId());
        }
        out.flush();
        out.close();
    }
    

}
