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
 */
public class CityManager {

    private static CityManager cityManager;
    private List<City> cities;

    public static CityManager getInstance(){
        if(cityManager == null){
            cityManager = new CityManager();
        }
        return cityManager;
    }

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

    public List<City> findCitiesByName(String name){

        return cities.stream().filter(city -> city.getName().contains(name)).collect(Collectors.toList());
    }

    public static void main(String[] arsg){

        CityManager cityManager = new CityManager();
        List<City> moscow = cityManager.findCitiesByName("Moscow");
        for(City c: moscow){
            System.out.println(c);
        }
    }
}
