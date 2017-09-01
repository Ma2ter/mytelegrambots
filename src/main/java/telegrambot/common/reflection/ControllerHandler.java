package telegrambot.common.reflection;

import org.reflections.Reflections;
import org.telegram.telegrambots.api.objects.Update;
import telegrambot.bot.BaseBot;
import telegrambot.core.api.M2RBotCommand;
import telegrambot.core.api.M2RController;
import telegrambot.core.api.Request;
import telegrambot.core.api.OutputHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by atols on 25.08.2017.
 * Класс-обработчик пользовательских запросов.
 * Вызывает методы-обработчики через рефлексию, определяя перечень методов по аннотациям
 */
public class ControllerHandler {

    //Перечень классов-контроллеров, по умолчанию - из пакета telegrambot.core.controllers, свойство *.properties - controllers_package
    private static Set<Class<?>> controllers;
    //Ссылка на экземпляр
    private static ControllerHandler instance = null;

    /* Метод для вызова методов-обработчиков
    Input:
        Request request - Запрос (контекст), описывающий входные параметры, информацию по вызвавшему метод боту.
     */
    public void resolveQuery(Update update, BaseBot from) {
        Request request = new Request(update);
        OutputHandler outputHandler = new OutputHandler(from, update);

        Request.QueryType queryType = request.getType();

        List<Class> validControllers = getListOfValidControllers(from.getClass());
        List<Method> methods = getListOfValidMethodsByPatternAndQueryTypeFromControllers(validControllers, queryType.toString(), request.getStringValue(), from.getBotUsername());

        //Invoking all valid methods and sending result to Users
        methods.sort(Comparator.comparingInt(e2 -> e2.getAnnotation(M2RBotCommand.class).priority()));
        for (Method method : methods) {
            try {
                method.invoke(method.getDeclaringClass().newInstance(), request, outputHandler);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Class> getListOfValidControllers(Class targetClass) {
        //Getting list of all controllers, which <includeBots> satisfies to BaseBot.<from>
        return controllers.stream()
                .filter(cl -> {
                    return Arrays.asList(cl.getAnnotation(M2RController.class).includeBots()).stream()
                            .anyMatch((Class aClass) -> {
                                Class localTargetClass = targetClass;
                                while (localTargetClass != null) {
                                    if (aClass.getSimpleName().equals(localTargetClass.getSimpleName()))
                                        return true;
                                    localTargetClass = localTargetClass.getSuperclass();
                                }
                                return false;
                            });
                })
                .collect(Collectors.toList());

    }

    //Getting list of all methods, which <pattern> and <queryType> satisfies request.<command> and QueryType.<queryType>
    public List<Method> getListOfValidMethodsByPatternAndQueryTypeFromControllers(List<Class> validControllers, String queryType, String searchFor, String patternFormatter) {
        List<Method> methods = new ArrayList<>();
        validControllers.forEach(cl -> {
            List<Method> localMethods = Arrays.asList(cl.getDeclaredMethods());
            localMethods.stream()
                    .filter(method -> method.isAnnotationPresent(M2RBotCommand.class))
                    .filter(method -> Arrays.asList(method.getAnnotation(M2RBotCommand.class).types()).stream().anyMatch(e -> e.toString().equals(queryType)))
                    .filter(e -> checkIfInputMatchesMethodsPattern(e, searchFor, patternFormatter))
                    .forEach(methods::add);
        });
        return methods;
    }

    //Constructors, getters, setters, Override methods
    private ControllerHandler() {
        InputStream controolersFolderStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties");
        Properties properties = new Properties();
        try {
            properties.load(controolersFolderStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String controllersFolder = properties.getProperty("controllers_package");
        Reflections reflections = new Reflections(controllersFolder);
        controllers = reflections.getTypesAnnotatedWith(M2RController.class);
    }

    //Фабричный метод для получения ссылки на экземпляр singleton'а
    public static ControllerHandler getInstance() {
        if (instance == null)
            instance = new ControllerHandler();
        return instance;
    }

    private boolean checkIfInputMatchesMethodsPattern(Method method, String searchFor, String patternFormatter){
        String pattern = method.getAnnotation(M2RBotCommand.class).pattern();
        System.out.println(pattern);

        Pattern checkForBotNameInCommand = Pattern.compile("/[^-\\s]*@(\\s.*|$)");
        if (checkForBotNameInCommand.matcher(pattern).matches())
            pattern = pattern.replaceFirst("@", String.format("(@%s)?", patternFormatter));

        Pattern p = Pattern.compile(pattern + "(\\s.*)?");
        return p.matcher(searchFor).matches();
    }

}
