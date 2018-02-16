package dao;

public class BaseballLeagueDaoTeamFileImpl implements BaseballLeagueDao {

    private static String filename;

    public BaseballLeagueDaoTeamFileImpl(String filename) {
        this.filename = filename;
    }
}
