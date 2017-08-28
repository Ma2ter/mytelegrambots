package telegrambot.common.results;

import telegrambot.bot.api.Request;

/**
 * Created by atols on 27.07.2017.
 */
public class Result {

    private String message;
    private Long chatId;

    public Result() {
        message = "No info";
    }

    public Result(String message){
        this.message = message;
    }

    public Result(String message, Long chatId){
        this.message = message;
        this.chatId = chatId;
    }

    @Override
    public String toString(){
        return message;
    };

    public String getMessage() {
        return message;
    }

    public Long getChatId() {
        return chatId;
    }

    public Result setChatId(Long chatId) {
        this.chatId = chatId;
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public static Result initFromRequestContext(Request request){
        Result result = new Result();
        result.chatId = request.getQueryContext().getMessage().getChatId();
        // Add more necessary fields here
        //.....
        return result;
    }
}
