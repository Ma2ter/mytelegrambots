package telegrambot.core.controllers;

import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.User;
import telegrambot.bot.BaseBot;
import telegrambot.bot.chatutilsbot.ChatUtilsBot;
import telegrambot.core.api.OutputHandler;
import telegrambot.core.api.M2RBotCommand;
import telegrambot.core.api.M2RController;
import telegrambot.core.api.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by atols on 29.08.2017.
 */
@M2RController(includeBots = {BaseBot.class})
public class ChatUtilControllers {

    @M2RBotCommand(pattern = ".*\\?.*", types = Request.QueryType.MESSAGE)
    public void notifyThatUserAskedQuestionAndCreateTimer(Request request, OutputHandler outputHandler)  {
        User user = request.getQueryContext().getMessage().getFrom();

        List<String> questions = findQuestionsInPhrase(request.getStringValue());
        for (String s : questions){
            String sendMessage = String.format("User %s %s asked question \"%s\"", user.getFirstName(), user.getLastName(), s);
            outputHandler.send(sendMessage);
        }
    }

    @M2RBotCommand(pattern = ".*(H|h)ello.*", priority = 0, types = Request.QueryType.MESSAGE)
    public void notifyUserSaidHello(Request request, OutputHandler outputHandler){
        User user = request.getQueryContext().getMessage().getFrom();
        outputHandler.send(String.format("%s %s just said hello", user.getFirstName(), user.getLastName()));
    }

    private List<String> findQuestionsInPhrase(String s){
        Pattern pattern = Pattern.compile("([^.?!]*)\\?");
        Matcher matcher = pattern.matcher(s);
        List<String> questions = new ArrayList<>();
        while(matcher.find()){
            System.out.println(matcher.group());
            questions.add(matcher.group());
        }
        return questions;
    }
}
