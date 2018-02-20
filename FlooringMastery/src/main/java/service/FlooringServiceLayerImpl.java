package service;

import dao.FlooringDaoOrder;
import dao.FlooringDaoProducts;
import dao.FlooringDaoTaxes;

public class FlooringServiceLayerImpl implements FlooringServiceLayer {

    private FlooringDaoOrder daoOrder;
    private FlooringDaoTaxes daoTaxes;
    private FlooringDaoProducts daoProducts;

    public FlooringServiceLayerImpl(FlooringDaoOrder daoOrder, FlooringDaoTaxes daoTaxes, FlooringDaoProducts daoProducts) {
        this.daoOrder = daoOrder;
        this.daoTaxes = daoTaxes;
        this.daoProducts = daoProducts;
    }
}
