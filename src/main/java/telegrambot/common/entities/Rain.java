package telegrambot.common.entities;

/**
 * Created by atols on 28.07.2017.
 * POJO for parsing from JSON via JACKSON
 */
public class Rain {

    public Float get3H() {
        return h;
    }

    public void set3H(Float h) {
        this.h = h;
    }

    private Float h;


}
