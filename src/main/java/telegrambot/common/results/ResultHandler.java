package telegrambot.common.results;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import telegrambot.bot.BaseBot;

public class ResultHandler {

    /**
     * Created by atols on 25.08.2017.
     */

    private final BaseBot sender;

    private ResultHandler(BaseBot sender){
        this.sender = sender;
    }

    public static ResultHandler start(BaseBot sender){
        return new ResultHandler(sender);
    }

    public void sendResult(Result result) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(result.getChatId()).setText(result.toString());
        sender.sendMessage(sendMessage);
    }
}

