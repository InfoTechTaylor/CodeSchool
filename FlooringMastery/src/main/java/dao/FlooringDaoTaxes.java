package dao;

import dto.Tax;

import java.util.List;

public interface FlooringDaoTaxes {

    Tax retrieveTaxByState(String state);
    List<Tax> retrieveAllTaxes();
    void createTax(Tax taxObject);
    void updateTax(Tax taxObject);
    void removeTax(Tax taxObject);
}
