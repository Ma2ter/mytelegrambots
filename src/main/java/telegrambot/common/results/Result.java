package telegrambot.common.results;

import telegrambot.core.api.Request;

/**
 * Created by atols on 27.07.2017.
 * Абстрактная сущность для хранения результатов выполнения программы, конечных или промежуточных, произвольного типа данных
 *
 */
public class Result {

    //Сообщение пользователю
    private String message;
    //Номер чата для отправки сообщения
    private Long chatId;

    //Constructors, getters, setters, Override methods
    Result() {
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
    }

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

    /*
    Статичный фабричный метод для инициализации экземпляра результата из контекста запроса пользователя.
    Например: Номер чата для отправки сообщения, исходя из контекста, по умолчанию может браться из запроса для обратной отправки.

    Метод открыт для расширения, но закрыт для изменения.

    TODO: Определить контекст, исходя из гипотетических ситуаций.
     */
    public static Result initFromRequestContext(Request request){
        Result result = new Result();
        result.chatId = request.getQueryContext().getMessage().getChatId();
        // Add more necessary fields here
        //.....
        return result;
    }
}
