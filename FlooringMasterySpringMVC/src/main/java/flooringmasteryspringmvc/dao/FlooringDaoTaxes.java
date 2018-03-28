package flooringmasteryspringmvc.dao;



import flooringmasteryspringmvc.dto.Tax;

import java.util.List;

public interface FlooringDaoTaxes {

    Tax retrieveTaxByState(String state) throws FlooringPersistenceException;
    List<Tax> retrieveAllTaxes() throws FlooringPersistenceException;
    Tax createTax(Tax taxObject) throws FlooringPersistenceException;
    void updateTax(Tax taxObject) throws FlooringPersistenceException;
    void removeTax(Tax taxObject) throws FlooringPersistenceException;
}
