package telegrambot.bot.chatutilsbot;

import telegrambot.bot.BaseBot;

/**
 * Created by atols on 29.08.2017.
 */
public class ChatUtilsBot extends BaseBot {

    public ChatUtilsBot(String API) {
        super(API);
    }

    @Override
    public String getBotUsername() {
        return "M2ChatUtilsBot";
    }

    @Override
    public void onClosing() {

    }
}
