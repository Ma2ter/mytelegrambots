package telegrambot.bot;

import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by atols on 25.07.2017.
 */
public class UpdateQuery {

    private Update update;

    public UpdateQuery(Update query){
        this.update = query;
    }



    public QueryType getType(){

        if(update.hasInlineQuery()) return QueryType.INLINE;
        else if(update.getMessage().isCommand()) return QueryType.COMMAND;
        return QueryType.MESSAGE;
    }

    public enum QueryType{
        MESSAGE,
        COMMAND,
        INLINE

    }
}
