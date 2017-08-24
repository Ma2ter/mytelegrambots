package telegrambot.utils;

/**
 * Created by atols on 27.07.2017.
 */
public class Result {

    private String message;

    public Result() {
        message = "No info";
    }

    public Result(String message){
        this.message = message;
    }

    @Override
    public String toString(){
        return message;
    };

    public void setMessage(String message) {
        this.message = message;
    }
}
