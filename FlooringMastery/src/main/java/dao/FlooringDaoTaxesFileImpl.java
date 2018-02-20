package dao;

import dto.Tax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlooringDaoTaxesFileImpl implements FlooringDaoTaxes {

    private Map<String, Tax> taxRateByStateMap = new HashMap<>();

    @Override
    public Tax retrieveTaxByState(String state) {
        return null;
    }

    @Override
    public List<Tax> retrieveAllTaxes() {
        return null;
    }

    @Override
    public void createTax(Tax taxObject) {

    }

    @Override
    public void updateTax(Tax taxObject) {

    }

    @Override
    public void removeTax(Tax taxObject) {

    }

    // load taxes into map
    private void loadTaxes(){
        Scanner scanner;

    }
}
