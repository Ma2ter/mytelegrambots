package telegrambot.bot.weatherbot;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by atols on 31.07.2017.
 */
public class AutoSerializableMap<T extends Serializable,V extends Serializable> extends HashMap {


    private String filepath = "savefile.txt";

    public String getFilepath() {
        return filepath;
    }

    private void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public static AutoSerializableMap load(String filepath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filepath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            AutoSerializableMap newMap = (AutoSerializableMap) objectInputStream.readObject();
            newMap.setFilepath(filepath);
            return newMap;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AutoSerializableMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public AutoSerializableMap(int initialCapacity) {
        super(initialCapacity);
    }

    public AutoSerializableMap() {
    }

    public AutoSerializableMap(Map m) {
        super(m);
    }

    @Override
    public Object put(Object key, Object value) {
        Object result = super.put(key, value);
        save();
        return result;
    }

    private void save(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filepath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
