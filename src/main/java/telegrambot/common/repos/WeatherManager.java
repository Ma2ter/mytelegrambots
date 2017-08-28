package telegrambot.common.repos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import telegrambot.common.entities.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by atols on 27.07.2017.
 */
@SuppressWarnings("ALL")
public class WeatherManager {

    private final static String weatherWebAddress;
    private final static String weatherAPIToken;

    final static String CURRENT = "weather";
    final static String FORECAST = "forecast";

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream("app.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        weatherWebAddress = properties.getProperty("weatherWebAddress");
        weatherAPIToken = properties.getProperty("WeatherAPIToken");
    }

    private class QueryBuilder{

        private String type;
        private String cityid;
        Boolean firstArg = false;


        private StringBuilder resultQuery = new StringBuilder();
        private Set<String> args = new HashSet<>();

        void addArg(String arg){
            if(!firstArg){
                resultQuery.append("&");
            } else {
                firstArg = true;
            }
            resultQuery.append(arg);
        }

        QueryBuilder() {
            resultQuery.append(weatherWebAddress);
        }

        QueryBuilder setType(String type){
            resultQuery.insert(weatherWebAddress.length(), type.concat("?"));
            return this;
        }

        QueryBuilder setCityId(Integer cityId){
            addArg("id=" + cityId);
            return this;
        }

        String build(){
            addArg("APPID=" + weatherAPIToken);
            return resultQuery.toString();
        }
    }

    @SuppressWarnings("WeakerAccess")
    public WeatherManager(){

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private Result processQuery(String url, Result resultType){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Result resultWeather;
        try {
            resultWeather = mapper.readValue(new URL(url), resultType.getClass());
            return resultWeather;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("WeakerAccess")
    public WeatherResult getCurrentWeatherByCityId(Integer cityId){
        String query = new QueryBuilder().setType(CURRENT).setCityId(cityId).build();
        return (WeatherResult) processQuery(query, new WeatherResult());
    }

    @SuppressWarnings("WeakerAccess")
    public ForecastResult getForecastHourlyByCityId(Integer cityId){
        return (ForecastResult) processQuery(
                new QueryBuilder().setCityId(cityId).setType(FORECAST).build(),
                new ForecastResult()
        );
    }
    public static void main(String[] args){

        CityManager cityManager = CityManager.getInstance();
        WeatherManager weatherManager = new WeatherManager();

        City moscow = cityManager.findCitiesByName("Moscow").get(1);
        WeatherResult weatherResult = weatherManager.getCurrentWeatherByCityId(moscow.getId());
        ForecastResult forecastResult = weatherManager.getForecastHourlyByCityId(moscow.getId());
        System.out.println(weatherResult);
        System.out.println(forecastResult);
    }

}
