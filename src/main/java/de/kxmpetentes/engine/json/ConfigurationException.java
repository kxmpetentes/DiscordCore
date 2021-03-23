package de.kxmpetentes.engine.json;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 23.02.2021 um 09:16
 */

public class ConfigurationException extends Exception {

    public ConfigurationException() {
        super("Failed to convert config!");
    }

    /**
     * Throws an exception
     *
     * @param message message of the error
     */
    public ConfigurationException(String message) {
        super(message);
    }
}
