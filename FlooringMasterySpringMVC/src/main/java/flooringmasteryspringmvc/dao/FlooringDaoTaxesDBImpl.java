package flooringmasteryspringmvc.dao;

import flooringmasteryspringmvc.dto.Tax;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FlooringDaoTaxesDBImpl implements FlooringDaoTaxes{

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_TAX_BY_STATE
            = "select * from tax where state = ?";

    private static final String SQL_INSERT_TAX
            = "insert into tax (state, taxRate) "
            + "values (?, ?)";

    private static final String SQL_DELETE_TAX
            = "delete from tax where taxId = ?";

    private static final String SQL_UPDATE_TAX
            = "update tax set state = ?, taxRate = ? where taxId = ?";

    private static final String SQL_SELECT_ALL_TAXES
            = "select * from tax";

    @Override
    public Tax retrieveTaxByState(String state) throws FlooringPersistenceException {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_TAX_BY_STATE,
                    new taxMapper(),
                    state);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Tax> retrieveAllTaxes() throws FlooringPersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_TAXES,
                new taxMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Tax createTax(Tax taxObject) throws FlooringPersistenceException {
        jdbcTemplate.update(SQL_INSERT_TAX,
                taxObject.getState(),
                taxObject.getTaxRate());

        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);

        taxObject.setTaxId(newId);
        return taxObject;
    }

    @Override
    public void updateTax(Tax taxObject) throws FlooringPersistenceException {
        jdbcTemplate.update(SQL_UPDATE_TAX,
                taxObject.getState(),
                taxObject.getTaxRate(),
                taxObject.getTaxId());
    }

    @Override
    public void removeTax(Tax taxObject) throws FlooringPersistenceException {
        jdbcTemplate.update(SQL_DELETE_TAX, taxObject.getTaxId());
    }


    private static final class taxMapper implements RowMapper<Tax>{

        public Tax mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tax tax = new Tax();
            tax.setTaxId(rs.getInt("taxId"));
            tax.setState(rs.getString("state"));
            tax.setTaxRate(rs.getBigDecimal("taxRate"));

            return tax;
        }
    }
}
