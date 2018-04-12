package baseball.dao;

import baseball.dto.Player;
import baseball.dto.PlayerPosition;
import baseball.dto.Position;

import java.util.List;

public interface PlayerPositionDao {

    public PlayerPosition create(PlayerPosition playerPosition);

    public PlayerPosition read(Long id);

    public void update(PlayerPosition playerPosition);

    public void delete(PlayerPosition playerPosition);

    public List<PlayerPosition> getPlayerPositionByPlayer(Player player, int limit, int offset);
}
