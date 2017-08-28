package telegrambot.common.entities;

/**
 * Created by atols on 27.07.2017.
 * POJO for parsing from JSON via JACKSON
 */
public class Clouds{

    public Clouds() {
    }

    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
