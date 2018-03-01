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
                "7. Remove a team\n" +
                "8. Exit\n" +
                "Your selection: ", 1, 8);
    }

    public void displayExitMessage(){
        io.print("Exiting application. Good bye.");
    }

    public void promptUserToHitEnterToContinue(){
        io.readString("\nHit Enter to continue: ");
    }

    public String promptForTeamName(){
        return io.readString("Please enter the name of a team: ");
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

    public void displaySubMenu(String menuSubTitle){
        io.print("================================================");
        io.print(menuSubTitle.toUpperCase());
        io.print("================================================");
    }

    public void displaySuccessCreateNewTeam(Team newTeam){
        io.print("Successfully created new team: " + newTeam.getTeamName() + " in the " + newTeam.getTeamLeague() + " league.");
    }

    public void displayAllTeams(List<Team> allTeams){
        io.print("Current Teams:");
        io.print("================================================");
        io.print(String.format("%-8s %-25s %-15s", "Team ID", "Team Name", "League"));
        io.print("------------------------------------------------");
        for(Team currentTeam : allTeams){
            if(currentTeam !=null) {
                io.print(String.format("%-8s %-25s %-15s", currentTeam.getTeamId(), currentTeam.getTeamName(), currentTeam.getTeamLeague()));
            }
        }
    }

    public Player promptForNewPlayerInfo(){
        String firstName = io.readString("\nEnter player's first name: ");
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

    public void displayAllPlayers(List<Player> allPlayers){
        for(Player currentPlayer : allPlayers){
            io.print("\nPlayer ID: " + currentPlayer.getPlayerId());
            io.print("First Name: " + currentPlayer.getPlayerFirstName());
            io.print("Last Name: " + currentPlayer.getPlayerLastName());
            io.print("Team: " + currentPlayer.getPlayersTeam().getTeamName());
        }
    }

    public String promptForPlayerId(){
        return io.readString("Enter player's ID: ");
    }

    public void displayBanner(String message){
        io.print(message);
    }

}
