package baseball.dao;

import baseball.dto.Player;
import baseball.dto.Position;
import baseball.dto.Team;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.lang.management.GarbageCollectorMXBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoDBImpl implements PlayerDao {

    // Injecting the JdbcTemplate
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT = "insert into player (first_name, last_name, home_town, team_id) "
            + "values (?, ?, ?, ?)";

    private static final String SQL_READ = "select * from player where id = ?";

    private static final String SQL_UPDATE = "update player set first_name = ?, last_name = ?, home_town = ?, "
            + "team_id = ? where id = ?";

    private static final String SQL_DELETE = "delete from player where id = ?";

    private static final String SQL_GET_PLAYERS_BY_TEAM = "select * from player where team_id = ? limit ? offset ?";

    private static final String SQL_GET_PLAYERS_BY_POSITION = "select * from player p " +
            "inner join player_position pp on " +
            "p.id = pp.player_id " +
            "where pp.position_id = ? limit ? offset ?";

    private static final String SQL_LIST = "SELECT * from player LIMIT ? OFFSET ?";

    @Inject
    public PlayerDaoDBImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Player create(Player player) {

        Long teamId = null;

        if (player.getTeam() != null) {
            teamId = player.getTeam().getId();
        }

        jdbcTemplate.update(SQL_INSERT,
                player.getFirstName(),
                player.getLastName(),
                player.getHomeTown(),
                teamId);

        long newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",
                Long.class);

        player.setId(newId);

        return player;
    }

    @Override
    public Player read(Long id) {
        try {
            return jdbcTemplate.queryForObject(SQL_READ,
                    new PlayerMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public void update(Player player) {

        Long teamId = null;

        if (player.getTeam() != null) {
            teamId = player.getTeam().getId();
        }


        jdbcTemplate.update(SQL_UPDATE,
                player.getFirstName(),
                player.getLastName(),
                player.getHomeTown(),
                teamId,
                player.getId());
    }

    @Override
    public void delete(Player player) {
        jdbcTemplate.update(SQL_DELETE,
                player.getId());
    }

    @Override
    public List<Player> getPlayersByTeam(Team team, int limit, int offset) {

        List<Player> players = jdbcTemplate.query(SQL_GET_PLAYERS_BY_TEAM,
                new PlayerMapper(),
                team.getId(),
                limit,
                offset);
        return players;
    }

    @Override
    public List<Player> getPlayersByPosition(Position position, int limit, int offset) {
        return jdbcTemplate.query(SQL_GET_PLAYERS_BY_POSITION,
                                    new PlayerMapper(),
                                    position.getId(),
                                    limit,
                                    offset);
    }

    @Override
    public List<Player> list(int limit, int offset) {

        return jdbcTemplate.query(SQL_LIST,
                new PlayerMapper(),
                limit,
                offset);
    }


    // mapper
    private static final class PlayerMapper implements RowMapper<Player> {

        public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
            Player player = new Player();
            player.setId(rs.getLong("id"));
            player.setFirstName(rs.getString("first_name"));
            player.setLastName(rs.getString("last_name"));
            player.setHomeTown(rs.getString("home_town"));

            Long teamId = rs.getLong("team_id");

            if(teamId != null) {
                Team team = new Team();
                team.setId(rs.getLong("team_id"));
                player.setTeam(team);
            }

            return player;
        }
    }

    // mapper
    private static final class PlayerMapperEagerFetchTeam implements RowMapper<Player> {

        public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
            Player player = new Player();
            player.setId(rs.getLong("p.id"));
            player.setFirstName(rs.getString("p.first_name"));
            player.setLastName(rs.getString("p.last_name"));
            player.setHomeTown(rs.getString("p.home_town"));

            Long teamId = rs.getLong("p.team_id");

            if(teamId != null) {
                Team team = new Team();
                team.setId(rs.getLong("team_id"));

                team.setCity(rs.getString("t.city"));
                team.setNickname(rs.getString("t.nickname"));

                player.setTeam(team);
            }

            return player;
        }
    }

}
