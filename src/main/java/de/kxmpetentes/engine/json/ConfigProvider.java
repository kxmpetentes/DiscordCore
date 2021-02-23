package de.kxmpetentes.engine.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@AllArgsConstructor
public class ConfigProvider {

    private Config config;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void safeConfig(@NotNull File file) throws ConfigurationException, IOException {
        if (this.config == null) {
            throw new ConfigurationException("Config can not be null!");
        }

        objectMapper.writeValue(file, this.config);
    }

    public void safeConfig(@NotNull File file, @NotNull Config config) throws IOException, ConfigurationException {
        setConfig(config);
        safeConfig(file);
    }

    public Config getConfigFromFile(File file) throws IOException {
        return objectMapper.readValue(file, Config.class);
    }
}
