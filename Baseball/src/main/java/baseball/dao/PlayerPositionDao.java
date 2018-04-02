package baseball.dao;

import baseball.dto.PlayerPosition;
import baseball.dto.Position;

public interface PlayerPositionDao {

    public PlayerPosition create(PlayerPosition playerPosition);

    public PlayerPosition read(Long id);

    public void update(PlayerPosition playerPosition);

    public void delete(PlayerPosition playerPosition);
}
