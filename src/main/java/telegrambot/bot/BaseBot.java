package telegrambot.bot;

import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.generics.LongPollingBot;
import telegrambot.bot.api.Request;
import telegrambot.common.reflection.ControllerHandler;

/**
 * Created by atols on 25.08.2017.
 */
public abstract class BaseBot extends TelegramLongPollingBot {

    protected final String API;
    private ControllerHandler controllerHandler = ControllerHandler.getInstance();


    public BaseBot(String API){
        this.API = API;
    }

    @Override
    public void onUpdateReceived(Update update) {
        controllerHandler.resolveQuery(new Request(update, this));
    }

    @Override
    public String getBotToken() {
        return API;
    }
}
