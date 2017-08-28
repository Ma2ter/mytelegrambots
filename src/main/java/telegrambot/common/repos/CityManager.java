package telegrambot.common.repos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import telegrambot.common.entities.City;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by atols on 27.07.2017.
 * Класс-репозиторий, осуществляющий загрузку списка городов и реализующий методы обращения с ними.
 */
public class CityManager {

    //Instance of singleton
    private static CityManager cityManager;
    //List of cities
    private List<City> cities;

    //Фабричный метод получения ссылки на экземпляр singleton'а
    public static CityManager getInstance(){
        if(cityManager == null){
            cityManager = new CityManager();
        }
        return cityManager;
    }

    //Constructors, getters, setters, Override methods
    private CityManager(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("file/city.list.json").getFile());
        ObjectMapper mapper = new ObjectMapper();
        try {
            cities = mapper.readValue(file, new TypeReference<List<City>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*Поиск города по названию
    Input:
        String name - наименование города (пример: Moscow)
    Output:
        List<City> - Список сущностей City - результатов поиска
     */

    public List<City> findCitiesByName(String name){

        return cities.stream().filter(city -> city.getName().contains(name)).collect(Collectors.toList());
    }

}
