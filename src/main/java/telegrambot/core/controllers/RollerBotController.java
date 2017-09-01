package telegrambot.core.controllers;

import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import telegrambot.core.api.M2RBotCommand;
import telegrambot.core.api.M2RController;
import telegrambot.core.api.Request;
import telegrambot.bot.rollerbot.RollerBot;
import telegrambot.core.api.OutputHandler;
import telegrambot.utils.roll.DiceRoller;
import telegrambot.utils.roll.RollResult;

/**
 * Created by atols on 25.08.2017.
 *
 * Controller for RollerBot, contains methods to manage data in model and send messages to User
 */
@Controller
@M2RController(includeBots = {RollerBot.class})
public class RollerBotController {

    @M2RBotCommand(pattern = "/start@")
    public void start(Request request, OutputHandler outputHandler){
        Message msg = request.getQueryContext().getMessage();
        SendMessage message = new SendMessage();
        outputHandler.send(message.setChatId(msg.getChatId()).setText("starting app"));

    }

    @M2RBotCommand(pattern = "/roll@", types = {Request.QueryType.COMMAND})
    public void rollWithoutInformation(Request request, OutputHandler outputHandler) {
        roll(request, outputHandler, false);
    }

    @M2RBotCommand(pattern = "/rolli@", types = {Request.QueryType.COMMAND})
    public void rollWithInformation(Request request, OutputHandler outputHandler){
        roll(request, outputHandler, true);
    }

    private void roll(Request request, OutputHandler outputHandler, boolean additionalInformation){
        outputHandler.send(DiceRoller.roll(request.getArgs(), additionalInformation).getSolution());
    }

}
