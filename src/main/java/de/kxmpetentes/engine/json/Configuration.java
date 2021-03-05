package de.kxmpetentes.engine.json;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 23.02.2021 um 09:00
 */

public class Configuration {

    private Map<String, Object> config;

    public Configuration() {
        config = new HashMap<>();
    }

    public Configuration(String key, Object object) {
        config = new HashMap<>();
        config.put(key, object);
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }

    public void put(String key, Object value) {
        this.config.put(key, value);
    }

    public boolean containsKey(String key) {
        return config.containsKey(key);
    }

    public int size() {
        return config.size();
    }

    public boolean containsValue(Object value) {
        return config.containsValue(value);
    }

    public Object get(String key) {
        return config.get(key);
    }

    public Object getOrDefault(String key, Object defaultValue) {
        return config.getOrDefault(key, defaultValue);
    }

}
