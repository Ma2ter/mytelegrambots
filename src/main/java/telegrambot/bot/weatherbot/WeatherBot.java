package telegrambot.bot.weatherbot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import telegrambot.common.entities.City;
import telegrambot.common.repos.CityManager;

import java.util.*;

/**
 * Created by atols on 27.07.2017.
 */
public class WeatherBot extends TelegramLongPollingBot {





    private final String API;
    private Map<Long, Preferences> userList;
    private Map<Long, List<City>> multiCache;
    public static final String START_COMMAND = "/start";
    public static final String STOP_COMMAND = "/stop";
    public static final String SELECT_CITY_MESSAGE = "Please, reply with a name of City";
    public static final String MULTIPLY_CITY_MESSAGE = "Please, choose one of those cities (answer this message with number):";


    public WeatherBot(String API) {
        this.API = API;
        userList = AutoSerializableMap.load("savefile.txt");
        multiCache = new HashMap<>();
    }

    @Override
    public String getBotUsername() {
        return "M2WeatherBot";
    }

    @Override
    public String getBotToken() {
        return API;
    }

    @Override
    public void onClosing() {

    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update != null && update.hasMessage()){
            Message message = update.getMessage();
            if(message.hasText()){
                try {
                    handleIncomingQuery(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void handleIncomingQuery(Message message) throws TelegramApiException {
        Preferences userPref = userList.getOrDefault(message.getChatId(), new Preferences());

        switch (userPref.getState()){
            case UNKNOWN:
                start(message, userPref);
                break;
            case NEWCITY:
                if(message.getReplyToMessage() != null && message.getReplyToMessage().getText().equals(SELECT_CITY_MESSAGE))
                    findCity(message, userPref);
                break;
            case SELECTINGCITY:
                if(message.getReplyToMessage() != null && message.getReplyToMessage().getText().startsWith(MULTIPLY_CITY_MESSAGE))
                    defineCity(message, userPref);
                break;
        }


        if(message.getReplyToMessage() != null && userPref.getState() == Preferences.State.SELECTINGCITY && message.getReplyToMessage().getText().startsWith(MULTIPLY_CITY_MESSAGE)){

        }


    }

    private void start(Message message, Preferences userPref) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage().setChatId(message.getChatId());
        sendMessage.setText(SELECT_CITY_MESSAGE);
        sendMessage(sendMessage);
        userPref.setState(Preferences.State.NEWCITY);
        userList.put(message.getChatId(), userPref);
    }

    private void findCity(Message message, Preferences userPref) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage().setChatId(message.getChatId());
        List<City> cities = CityManager.getInstance().findCitiesByName(message.getText());
        if(cities.size() < 1){
            sendMessage(sendMessage.setText("City not found. Please try again"));
            sendMessage(sendMessage.setText(SELECT_CITY_MESSAGE));
            userPref.setState(Preferences.State.NEWCITY);
            return;
        } else if(cities.size() == 1){
            sendMessage(sendMessage.setText("Choosen city:" + cities.get(0).toString()));
            userPref.setCityId(cities.get(0).getId());
        } else {
            StringBuilder answer = new StringBuilder();
            answer.append(MULTIPLY_CITY_MESSAGE + "\n");
            int i = 0;
            for(City city : cities){
                answer.append(String.format("%s. %s, %s - {%s, %s}\n", i++, city.getCountry(), city.getName(), city.getCoord().getLat(), city.getCoord().getLon() ));
            }
            sendMessage(sendMessage.setText(answer.toString()));
            userPref.setState(Preferences.State.SELECTINGCITY);
            userList.put(message.getChatId(), userPref);
            multiCache.put(message.getChatId(), cities);
        }
    }

    private void defineCity(Message message, Preferences userPref) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage().setChatId(message.getChatId());
        Integer cityNum = Integer.parseInt(message.getText());
        City city = multiCache.get(message.getChatId()).get(cityNum);
        userPref.setCityId(city.getId());
        userPref.setState(Preferences.State.READY);
        userList.put(message.getChatId(), userPref);
        multiCache.remove(message.getChatId());
        sendMessage(sendMessage.setText(String.format("City was successfully chosen. %s, %s - {%s, %s}\n", city.getCountry(), city.getName(), city.getCoord().getLat(), city.getCoord().getLon() )));
    }
}
