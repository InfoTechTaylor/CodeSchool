package baseball.dao;

import baseball.dto.Player;
import baseball.dto.Position;
import baseball.dto.Team;

import java.util.List;

public interface PositionDao {

    public Position create(Position position);

    public Position read(Long id);

    public void update(Position position);

    public void delete(Position position);

    public List<Position> list(int limit, int offset);

    public List<Position> getPositionsByPlayer(Player player, int limit, int offset);
}
