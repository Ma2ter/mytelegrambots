package telegrambot.bot.weatherbot;

import telegrambot.common.entities.Weather;

/**
 * Created by atols on 31.07.2017.
 */
public class WeatherBotManager {

    private static WeatherBotManager instance;


    private WeatherBotManager(){

    }

    public WeatherBotManager getInstance(){
        if(instance == null){
            instance = new WeatherBotManager();
        }
        return instance;
    }



}
