package controller;

import service.BaseballLeagueServiceLayer;
import ui.BaseballLeagueView;

public class BaseballLeagueController {

    private BaseballLeagueView view;
    private BaseballLeagueServiceLayer service;

    public BaseballLeagueController(BaseballLeagueView view, BaseballLeagueServiceLayer service) {
        this.view = view;
        this.service = service;
    }
}
