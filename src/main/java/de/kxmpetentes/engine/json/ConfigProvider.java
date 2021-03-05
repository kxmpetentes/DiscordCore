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

    public void safeConfig(@NotNull File file) throws ConfigurationException, IOException {
        if (this.configuration == null) {
            throw new ConfigurationException("Configuration can not be null!");
        }

        objectMapper.writeValue(file, this.configuration);
    }

    public void safeConfig(@NotNull File file, @NotNull Configuration configuration) throws IOException, ConfigurationException {
        setConfiguration(configuration);
        safeConfig(file);
    }

    public Configuration getConfigFromFile(File file) throws IOException {
        return objectMapper.readValue(file, Configuration.class);
    }
}
