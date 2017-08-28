package telegrambot.bot.api;

import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.telegram.telegrambots.api.objects.Update;
import telegrambot.bot.BaseBot;

import java.util.Hashtable;
import java.util.Map;


/**
 * Created by atols on 25.07.2017.
 */
public class Request {

    private QueryType queryType;
    private BaseBot sender;
    private Update queryContext;

    private final String STRING_VALUE = "StringValue";
    private final String COMMAND = "cmd";
    private final String ARGUMENTS = "args";

    private Map<String, Object> model = new Hashtable<>();

    public String getCommand() {
        return model.get(COMMAND).toString();
    }

    public String getArgs() {
        return model.get(ARGUMENTS).toString();
    }


    public String getStringValue() {
        return model.get(STRING_VALUE).toString();
    }



    public Request(Update query, BaseBot sender){
        this.sender = sender;
        this.queryContext = query;
        //TODO:Encapsulate hardcoded transformation to factory
        if(query.hasInlineQuery()) queryType = QueryType.INLINE;
        else if(query.getMessage().isCommand()) queryType = QueryType.COMMAND;
        else queryType = QueryType.MESSAGE;

        String text = query.getMessage().getText();

        model.put(STRING_VALUE,text);
        model.put(COMMAND, text.split(" ")[0]);
        model.put(ARGUMENTS, text.indexOf(' ') > 0 ? text.split(" ", 2)[1] : "");

    }

    public BaseBot getSender(){
        return sender;
    }

    public QueryType getType(){
        return queryType;
    }

    public enum QueryType{
        MESSAGE,
        COMMAND,
        INLINE

    }

    public void addAttribute(String key, Object value) {
        model.put(key, value);
    }

    public Object getAttribute(String key){
        return model.get(key);
    }

    public Update getQueryContext(){
        return queryContext;
    }

}
