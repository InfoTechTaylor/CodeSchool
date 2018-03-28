package addressbookspringmvc.dao;

import addressbookspringmvc.dto.Address;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressBookDaoDBImpl implements AddressBookDao {

    private static final String SQL_INSERT_CONTACT
            = "insert into contact "
            + "(firstName, lastName, streetAddress, city, state, zip) "
            + "values (?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_CONTACT
            = "delete from contact where contactID = ?";

    private static final String SQL_SELECT_CONTACT
            = "select * from contact where contactID = ?";

    private static final String SQL_UPDATE_CONTACT
            = "update contact set "
            + "firstName = ?, lastName = ?, streetAddress = ?, "
            + "city = ?, state = ?, zip = ? "
            + "where contactID = ?";

    private static final String SQL_SELECT_ALL_CONTACTS
            = "select * from contact";

    private static final String SQL_SELECT_CONTACTS_BY_LAST_NAME
            = "select * from contact where lastName = ?";


    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public AddressBookDaoDBImpl(JdbcTemplate jdbcTemplate){
//        this.jdbcTemplate = jdbcTemplate;
//    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Address addAddress(String lastName, Address address) {
        jdbcTemplate.update(SQL_INSERT_CONTACT,
                address.getFirstName(),
                address.getLastName(),
                address.getStreetAddress(),
                address.getCity(),
                address.getState(),
                address.getZip());

        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);

        address.setAddressId(newId);
        return address;
    }

    @Override
    public Address deleteAddress(int addressId) {
        jdbcTemplate.update(SQL_DELETE_CONTACT, addressId);
        return null;
    }

    @Override
    public Address getAddress(String lastName) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CONTACTS_BY_LAST_NAME,
                    new AddressMapper(), lastName);
        } catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public int getAddressCount() {
        return getAllAddresses().size();
    }

    @Override
    public List<Address> getAllAddresses() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CONTACTS,
                new AddressMapper());
    }


    private static final class AddressMapper implements RowMapper<Address> {

        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address address = new Address();
            address.setAddressId(rs.getInt("contactID"));
            address.setFirstName(rs.getString("firstName"));
            address.setLastName(rs.getString("lastName"));
            address.setStreetAddress(rs.getString("streetAddress"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setZip(rs.getString("zip"));

            return address;
        }
    }
}
