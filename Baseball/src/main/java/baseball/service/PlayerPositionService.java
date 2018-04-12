package baseball.service;

import baseball.dto.Player;
import baseball.dto.PlayerPosition;

import java.util.List;

public interface PlayerPositionService {
    public PlayerPosition create(PlayerPosition playerPosition);

    public PlayerPosition read(Long id);

    public void update(PlayerPosition playerPosition);

    public void delete(PlayerPosition playerPosition);

    public List<PlayerPosition> getPlayerPositionByPlayer(Player player, int limit, int offset);
}
