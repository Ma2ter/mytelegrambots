package telegrambot.bot;

import org.telegram.telegrambots.api.methods.send.SendAudio;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Audio;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import telegrambot.core.api.Request;
import telegrambot.common.reflection.ControllerHandler;

/**
 * Created by atols on 25.08.2017.
 * Абстрактная сущность бота на основе TelegramLongPollingBot'ов
 * Классы-предки реализуют методы getBotUsername() и onClosing()
 * API-токен передается в конструктор из конструктора класса-предка
 *
 */
public abstract class BaseBot extends TelegramLongPollingBot {

    //API-токен бота
    private final String API;
    //Экземпляр-singleton класса-обработчика запросов.
    private final ControllerHandler controllerHandler = ControllerHandler.getInstance();



    @Override
    public void onUpdateReceived(Update update) {
        //Отправка экземпляра запроса (контекста) в экземпляр-обработчик
        controllerHandler.resolveQuery(update, this);
    }

    protected BaseBot(String API){
        this.API = API;
    }

    @Override
    public String getBotToken() {
        return API;
    }
}
