package dao;

import dto.Team;

import java.io.*;
import java.util.*;

public class BaseballLeagueDaoTeamFileImpl implements BaseballLeagueDaoTeam {

    private Map<String, Team> teamMap = new HashMap<>();
    private static String filename;
    private static final String STRING_DELIMITER = "::";

    public BaseballLeagueDaoTeamFileImpl(String filename) {
        this.filename = filename;
    }


    @Override
    public Team createTeam(Team newTeam) throws BaseballLeaguePersistenceException{
        teamMap.put(newTeam.getTeamId(), newTeam);
        writeTeams();
        return newTeam;
    }

    @Override
    public Team retrieveTeamById(String playerId) throws BaseballLeaguePersistenceException{
        loadTeams();
        return teamMap.get(playerId);
    }

    @Override
    public List<Team> retrieveAllTeams() throws BaseballLeaguePersistenceException{
        loadTeams();
        return new ArrayList<>(teamMap.values());
    }

    @Override
    public Team updateTeam(Team updatedTeam) throws BaseballLeaguePersistenceException{
        teamMap.replace(updatedTeam.getTeamId(), updatedTeam);
        writeTeams();
        return updatedTeam;
    }

    @Override
    public Team removeTeam(String teamId) throws BaseballLeaguePersistenceException{
        Team teamToRemove = teamMap.remove(teamId);
        writeTeams();
        return teamToRemove;
    }

    private void loadTeams() throws BaseballLeaguePersistenceException{
        Scanner scanner;

        try{
            scanner = new Scanner(new BufferedReader(new FileReader(filename)));
        } catch (FileNotFoundException e){
            throw new BaseballLeaguePersistenceException("-_- Could not load team data into memory.", e);
        }

        // currentLine holds the most recent line read from the file
        String currentLine;

        //currentToken holds each of the parts of the currentLine after it has been split on our DELIMITER
        String[] currentTokens;

        // Go through each line in the file, decoding each into an object
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(STRING_DELIMITER);

            // create new team object, with id as the parameter
            Team currentTeam = new Team(currentTokens[0]);
            currentTeam.setTeamName(currentTokens[1]);
            currentTeam.setTeamLeague(currentTokens[2]);

            // put currentTeam in the map
            teamMap.put(currentTeam.getTeamId(), currentTeam);
        }
        scanner.close();
    }

    private void writeTeams() throws BaseballLeaguePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(filename));
        } catch(IOException e){
            throw new BaseballLeaguePersistenceException("Could not save team data");
        }

        List<Team> teamList = this.retrieveAllTeams();
        for(Team currentTeam : teamList){
            out.println(currentTeam.getTeamId() + STRING_DELIMITER
                            + currentTeam.getTeamName() + STRING_DELIMITER
                            + currentTeam.getTeamLeague());
            // force PrintWriter to write line to the file
            out.flush();
        }
        out.close();
    }
}
