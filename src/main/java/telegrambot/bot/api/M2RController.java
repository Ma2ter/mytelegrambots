package telegrambot.bot.api;

import org.telegram.telegrambots.generics.LongPollingBot;

import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by atols on 25.08.2017.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface M2RController {

    Class[] includeBots() default LongPollingBot.class;


}
