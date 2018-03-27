package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

public class DvdLibraryDaoDbImpl implements DvdLibraryDao {

    // prepared statements
    private static final String SQL_INSERT_DVD
            = "INSERT INTO dvd "
            + "(dvdTitle, releaseDate, director, mpaaRating, notes) "
            + "values (?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_DVD
            = "DELETE FROM dvd WHERE dvdID = ?";

    private static final String SQL_SELECT_DVD
            = "SELECT * from dvd WHERE dvdID = ?";

    private static final String SQL_UPDATE_DVD
            = "UPDATE dvd SET "
            + "dvdTitle = ?, releaseDate = ?, director = ?, mpaaRating = ?, notes = ? "
            + "WHERE dvdID = ?";

    private static final String SQL_SELECT_ALL_DVDS
            = "SELECT * FROM dvd";

    private static final String SQL_SELECT_DVDS_BY_TITLE
            = "SELECT * FROM dvd WHERE title = ?";

    private static final String SQL_SELECT_DVDS_BY_RELEASE_DATE
            = "SELECT * FROM dvd WHERE releaseDate = ?";

    private static final String SQL_SELECT_DVDS_BY_DIRECTOR
            = "SELECT * FROM dvd WHERE director = ?";

    private static final String SQL_SELECT_DVDS_BY_MPAARATING
            = "SELECT * FROM dvd WHERE mpaaRating = ?";

    // Injecting the JdbcTemplate
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // interface methods

    @Override
    public Dvd getDvd(int dvdId) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD,
                    new DvdMapper(), dvdId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS,
                new DvdMapper());
    }

    @Override
    public void removeDvd(int dvdId) {
        jdbcTemplate.update(SQL_DELETE_DVD, dvdId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd addDvd(Dvd dvd) {
        Date date = Date.valueOf(dvd.getReleaseDate());
        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getTitle(),
                date,
                dvd.getDirector(),
                dvd.getRating(),
                dvd.getNotes());

        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",
                Integer.class);

        dvd.setDvdId(newId);
        return dvd;
    }

    @Override
    public Dvd editDvd(Dvd dvd) {
        Date date = Date.valueOf(dvd.getReleaseDate());
        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getTitle(),
                date,
                dvd.getDirector(),
                dvd.getRating(),
                dvd.getNotes(),
                dvd.getDvdId());
        return dvd;
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        StringBuilder sQuery;
        String paramVal;

        if (criteria.isEmpty()) {
            return getAllDvds();
        } else {
            // build a prepared statement based on the user's search terms
            sQuery =
                    new StringBuilder("select * from dvd where ");
            // build the where clause
            Set<SearchTerm> keySet = criteria.keySet();
                SearchTerm searchCategory = keySet.iterator().next();
                sQuery.append(searchCategory + " = ?");
                paramVal = criteria.get(searchCategory);
            }

            return jdbcTemplate.query(sQuery.toString(),
                    new DvdMapper(),
                    paramVal);
        }



    // Mapper nested class
    private static final class DvdMapper implements RowMapper<Dvd> {
        public Dvd mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setDvdId(rs.getInt("dvdID"));
            dvd.setTitle(rs.getString("dvdTitle"));
            dvd.setDirector(rs.getString("director"));
            dvd.setRating(rs.getString("mpaaRating"));
            dvd.setReleaseDate((rs.getTimestamp("releaseDate")
                    .toLocalDateTime().toLocalDate()));
            dvd.setNotes(rs.getString("notes"));
            return dvd;
        }
    }
}
