package telegrambot.common.entities;

/**
 * Created by atols on 27.07.2017.
 * POJO for parsing from JSON via JACKSON
 *
 */
public class Temperature {

    private Double temperature;

    public Temperature() {
    }

    public Temperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getCelciusValue(){
        return temperature - 273;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
