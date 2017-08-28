package telegrambot.bot.rollerbot;

import org.telegram.telegrambots.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import telegrambot.bot.BaseBot;
import telegrambot.bot.api.Request;
import telegrambot.common.results.RollResult;
import telegrambot.utils.roll.DiceRoller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by atols on 25.07.2017.
 */
public class RollerBot extends BaseBot {


    private static final String cmd_start = "/start";
    private static final String cmd_stop = "/stop";
    private static final String cmd_roll = "/roll";
    private static final String cmd_rollInfo = "/rolli";
    private List<String> commands;

    public RollerBot(String API){
        super(API);
        commands = new ArrayList<>();
        commands.addAll(Arrays.asList(cmd_roll, cmd_start, cmd_stop));
    }

    /*@Override
    public void onUpdateReceived(Update update) {

        //

        Request query = new Request(update);
        Request.QueryType type = query.getType();
        SendMessage sendMessage = null;
    switch (type){
        case COMMAND:
                Message msg = update.getMessage();
                String message = Commands.getCommand(msg.getText()).run(msg.getText()).toString();
                sendMessage = new SendMessage();
                sendMessage.setText(message).setChatId(msg.getChatId());
                break;
            case INLINE:
                InlineQuery inlineQuery = update.getInlineQuery();
                AnswerInlineQuery answer = new AnswerInlineQuery();
                RollResult answerMessage;
                try {
                    answerMessage = DiceRoller.roll(inlineQuery.getQuery(), false);
                } catch (Exception e) {
                    answerMessage = new RollResult(e.getMessage());
                }
                InputTextMessageContent content = new InputTextMessageContent();
                content.setMessageText(answerMessage.getSolution());
                answer.setInlineQueryId(inlineQuery.getId()).setResults(new InlineQueryResultArticle().setInputMessageContent(content).setId(Integer.toString(1)).setTitle(answerMessage.getQuery() + " : " + answerMessage.getResult()));
                try {
                    answerInlineQuery(answer);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                System.out.println(inlineQuery.getQuery());
        }
        if(sendMessage != null){
            try {
                sendMessage(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }*/

    public String getBotUsername() {
        return "M2RollerBot";
    }

    public void onClosing() {

    }

    private String processQuery(String query){

        return "";
    }


}
