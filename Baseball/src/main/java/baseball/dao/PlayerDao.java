package baseball.dao;

import baseball.dto.Player;
import baseball.dto.Position;
import baseball.dto.Team;

import java.util.List;

public interface PlayerDao {

    public Player create(Player player);

    public Player read(Long id);

    public void update(Player player);

    public void delete(Player player);

    public List<Player> getPlayersByTeam(Team team, int limit, int offset);
    // limit is how many to get, offset is where in the result set to start, offset is how many you're going to skip

    public List<Player> getPlayersByPosition(Position position, int limit, int offset);


}
