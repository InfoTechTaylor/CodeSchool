package baseball.dao;

import baseball.dto.Player;
import baseball.dto.PlayerPosition;
import baseball.dto.Position;
import baseball.dto.Team;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerPositionDaoImpl implements PlayerPositionDao {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT = "insert into player_position (player_id, position_id) values (?,?)";

    private static final String SQL_READ = "select * from player_position where id = ?";

    private static final String SQL_UPDATE = "update player_position set player_id = ?, position_id = ? where id = ?";

    private static final String SQL_DELETE = "delete from player_position where id = ?";

    @Inject
    public PlayerPositionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public PlayerPosition create(PlayerPosition playerPosition) {

        Long positionId = null;
        Long playerId = null;

        if(playerPosition.getPlayer() != null){
            playerId = playerPosition.getPlayer().getId();
        }

        if(playerPosition.getPosition() != null){
            positionId = playerPosition.getPosition().getId();
        }

        jdbcTemplate.update(SQL_INSERT,
                playerId,
                positionId);

        long newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",
                Long.class);

        playerPosition.setId(newId);

        return playerPosition;
    }

    @Override
    public PlayerPosition read(Long id) {
        try {
            return jdbcTemplate.queryForObject(SQL_READ,
                    new teamMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public void update(PlayerPosition playerPosition) {
        Long positionId = null;
        Long playerId = null;

        if(playerPosition.getPlayer() != null){
            playerId = playerPosition.getPlayer().getId();
        }

        if(playerPosition.getPosition() != null){
            positionId = playerPosition.getPosition().getId();
        }


        jdbcTemplate.update(SQL_UPDATE,
                playerId,
                positionId,
                playerPosition.getId());
    }

    @Override
    public void delete(PlayerPosition playerPosition) {
        jdbcTemplate.update(SQL_DELETE,
                playerPosition.getId());
    }


    // mapper
    private static final class teamMapper implements RowMapper<PlayerPosition> {

        public PlayerPosition mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlayerPosition playerPosition = new PlayerPosition();

            playerPosition.setId(rs.getLong("id"));

            // lazy load player
            Long playerId = rs.getLong("player_id");

            if(playerId != null) {
                Player player = new Player();
                player.setId(playerId);
                playerPosition.setPlayer(player);
            }

            // lazy load position
            Long positionId = rs.getLong("position_id");

            if(positionId != null){
                Position position = new Position();
                position.setId(positionId);
                playerPosition.setPosition(position);
            }

            return playerPosition;
        }
    }
}
