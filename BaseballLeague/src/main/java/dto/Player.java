package dto;

import java.util.Objects;

public class Player {
    private String playerId;
    private String playerFirstName;
    private String playerLastName;
    private Team playersTeam;

    public Player(String playerId){
        this.playerId = playerId;
    }
    public Player(){

    }

    public String getPlayerId() {
        return playerId;
    }



    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

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

    public Team getPlayersTeam() {
        return playersTeam;
    }

    public void setPlayersTeam(Team playersTeam) {
        this.playersTeam = playersTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerId, player.playerId) &&
                Objects.equals(playerFirstName, player.playerFirstName) &&
                Objects.equals(playerLastName, player.playerLastName) &&
                Objects.equals(playersTeam, player.playersTeam);
    }

    @Override
    public int hashCode() {

        return Objects.hash(playerId, playerFirstName, playerLastName, playersTeam);
    }
}
