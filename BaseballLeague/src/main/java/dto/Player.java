package dto;

public class Player {
    private String playerId;
    private String playerFirstName;
    private String playerLastName;
    private String playerPosition;
    private Team playersTeam;

    public Player(String playerId){
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

//    public void setPlayerId(String playerId) {
//        this.playerId = playerId;
//    }

    public String getPlayerFirstName() {
        return playerFirstName;
    }

    public void setPlayerFirstName(String playerFirstName) {
        this.playerFirstName = playerFirstName;
    }

    public String getPlayerLastName() {
        return playerLastName;
    }

    public void setPlayerLastName(String playerLastName) {
        this.playerLastName = playerLastName;
    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Team getPlayersTeam() {
        return playersTeam;
    }

    public void setPlayersTeam(Team playersTeam) {
        this.playersTeam = playersTeam;
    }
}
