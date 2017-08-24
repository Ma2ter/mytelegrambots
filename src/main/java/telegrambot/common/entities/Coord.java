package telegrambot.common.entities;

/**
 * Created by atols on 27.07.2017.
 */
public class Coord {

    private Integer lon;
    private Integer lat;

    public Coord(Integer lon, Integer lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Coord() {
    }

    public Integer getLon() {
        return lon;
    }

    public void setLon(Integer lon) {
        this.lon = lon;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
