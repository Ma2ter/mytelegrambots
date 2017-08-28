package telegrambot.common.reflection;

import org.reflections.Reflections;
import telegrambot.bot.BaseBot;
import telegrambot.core.api.M2RBotCommand;
import telegrambot.core.api.M2RController;
import telegrambot.core.api.Request;
import telegrambot.common.results.Result;
import telegrambot.common.results.ResultHandler;

import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by atols on 25.08.2017.
 * Класс-обработчик пользовательских запросов.
 * Вызывает методы-обработчики через рефлексию, определяя перечень методов по аннотациям
 */
public class ControllerHandler {

    //Перечень классов-контроллеров, по умолчанию - из пакета telegrambot.core.controllers
    private static Set<Class<?>> controllers;
    //Ссылка на экземпляр
    private static ControllerHandler instance = null;

    /* Метод для вызова методов-обработчиков
    Input:
        Request request - Запрос (контекст), описывающий входные параметры, информацию по вызвавшему метод боту.
     */
    public void resolveQuery(Request request) {
        Request.QueryType queryType = request.getType();
        BaseBot from = request.getSender();
        List<Method> methods = new ArrayList<>();
        //Getting list of all controllers, which <includeBots> satisfies to BaseBot.<from>
        List<Class<?>> validControllers = controllers.stream()
                .filter(aClass -> aClass.isAnnotationPresent(M2RController.class))
                .filter(cl -> {
                    return Arrays.asList(cl.getAnnotation(M2RController.class).includeBots()).stream().anyMatch((Class aClass) -> {
                        return aClass.getName().equals(from.getClass().getName());
                    });
                })
                .collect(Collectors.toList());
        //Getting list of all methods, which <pattern> and <queryType> satisfies request.<command> and QueryType.<queryType>
        validControllers.forEach(cl -> {
            List<Method> localMethods = Arrays.asList(cl.getDeclaredMethods());
            localMethods.stream()
                    .filter(method -> method.isAnnotationPresent(M2RBotCommand.class))
                    .filter(method -> Arrays.asList(method.getAnnotation(M2RBotCommand.class).types()).stream().anyMatch(e -> e.compareTo(queryType) == 0))
                    .filter(method -> {
                        String pattern = method.getAnnotation(M2RBotCommand.class).pattern();

                        Pattern checkForBotNameInCommand = Pattern.compile("/[^-\\s]*@(\\s.*|$)");
                        if (checkForBotNameInCommand.matcher(pattern).matches())
                            pattern = pattern.replaceFirst("@", String.format("(@%s)?", from.getBotUsername()));

                        Pattern p = Pattern.compile(pattern + "(\\s.*)?");
                        //TODO: Encapsulate update methods from resolveQuery
                        return p.matcher(request.getStringValue()).matches();
                    })
                    .forEach(methods::add);
        });
        //Invoking all valid methods and sending result to Users
        methods.sort(Comparator.comparingInt(e2 -> e2.getAnnotation(M2RBotCommand.class).priority()));
        for (Method method : methods) {
            try {
                Result result = (Result) method.invoke(method.getDeclaringClass().newInstance(), request, Result.initFromRequestContext(request));
                ResultHandler.start(from).sendResult(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Constructors, getters, setters, Override methods
    private ControllerHandler() {
        Reflections reflections = new Reflections("telegrambot.core.controllers");
        controllers = reflections.getTypesAnnotatedWith(M2RController.class);
    }

    //Фабричный метод для получения ссылки на экземпляр singleton'а
    public static ControllerHandler getInstance() {
        if (instance == null)
            instance = new ControllerHandler();
        return instance;
    }

}
