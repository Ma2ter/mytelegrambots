import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import telegrambot.bot.chatutilsbot.ChatUtilsBot;
import telegrambot.bot.rollerbot.RollerBot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class Main {

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
        String chatUtilsBotAPI = properties.getProperty("ChatUtilsBotAPI");

        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);

        RollerBot rollerBot = new RollerBot(rollerBotAPI);
        ChatUtilsBot chatUtilsBot = new ChatUtilsBot(chatUtilsBotAPI);
        //WeatherBot weatherBot = new WeatherBot(weatherBotAPI);
        try {
            //botsApi.registerBot(rollerBot);
            botsApi.registerBot(chatUtilsBot);
            //botsApi.registerBot(weatherBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
