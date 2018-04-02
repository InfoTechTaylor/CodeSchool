package baseball.service;

import baseball.dto.Player;
import baseball.dto.Position;
import baseball.dto.Team;
import javafx.geometry.Pos;

import java.util.List;

public interface PlayerService {

    public Player create(Player player);

    public Player read(Long id);

    public void update(Player player);

    public void delete(Player player);

    public List<Player> getPlayersByTeam(Team team, int limit, int offset);

    public List<Player> getPlayersByPosition(Position position, int limit, int offset);

//    public void addPlayerToPosition(Player player, Position position);
//
//    public void deletePlayerFromPosition(Player player, Position position);
}
