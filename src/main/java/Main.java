import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import sun.misc.IOUtils;
import telegrambot.bot.rollerbot.RollerBot;
import telegrambot.bot.weatherbot.WeatherBot;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by atols on 25.07.2017.
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream("app.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String rollerBotAPI = properties.getProperty("RollerBotAPI");
        String weatherBotAPI = properties.getProperty("WeatherBotAPI");

        RollerBot rollerBot = new RollerBot(rollerBotAPI);
        WeatherBot weatherBot = null;
        weatherBot = new WeatherBot(weatherBotAPI);
        try {
            botsApi.registerBot(rollerBot);
            botsApi.registerBot(weatherBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
