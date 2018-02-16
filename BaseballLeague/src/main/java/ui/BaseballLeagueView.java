package ui;

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
        return io.readInt("\nOptions:\n" +
                "1. Create new team\n" +
                "2. Create new player\n" +
                "3. View all teams in the league\n" +
                "4. List all players on a team\n" +
                "5. Trade two players\n" +
                "6. Remove a player\n" +
                "7. Exit", 1, 7);
    }

    public void displayExitMessage(){
        io.print("Exiting application. Good bye.");
    }
}
