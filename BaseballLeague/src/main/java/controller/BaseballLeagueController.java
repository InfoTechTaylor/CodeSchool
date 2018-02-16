package controller;

import dao.BaseballLeaguePersistenceException;
import dto.Team;
import service.BaseballLeagueServiceLayer;
import ui.BaseballLeagueView;

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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
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
            // get new team name & league from the user
            String teamName = view.promptForTeamName();
            String leagueName = view.promptForTeamLeague();

            // call service to create team
            Team newTeam = service.createTeam(teamName, leagueName);

            if(newTeam != null) {
                //display success
                view.displaySuccessCreateNewTeam(newTeam);
            }

            view.promptUserToHitEnterToContinue();

        }catch (BaseballLeaguePersistenceException e){
            view.displayError(e.getMessage());
        }


    }
}
