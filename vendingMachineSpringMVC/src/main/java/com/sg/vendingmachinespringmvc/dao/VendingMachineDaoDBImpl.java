package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.VendingMachineItem;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VendingMachineDaoDBImpl implements VendingMachineDao {

    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from VendingMachineItem";

    private static final String SQL_UPDATE_ITEM
            = "update VendingMachineItem set "
            + "itemName = ?, itemCost = ?, itemQuantity = ? "
            + "where itemID = ?";

    private static final String SQL_SELECT_ITEM_BY_ID
            = "select * from VendingMachineItem "
            + "where itemID = ?";

    private static final String SQL_DELETE_ITEM
            = "delete from VendingMachineItem "
            + "where itemID = ?";

    private static final String SQL_INSERT_ITEM
            = "insert into VendingMachineItem "
            + "(itemName, itemCost, itemQuantity) "
            + "values (?, ?, ?)";


    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS,
                new ItemMapper());
    }

    @Override
    public void updateItem(VendingMachineItem item) throws VendingMachinePersistenceException {
        jdbcTemplate.update(SQL_UPDATE_ITEM,
                item.getItemName(),
                item.getItemCost(),
                item.getItemQuantity(),
                item.getItemId());
    }

    @Override
    public VendingMachineItem retrieveItemById(VendingMachineItem item) throws VendingMachinePersistenceException {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM_BY_ID,
                    new ItemMapper(),
                    item.getItemId());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public VendingMachineItem removeVendingMachineItem(VendingMachineItem item) throws VendingMachinePersistenceException {
        jdbcTemplate.update(SQL_DELETE_ITEM, item.getItemId());
        return item;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public VendingMachineItem createVendingMachineItem(VendingMachineItem item) throws VendingMachinePersistenceException {

        jdbcTemplate.update(SQL_INSERT_ITEM,
                item.getItemName(),
                item.getItemCost(),
                item.getItemQuantity());

        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        item.setItemId(newId);
        return item;
    }



    private static final class ItemMapper implements RowMapper<VendingMachineItem> {

        public VendingMachineItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            VendingMachineItem item = new VendingMachineItem();
            item.setItemId(rs.getInt("itemID"));
            item.setItemName(rs.getString("itemName"));
            item.setItemCost(rs.getBigDecimal("itemCost"));
            item.setItemQuantity(rs.getInt("itemQuantity"));

            return item;
        }
    }
}
