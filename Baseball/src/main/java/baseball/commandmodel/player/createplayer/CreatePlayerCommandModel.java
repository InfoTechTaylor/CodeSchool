package baseball.commandmodel.player.createplayer;

import org.hibernate.validator.constraints.NotEmpty;

public class CreatePlayerCommandModel {

    private String first;
    @NotEmpty(message="last name is required")
    private String last;
    private String hometown;
    private Long teamId;
    private Long[] positionIds;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long[] getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(Long[] positionIds) {
        this.positionIds = positionIds;
    }
}
