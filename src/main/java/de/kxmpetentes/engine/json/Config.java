package de.kxmpetentes.engine.json;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 23.02.2021 um 09:00
 */

public class Config {

    private Map<String, Object> config;

    public Config() {
        config = new HashMap<>();
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

}
