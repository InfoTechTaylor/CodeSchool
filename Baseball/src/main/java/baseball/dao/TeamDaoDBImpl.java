package baseball.dao;

import baseball.dto.Team;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamDaoDBImpl implements TeamDao {

    // Injecting the JdbcTemplate
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT = "insert into team (city, nickname) values (?,?)";

    private static final String SQL_READ = "select * from team where id = ?";

    private static final String SQL_UPDATE = "update team set city = ?, nickname = ? where id = ?";

    private static final String SQL_DELETE = "delete from team where id = ?";

    @Inject
    public TeamDaoDBImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Team create(Team team) {

        jdbcTemplate.update(SQL_INSERT,
                team.getCity(),
                team.getNickname());

        long newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",
                Long.class);

        team.setId(newId);

        return team;
    }

    @Override
    public Team read(Long id) {
        try {
            return jdbcTemplate.queryForObject(SQL_READ,
                    new teamMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public void update(Team team) {
        jdbcTemplate.update(SQL_UPDATE,
                team.getCity(),
                team.getNickname(),
                team.getId());
    }

    @Override
    public void delete(Team team) {
        jdbcTemplate.update(SQL_DELETE,
                team.getId());
    }


    // mapper
    private static final class teamMapper implements RowMapper<Team> {

        public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
            Team team = new Team();
            team.setId(rs.getLong("id"));
            team.setCity(rs.getString("city"));
            team.setNickname(rs.getString("nickname"));
            return team;
        }
    }

}
