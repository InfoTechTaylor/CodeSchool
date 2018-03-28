package flooringmasteryspringmvc.dao;

public class ConfigDaoStubImpl implements ConfigDao {

    @Override
    public String generateOrderNumber() throws FlooringPersistenceException {
        return "1";
    }
}
