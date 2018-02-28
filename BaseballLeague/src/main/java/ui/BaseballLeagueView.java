package ui;

import dto.Player;
import dto.Team;

import java.util.List;

public class BaseballLeagueView {

    UserIO io;

    public BaseballLeagueView(UserIO io) {
        this.io = io;
    }

    public void displayOpeningBanner(){
        io.print("================================================");
        io.print("             BASEBALL LEAGUE");
        io.print("================================================");
    }

    public int displayMenuAndPromptForSelection(){
        return io.readInt("\nOPTIONS:\n" +
                "================================================\n" +
                "1. Create new team\n" +
                "2. Create new player\n" +
                "3. View all teams in the league\n" +
                "4. List all players on a team\n" +
                "5. Trade two players\n" +
                "6. Remove a player\n" +
                "7. Exit\n" +
                "Your selection: ", 1, 7);
    }

    public void displayExitMessage(){
        io.print("Exiting application. Good bye.");
    }

    public void promptUserToHitEnterToContinue(){
        io.readString("Hit Enter to continue: ");
    }

    public String promptForTeamName(){
        return io.readString("Please enter the name of the team you want to create: ");
    }

    public String promptForTeamLeague(){
        return io.readString("Please enter the league name of the new team: ");
    }

    public void displayError(String message){
        io.print("ERROR");
        io.print("================================================");
        io.print(message);
        io.print("================================================");
    }

    public void displaySuccessCreateNewTeam(Team newTeam){
        io.print("Successfully created new team: " + newTeam.getTeamName() + " in the " + newTeam.getTeamLeague() + ".");
    }

    public void displayAllTeams(List<Team> allTeams){
        io.print("Current Teams:");
        io.print("================================================");
        for(Team currentTeam : allTeams){
            if(currentTeam !=null) {
                io.print(currentTeam.getTeamName());
            }
        }
    }

    public Player promptForNewPlayerInfo(){
        io.print("NEW PLAYER CREATION");
        io.print("================================================");
        String firstName = io.readString("Enter player's first name: ");
        String lastName = io.readString("Enter player's last name: ");
        String teamName = io.readString("Enter the player's team: ");
        Team teamObj = new Team();
        teamObj.setTeamName(teamName);
        Player newPlayer = new Player();
        newPlayer.setPlayerFirstName(firstName);
        newPlayer.setPlayerLastName(lastName);
        newPlayer.setPlayersTeam(teamObj);

        return newPlayer;

    }


}
