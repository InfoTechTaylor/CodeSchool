package service;

import dao.BaseballLeagueAuditDao;
import dao.BaseballLeagueDaoPlayerFileImpl;
import dao.BaseballLeagueDaoTeamFileImpl;

public class BaseballLeagueServiceLayerImpl implements BaseballLeagueServiceLayer {

    private BaseballLeagueDaoTeamFileImpl teamDao;
    private BaseballLeagueDaoPlayerFileImpl playerDao;
    private BaseballLeagueAuditDao auditDao;

    public BaseballLeagueServiceLayerImpl(BaseballLeagueDaoTeamFileImpl teamDao,
                                          BaseballLeagueDaoPlayerFileImpl playerDao, BaseballLeagueAuditDao auditDao) {
        this.teamDao = teamDao;
        this.playerDao = playerDao;
        this.auditDao = auditDao;
    }
}
