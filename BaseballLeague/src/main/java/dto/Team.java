package dto;

import java.util.List;
import java.util.Objects;

public class Team {

    private String teamId;
    private String teamName;
    private String teamLeague;

    public Team(String teamId){
        this.teamId = teamId;
    }

    public Team(){

    }

    public String getTeamId() {
        return teamId;
    }

//    public void setTeamId(String teamId) {
//        this.teamId = teamId;
//    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLeague() {
        return teamLeague;
    }

    public void setTeamLeague(String teamLeague) {
        this.teamLeague = teamLeague;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(teamId, team.teamId) &&
                Objects.equals(teamName, team.teamName) &&
                Objects.equals(teamLeague, team.teamLeague);
    }

    @Override
    public int hashCode() {

        return Objects.hash(teamId, teamName, teamLeague);
    }
}
