package baseball.commandmodel.team.createteam;

public class CreateTeamCommandModel {

    // validation annotations will go here
    private String city;
    private String nickname;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
