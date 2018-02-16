package dao;


import dto.Team;

import java.util.List;

public interface BaseballLeagueDaoTeam {

    // C - CREATE
    Team createTeam(Team newTeam) throws BaseballLeaguePersistenceException;

    // R - READ
    Team retrieveTeamById(String playerId) throws BaseballLeaguePersistenceException;

    // R - retrieve all objects
    List<Team> retrieveAllTeams() throws BaseballLeaguePersistenceException;

    // U - update an Object
    Team updateTeam(Team updatedTeam) throws BaseballLeaguePersistenceException;

    // D - remove object
    Team removeTeam(String teamId) throws BaseballLeaguePersistenceException;
}
