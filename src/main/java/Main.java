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

        System.out.println("1");
        ApiContextInitializer.init();
        System.out.println("1");

        TelegramBotsApi botsApi = new TelegramBotsApi();
        System.out.println("1");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("1");

        InputStream is = classLoader.getResourceAsStream("app.properties");
        System.out.println("1");

        Properties properties = new Properties();
        System.out.println("1");

        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String rollerBotAPI = properties.getProperty("RollerBotAPI");
        String weatherBotAPI = properties.getProperty("WeatherBotAPI");

        RollerBot rollerBot = new RollerBot(rollerBotAPI);
        //WeatherBot weatherBot = null;
        //weatherBot = new WeatherBot(weatherBotAPI);
        try {
            System.out.println("Я СЕЙЧАС УПАДУ!");

            botsApi.registerBot(rollerBot);
            //botsApi.registerBot(weatherBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
