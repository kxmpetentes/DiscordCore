package de.kxmpetentes.engine.json;

import de.kxmpetentes.engine.utils.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 23.02.2021 um 09:00
 */
public class Configuration {

    /**
     * Config objects
     */
    private Map<String, Object> config;

    /**
     * create a new configuration
     */
    public Configuration() {
        config = new HashMap<>();
    }

    /**
     * create a new configuration with 1 key and object
     *
     * @param key    key of the object
     * @param object object to safe
     */
    public Configuration(String key, Object object) {
        this();
        config.put(key, object);
    }

    /**
     * Creates a new Configuration with some pairs
     *
     * @param pairs pairs of config objects
     */
    @SafeVarargs
    public Configuration(Pair<String, Object>... pairs) {
        this();

        for (Pair<String, Object> pair : pairs) {
            config.put(pair.getFirst(), pair.getSecond());
        }
    }

    /**
     * Gets the configuration object
     *
     * @return HashMap of config objects
     */
    public Map<String, Object> getConfig() {
        return config;
    }

    /**
     * Sets the config hash map
     *
     * @param config hash map of config objects
     */
    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }

    /**
     * Adds a new pair to the config
     *
     * @param pair pair to add to the config
     */
    public void put(Pair<String, Object> pair) {
        this.config.put(pair.getFirst(), pair.getSecond());
    }

    /**
     * Adds new key and value to the config
     *
     * @param key   key to add
     * @param value value to add
     */
    public void put(String key, Object value) {
        this.config.put(key, value);
    }

    /**
     * Checks if the config contains the <b>key</b>
     *
     * @param key key to check
     * @return contains the config the key?
     */
    public boolean containsKey(String key) {
        return config.containsKey(key);
    }

    /**
     * Checks if the config contains the <b>value</b>
     *
     * @param value key to check
     * @return contains the config the value?
     */
    public boolean containsValue(Object value) {
        return config.containsValue(value);
    }

    /**
     * Checks if the config contains the <b>value</b>
     *
     * @param pair pair to check
     * @return contains the config the pair?
     */
    public boolean containsPair(Pair<String, Object> pair) {
        if (!containsKey(pair.getFirst())) {
            return false;
        }

        if (!containsValue(pair.getSecond())) {
            return false;
        }

        return config.get(pair.getFirst()).equals(pair.getSecond());
    }

    /**
     * Size of the config
     *
     * @return the size of the config
     */
    public int size() {
        return config.size();
    }

    /**
     * Gets an object from the configuration
     *
     * @param key key to get the value
     * @return value of the key
     */
    public Object get(String key) {
        return config.get(key);
    }

    /**
     * Gets an object from the configuration safely
     *
     * @param key          key to get the value
     * @param defaultValue if the value of the key is null or the key does not exist return the default
     * @return default value or value of the key
     */
    public Object getOrDefault(String key, Object defaultValue) {
        return config.getOrDefault(key, defaultValue);
    }

}
