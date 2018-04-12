package baseball.dao;

import baseball.dto.Player;
import baseball.dto.Position;
import baseball.dto.Team;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PositionDaoDBImpl implements PositionDao {

    // Injecting the JdbcTemplate
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT = "insert into position (name) values (?)";

    private static final String SQL_READ = "select * from position where id = ?";

    private static final String SQL_UPDATE = "update position set name = ? where id = ?";

    private static final String SQL_DELETE = "delete from position where id = ?";

    private static final String SQL_GET_POSITIONS_BY_PLAYER = "select * from position p "
                                + "inner join player_position pp on p.id = pp.position_id "
                                + "where pp.player_id = ? limit ? offset ?";

    private static final String SQL_LIST = "select * from position limit ? offset ?";

    @Inject
    public PositionDaoDBImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Position create(Position position) {


        jdbcTemplate.update(SQL_INSERT,
                position.getName());

        long newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",
                Long.class);

        position.setId(newId);

        return position;
    }

    @Override
    public Position read(Long id) {
        try {
            return jdbcTemplate.queryForObject(SQL_READ,
                    new PositionMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public void update(Position position) {
        jdbcTemplate.update(SQL_UPDATE,
                position.getName(),
                position.getId());
    }

    @Override
    public void delete(Position position) {
        jdbcTemplate.update(SQL_DELETE,
                position.getId());
    }

    @Override
    public List<Position> list(int limit, int offset) {
        return jdbcTemplate.query(SQL_LIST,
                new PositionMapper(),
                limit,
                offset);
    }

    @Override
    public List<Position> getPositionsByPlayer(Player player, int limit, int offset) {
        return jdbcTemplate.query(SQL_GET_POSITIONS_BY_PLAYER,
                new PositionMapper(),
                player.getId(),
                limit,
                offset);
    }


    // mapper
    private static final class PositionMapper implements RowMapper<Position> {

        public Position mapRow(ResultSet rs, int rowNum) throws SQLException {
            Position position = new Position();
            position.setId(rs.getLong("id"));
            position.setName(rs.getString("name"));
            return position;
        }
    }

}
