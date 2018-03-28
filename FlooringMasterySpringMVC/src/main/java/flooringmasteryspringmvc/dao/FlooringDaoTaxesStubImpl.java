package flooringmasteryspringmvc.dao;



import flooringmasteryspringmvc.dto.Tax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FlooringDaoTaxesStubImpl implements FlooringDaoTaxes {

    Tax onlyTaxObject;
    List<Tax> allTaxes = new ArrayList<>();

    public FlooringDaoTaxesStubImpl(){
        onlyTaxObject = new Tax("NH", new BigDecimal("3.5"));
        allTaxes.add(onlyTaxObject);
    }

    @Override
    public Tax retrieveTaxByState(String state) throws FlooringPersistenceException {
        if(state.equals(onlyTaxObject.getState())){
            return onlyTaxObject;
        } else {
            return null;
        }
    }

    @Override
    public List<Tax> retrieveAllTaxes() throws FlooringPersistenceException {
        return allTaxes;
    }

    @Override
    public Tax createTax(Tax taxObject) throws FlooringPersistenceException {
        return null;
    }

    @Override
    public void updateTax(Tax taxObject) throws FlooringPersistenceException {

    }

    @Override
    public void removeTax(Tax taxObject) throws FlooringPersistenceException {

    }
}
