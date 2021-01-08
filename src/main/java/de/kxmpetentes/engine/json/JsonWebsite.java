package de.kxmpetentes.engine.json;

import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 07.01.2021 um 22:44
 */

public class JsonWebsite {

    private final String url;

    public JsonWebsite(String url) {
        this.url = url;
    }

    public JSONObject getJsonObject() throws IOException {

        try {
            final InputStream inputStream = new URL(url).openStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            final String jsonText = read(reader);

            return new JSONObject(jsonText);

        } catch (FileNotFoundException e) {
            return null;
        }

    }

    private String read(Reader reader) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        int counter;

        while ((counter = reader.read()) != -1) {
            stringBuilder.append((char) counter);
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        try {
            return getJsonObject().toString();
        } catch (IOException e) {
            return "";
        }
    }
}
