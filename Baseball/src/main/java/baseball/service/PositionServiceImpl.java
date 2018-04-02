package baseball.service;

import baseball.dao.PositionDao;
import baseball.dto.Player;
import baseball.dto.Position;

import javax.inject.Inject;
import java.util.List;

public class PositionServiceImpl implements PositionService {

    PositionDao positionDao;

    @Inject
    public void PositionServiceImpl(PositionDao positionDao){
        this.positionDao = positionDao;
    }

    @Override
    public Position create(Position position) {
        return positionDao.create(position);
    }

    @Override
    public Position read(Long id) {
        return positionDao.read(id);
    }

    @Override
    public void update(Position position) {
        positionDao.update(position);
    }

    @Override
    public void delete(Position position) {
        positionDao.delete(position);
    }

    @Override
    public List<Position> getPositionsByPlayer(Player player, int limit, int offset) {
        return positionDao.getPositionsByPlayer(player, limit, offset);
    }

}
