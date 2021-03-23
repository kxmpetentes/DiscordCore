package de.kxmpetentes.engine.json;

import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 07.01.2021 um 22:44
 */

public class JsonWebsite {

    private final String url;

    /**
     * Gets an JsonWebsite object form specified url
     *
     * @param url url to load
     */
    public JsonWebsite(String url) {
        this.url = url;
    }

    /**
     * Gets the JsonObject
     *
     * @return the JsonObject
     * @throws IOException if the file cannot be opened
     */
    public JSONObject getJsonObject() throws IOException {

        try {
            InputStream inputStream = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String jsonText = read(reader);

            inputStream.close();

            return new JSONObject(jsonText);
        } catch (FileNotFoundException e) {
            return null;
        }

    }

    /**
     * Reads an Array
     *
     * @return Collection of Strings
     * @throws IOException if the url cannot be opened
     */
    public Collection<String> getArray() throws IOException {
        ArrayList<String> values = new ArrayList<>();

        Scanner s = new Scanner(new URL(url).openStream());
        while (s.hasNextLine()) {
            String line = s.nextLine().replaceAll(",", "").replaceAll("\"", "").
                    replaceAll(" {3}", "");
            if (!line.contains("[") && !line.contains("]")) {
                values.add(line);
            }
        }
        s.close();

        if (values.isEmpty()) return new ArrayList<>();

        return values;
    }

    private String read(Reader reader) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        int counter;

        while ((counter = reader.read()) != -1) {
            stringBuilder.append((char) counter);
        }

        return stringBuilder.toString();
    }

}
