package controller;

import service.FlooringServiceLayer;
import ui.FlooringView;

public class FlooringController {

    private FlooringView view;
    private FlooringServiceLayer service;

    public FlooringController(FlooringView view, FlooringServiceLayer service){
        this.view = view;
        this.service = service;
    }
}
