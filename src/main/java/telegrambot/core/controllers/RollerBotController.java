package telegrambot.core.controllers;

import telegrambot.core.api.M2RBotCommand;
import telegrambot.core.api.M2RController;
import telegrambot.core.api.Request;
import telegrambot.bot.rollerbot.RollerBot;
import telegrambot.common.results.Result;
import telegrambot.utils.roll.DiceRoller;

/**
 * Created by atols on 25.08.2017.
 *
 * Controller for RollerBot, contains methods to manage data in model and send messages to User
 */
@M2RController(includeBots = {RollerBot.class})
public class RollerBotController {

    @M2RBotCommand(pattern = "/start@")
    public Result start(Request request, Result result){

        return result.setMessage("Starting app...");
    }

    @M2RBotCommand(pattern = "/roll@", types = {Request.QueryType.COMMAND})
    public Result rollWithoutInformation(Request request, Result result) {

        return roll(request, result, false);
    }

    @M2RBotCommand(pattern = "/rolli@", types = {Request.QueryType.COMMAND})
    public Result rollWithInformation(Request request, Result result){

        return roll(request, result, true);
    }

    private Result roll(Request request, Result result, boolean additionalInformation){
        return DiceRoller.roll(request.getArgs(), additionalInformation).setChatId(result.getChatId());
    }

}
