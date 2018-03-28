package flooringmasteryspringmvc.dao;

public interface FlooringDaoAudit {

    void writeAuditEntry(String entry) throws FlooringPersistenceException;
}
