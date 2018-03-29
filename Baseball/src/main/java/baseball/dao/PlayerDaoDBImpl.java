package baseball.dao;

import baseball.dto.Player;
import baseball.dto.Team;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDaoDBImpl implements PlayerDao {

    // Injecting the JdbcTemplate
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT = "insert into player (first_name, last_name, home_town, team_id) "
            + "values (?, ?, ?, ?)";

    private static final String SQL_READ = "select * from player where id = ?";

    private static final String SQL_UPDATE = "update player set first_name = ?, last_name = ?, home_town = ?, "
            + "team_id = ? where id = ?";

    private static final String SQL_DELETE = "delete from player where id = ?";

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
                    new playerMapper(),
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


    // mapper
    private static final class playerMapper implements RowMapper<Player> {

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

}
