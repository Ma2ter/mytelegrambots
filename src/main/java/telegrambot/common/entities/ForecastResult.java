package telegrambot.common.entities;

import java.util.List;

/**
 * Created by atols on 28.07.2017.
 */
public class ForecastResult extends Result {

    private int cod;

    @Override
    public String toString() {
        return "ForecastResult{" +
                "cod=" + cod +
                ", message='" + message + '\'' +
                ", cnt=" + cnt +
                ", list=" + list +
                ", city=" + city +
                ", country='" + country + '\'' +
                '}';
    }

    private String message;
    private int cnt;

    private List<WeatherResult> list;
    private City city;

    public ForecastResult() {
    }

    public ForecastResult(int cod, String message, int cnt, List<WeatherResult> list, City city, String country) {
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
        this.city = city;
        this.country = country;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherResult> getList() {
        return list;
    }

    public void setList(List<WeatherResult> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String country;
}
