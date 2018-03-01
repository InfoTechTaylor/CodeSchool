package controller;

import dao.BaseballLeaguePersistenceException;
import dto.Player;
import dto.Team;
import service.BaseballLeagueServiceLayer;
import service.PlayerNotFoundException;
import service.TeamNotFoundException;
import ui.BaseballLeagueView;

import java.util.List;

public class BaseballLeagueController {

    private BaseballLeagueView view;
    private BaseballLeagueServiceLayer service;

    public BaseballLeagueController(BaseballLeagueView view, BaseballLeagueServiceLayer service) {
        this.view = view;
        this.service = service;
    }


    public void run(){
        boolean isRunning = true;
        int userSelection = 0;

        displayOpeningBanner();

        while(isRunning){
            userSelection = displayMenuAndPromptUserForSelection();

            switch(userSelection){
                case 1:
                    createTeam();
                    break;
                case 2:
                    createPlayer();
                    break;
                case 3:
                    ListAllTeams();
                    break;
                case 4:
                    displayAllPlayersOnATeam();
                    break;
                case 5:
                    tradePlayers();
                    break;
                case 6:
                    removePlayer();
                    break;
                case 7:
                    removeTeam();
                    break;
                case 8:
                    isRunning = false;
                    displayExitMessage();
                    break;
            }
        }
    }

    private void displayOpeningBanner(){
        view.displayOpeningBanner();
    }

    private int displayMenuAndPromptUserForSelection(){
        return view.displayMenuAndPromptForSelection();
    }

    private void displayExitMessage(){
        view.displayExitMessage();
    }

    private void createTeam(){

        try {
            view.displaySubMenu("Create a Team");
            // get new team name & league from the user
            String teamName = view.promptForTeamName();
            String leagueName = view.promptForTeamLeague();

            // call service to create team
            Team newTeam = service.createTeam(teamName, leagueName);

            if(newTeam != null) {
                //display success
                view.displaySuccessCreateNewTeam(newTeam);
            }
        }catch (BaseballLeaguePersistenceException e){
            view.displayError(e.getMessage());
        }
        view.promptUserToHitEnterToContinue();

    }

    private void ListAllTeams(){
        try {
            view.displaySubMenu("Display All Teams");
            List<Team> allTeams = service.retrieveAllTeams();
            view.displayAllTeams(allTeams);
        } catch(BaseballLeaguePersistenceException e){
            view.displayError(e.getMessage());
        }
        view.promptUserToHitEnterToContinue();
    }

    private void createPlayer(){

        try {
            view.displaySubMenu("Create a Player");
            // get new player details from user
            Player newPlayer = view.promptForNewPlayerInfo();
            // pass to service
            service.createPlayer(newPlayer);
        } catch(BaseballLeaguePersistenceException | TeamNotFoundException e){
            view.displayError(e.getMessage());
        }
        view.promptUserToHitEnterToContinue();
    }

    private void displayAllPlayersOnATeam(){
        try {
            view.displaySubMenu("Display All Players");
            String teamName = view.promptForTeamName();
            List<Player> allPlayers = service.retrieveAllPlayersWithTeamName(teamName);
            view.displayAllPlayers(allPlayers);
        } catch(TeamNotFoundException | BaseballLeaguePersistenceException e){
            view.displayError(e.getMessage());
        }
        view.promptUserToHitEnterToContinue();
    }

    private void removePlayer(){
        try {
            view.displaySubMenu("Remove a Player");
            List<Player> allPlayers = service.retrieveAllPlayers();
            view.displayAllPlayers(allPlayers);
            String playerId = view.promptForPlayerId();
            service.removePlayer(playerId);
        } catch(PlayerNotFoundException | BaseballLeaguePersistenceException e){
            view.displayError(e.getMessage());
        }
        view.promptUserToHitEnterToContinue();

    }

    private void removeTeam(){
        try {
            view.displaySubMenu("Remove a Team");
            List<Team> allTeams = service.retrieveAllTeams();
            view.displayAllTeams(allTeams);
            String teamName = view.promptForTeamName();
            service.removeTeam(teamName);
        }catch(BaseballLeaguePersistenceException | TeamNotFoundException e){
            view.displayError(e.getMessage());
        }
        view.promptUserToHitEnterToContinue();

    }

    private void tradePlayers(){
        view.promptUserToHitEnterToContinue();
    }


}
