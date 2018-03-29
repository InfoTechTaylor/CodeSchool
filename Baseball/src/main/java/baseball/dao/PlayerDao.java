package baseball.dao;

import baseball.dto.Player;

public interface PlayerDao {

    public Player create(Player player);

    public Player read(Long id);

    public void update(Player player);

    public void delete(Player player);
}
