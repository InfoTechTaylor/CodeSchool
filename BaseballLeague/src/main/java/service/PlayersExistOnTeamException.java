package service;

public class PlayersExistOnTeamException extends Exception {

    public PlayersExistOnTeamException(String message) {
        super(message);
    }

    public PlayersExistOnTeamException(String message, Throwable cause) {
        super(message, cause);
    }
}
