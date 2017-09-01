package telegrambot.core.api;

import org.telegram.telegrambots.api.interfaces.BotApiObject;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.ForwardMessage;
import org.telegram.telegrambots.api.methods.send.*;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import telegrambot.bot.BaseBot;

import java.lang.reflect.Method;

/**
 * Created by atols on 27.07.2017.
 * Абстрактная сущность для хранения результатов выполнения программы, конечных или промежуточных, произвольного типа данных
 *
 */
public class OutputHandler {

    private BaseBot sender;

    private Long chatId;

    public OutputHandler(BaseBot sender, Update update){
        this.sender = sender;
        this.chatId = update.getMessage().getChatId();
    }


    public void send(String message){
        try {
            sender.execute(new SendMessage().setText(message).setChatId(chatId));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void send(String message, Long chatId){
        try {
            sender.execute(new SendMessage().setText(message).setChatId(chatId));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void send(BotApiMethod<?> sendMessage){
        try {
            sender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
