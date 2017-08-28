package telegrambot.bot.rollerbot;

import telegrambot.bot.BaseBot;

/**
 * Created by atols on 25.07.2017.
 * Bot для симуляции бросков кубиков и подсчета конечного результата
 * Доступные команды по спецификации:
 * /roll <value>
 * /rolli <value>
 */
public class RollerBot extends BaseBot {


    public RollerBot(String API){
        super(API);
    }

    public String getBotUsername() {
        return "M2RollerBot";
    }

    public void onClosing() {

    }

}
