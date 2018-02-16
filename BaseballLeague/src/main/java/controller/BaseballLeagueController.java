package controller;

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
}
