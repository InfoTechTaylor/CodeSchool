package baseball.service;

import baseball.dao.PlayerDao;
import baseball.dto.Player;
import baseball.dto.Position;
import baseball.dto.Team;

import javax.inject.Inject;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {

    private PlayerDao playerDao;

    @Inject
    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public Player create(Player player) {
        return playerDao.create(player);
    }

    @Override
    public Player read(Long id) {
        return playerDao.read(id);
    }

    @Override
    public void update(Player player) {
        playerDao.update(player);
    }

    @Override
    public void delete(Player player) {
        playerDao.delete(player);
    }

    @Override
    public List<Player> getPlayersByTeam(Team team, int limit, int offset) {
        return playerDao.getPlayersByTeam(team, limit, offset);
    }

    @Override
    public List<Player> getPlayersByPosition(Position position, int limit, int offset) {
        return playerDao.getPlayersByPosition(position, limit, offset);
    }


}
