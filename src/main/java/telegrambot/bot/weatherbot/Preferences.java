package telegrambot.bot.weatherbot;

import java.io.Serializable;

/**
 * Created by atols on 31.07.2017.
 */
public class Preferences implements Serializable {

    private Integer cityId;
    private State state;

    public Preferences() {
        cityId = -1;
        state = State.UNKNOWN;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public enum State{
        UNKNOWN, NEWCITY, SELECTINGCITY, READY
    }
}
