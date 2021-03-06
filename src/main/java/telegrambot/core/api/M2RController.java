package telegrambot.core.api;

import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.telegram.telegrambots.generics.LongPollingBot;
import telegrambot.bot.BaseBot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by atols on 25.08.2017.
 * Аннотация для классов-контроллеров, содержащих методы-обработчики
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface M2RController {

    Class[] includeBots() default BaseBot.class;


}
