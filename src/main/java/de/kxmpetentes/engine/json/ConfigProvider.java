package de.kxmpetentes.engine.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 23.02.2021 um 09:13
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigProvider {

    private Configuration configuration;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Loads the configuration from file
     *
     * @param file file to read
     * @throws ConfigurationException if the configuration is null
     * @throws IOException            if the file can't saved
     */
    public void safeConfig(@NotNull File file) throws ConfigurationException, IOException {
        if (this.configuration == null) {
            throw new ConfigurationException("Configuration can not be null!");
        }

        objectMapper.writeValue(file, this.configuration);
    }

    /**
     * Save the configuration to file
     *
     * @param file          file to save the configuration to
     * @param configuration configuration to save
     * @throws IOException            if the file can't save
     * @throws ConfigurationException if the configuration is null
     */
    public void safeConfig(@NotNull File file, @NotNull Configuration configuration) throws IOException, ConfigurationException {
        setConfiguration(configuration);
        safeConfig(file);
    }

    /**
     * Loads the configuration from the file
     *
     * @param file file to load the configuration from
     * @return the configuration that was loaded from the file
     * @throws IOException            if the file cannot be read
     * @throws ConfigurationException if the file is null
     */
    public Configuration getConfigFromFile(File file) throws IOException, ConfigurationException {
        if (file == null) {
            throw new ConfigurationException("File cannot be null");
        }

        return objectMapper.readValue(file, Configuration.class);
    }
}
