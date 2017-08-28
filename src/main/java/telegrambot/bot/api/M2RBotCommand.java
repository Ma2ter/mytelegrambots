package telegrambot.bot.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by atols on 25.08.2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface M2RBotCommand {

    String pattern() default "*";
    Request.QueryType[] types() default {Request.QueryType.COMMAND};
    int priority() default 1;

}
