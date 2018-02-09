package service;

import dao.VendingMachineDao;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao){
        this.dao = dao;
    }
}
