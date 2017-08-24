package telegrambot.bot.api;

/**
 * Created by atols on 31.07.2017.
 */
public abstract class Handler {

    private Handler nextHandler;

    public Handler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Handler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void process();
}
