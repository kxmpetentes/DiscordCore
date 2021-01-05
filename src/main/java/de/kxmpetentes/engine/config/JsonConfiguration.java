package de.kxmpetentes.engine.config;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 05.01.2021 um 13:40
 */

public class JsonConfiguration {


    public static Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping().create();
    protected static final JsonParser PARSER = new JsonParser();

    protected String name;
    private File file;

    protected JsonObject dataCatcher;

    public JsonConfiguration(String name) {
        this.name = name;
        this.dataCatcher = new JsonObject();
    }

    public JsonConfiguration(String name, JsonObject source) {
        this.name = name;
        this.dataCatcher = source;
    }

    public JsonConfiguration(File file, JsonObject jsonObject) {
        this.file = file;
        this.dataCatcher = jsonObject;
    }

    public JsonConfiguration(String key, String value) {
        this.dataCatcher = new JsonObject();
        this.append(key, value);
    }

    public JsonConfiguration(String key, Object value) {
        this.dataCatcher = new JsonObject();
        this.append(key, value);
    }

    public JsonConfiguration(String key, Number value) {
        this.dataCatcher = new JsonObject();
        this.append(key, value);
    }

    public JsonConfiguration(JsonConfiguration defaults) {
        this.dataCatcher = defaults.dataCatcher;
    }

    public JsonConfiguration(JsonConfiguration defaults, String name) {
        this.dataCatcher = defaults.dataCatcher;
        this.name = name;
    }

    public JsonConfiguration() {
        this.dataCatcher = new JsonObject();
    }

    public JsonConfiguration(JsonObject source) {
        this.dataCatcher = source;
    }

    public JsonObject getDataCatcher() {
        return dataCatcher;
    }

    public boolean contains(String key) {
        return this.dataCatcher.has(key);
    }

    public JsonConfiguration append(String key, String value) {
        if (value == null) return this;
        this.dataCatcher.addProperty(key, value);
        return this;
    }

    public JsonConfiguration append(String key, Number value) {
        if (value == null) return this;
        this.dataCatcher.addProperty(key, value);
        return this;
    }

    public JsonConfiguration append(String key, Boolean value) {
        if (value == null) return this;
        this.dataCatcher.addProperty(key, value);
        return this;
    }

    public JsonConfiguration append(String key, List<String> value) {
        if (value == null) return this;
        JsonArray jsonElements = new JsonArray();

        for (String b : value) {
            jsonElements.add(b);
        }

        this.dataCatcher.add(key, jsonElements);
        return this;
    }

    public JsonConfiguration append(String key, JsonConfiguration value) {
        if (value == null) return this;
        this.dataCatcher.add(key, value.dataCatcher);
        return this;
    }

    @Deprecated
    public JsonConfiguration append(String key, Object value) {
        if (value == null) return this;
        if (value instanceof JsonConfiguration) {
            this.append(key, (JsonConfiguration) value);
            return this;
        }
        this.dataCatcher.add(key, GSON.toJsonTree(value));
        return this;
    }

    public JsonConfiguration appendValues(Map<String, Object> values) {
        for (Map.Entry<String, Object> valuess : values.entrySet()) {
            append(valuess.getKey(), valuess.getValue());
        }
        return this;
    }

    public JsonConfiguration remove(String key) {
        this.dataCatcher.remove(key);
        return this;
    }

    public Set<String> keys() {
        Set<String> c = new HashSet<>();

        for (Map.Entry<String, JsonElement> x : dataCatcher.entrySet()) {
            c.add(x.getKey());
        }

        return c;
    }

    public JsonElement get(String key) {
        if (!dataCatcher.has(key)) return null;
        return dataCatcher.get(key);
    }

    public String getString(String key) {
        if (!dataCatcher.has(key)) return null;
        return dataCatcher.get(key).getAsString();
    }

    public int getInt(String key) {
        if (!dataCatcher.has(key)) return 0;
        return dataCatcher.get(key).getAsInt();
    }

    public long getLong(String key) {
        if (!dataCatcher.has(key)) return 0L;
        return dataCatcher.get(key).getAsLong();
    }

    public double getDouble(String key) {
        if (!dataCatcher.has(key)) return 0D;
        return dataCatcher.get(key).getAsDouble();
    }

    public boolean getBoolean(String key) {
        if (!dataCatcher.has(key)) return false;
        return dataCatcher.get(key).getAsBoolean();
    }

    public float getFloat(String key) {
        if (!dataCatcher.has(key)) return 0F;
        return dataCatcher.get(key).getAsFloat();
    }

    public short getShort(String key) {
        if (!dataCatcher.has(key)) return 0;
        return dataCatcher.get(key).getAsShort();
    }

    public <T> T getObject(String key, Class<T> class_) {
        if (!dataCatcher.has(key)) return null;
        JsonElement element = dataCatcher.get(key);

        return GSON.fromJson(element, class_);
    }

    public JsonConfiguration getDocument(String key) {
        if (!dataCatcher.has(key)) return null;
        return new JsonConfiguration(dataCatcher.get(key).getAsJsonObject());
    }

    public JsonConfiguration clear() {
        for (String key : keys()) {
            remove(key);
        }
        return this;
    }

    public int size() {
        return this.dataCatcher.size();
    }

    public JsonConfiguration loadProperties(Properties properties) {
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            Object x = enumeration.nextElement();
            this.append(x.toString(), properties.getProperty(x.toString()));
        }
        return this;
    }

    public boolean isEmpty() {
        return this.dataCatcher.size() == 0;
    }

    public JsonArray getArray(String key) {
        return dataCatcher.get(key).getAsJsonArray();
    }

    public String convertToJson() {
        return GSON.toJson(dataCatcher);
    }

    public String convertToJsonString() {
        return dataCatcher.toString();
    }

    public boolean saveAsConfig(File backend) {
        if (backend == null) return false;

        if (backend.exists()) {
            backend.delete();
        }

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(backend), StandardCharsets.UTF_8)) {
            GSON.toJson(dataCatcher, (writer));
            return true;
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return false;
    }

    public boolean saveAsConfig(Path path) {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(path), StandardCharsets.UTF_8)) {
            GSON.toJson(dataCatcher, outputStreamWriter);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveAsConfig(String path) {
        return saveAsConfig(Paths.get(path));
    }

    public static JsonConfiguration loadDocument(File backend) {
        return loadDocument(backend.toPath());
    }

    public static JsonConfiguration JsonConfiguration(File backend) throws Exception {
        try {
            return new JsonConfiguration(PARSER.parse(new String(Files.readAllBytes(backend.toPath()), StandardCharsets.UTF_8)).getAsJsonObject());
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public static JsonConfiguration loadDocument(Path backend) {

        try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(backend), StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            JsonObject object = PARSER.parse(bufferedReader).getAsJsonObject();
            return new JsonConfiguration(object);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return new JsonConfiguration();

    }

    public JsonConfiguration loadToExistingDocument(File backend) {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(backend), StandardCharsets.UTF_8)) {

            this.dataCatcher = PARSER.parse(reader).getAsJsonObject();
            this.file = backend;
            return this;
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return new JsonConfiguration();
    }

    public JsonConfiguration loadToExistingDocument(Path path) {
        try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(path), StandardCharsets.UTF_8)) {

            this.dataCatcher = PARSER.parse(reader).getAsJsonObject();
            return this;
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return new JsonConfiguration();
    }

    public static JsonConfiguration load(String input) {
        try (InputStreamReader reader = new InputStreamReader(new StringBufferInputStream(input), "UTF-8")) {
            return new JsonConfiguration(PARSER.parse(new BufferedReader(reader)).getAsJsonObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonConfiguration();
    }

    @Override
    public String toString() {
        return convertToJsonString();
    }

    public static JsonConfiguration load(JsonObject input) {
        return new JsonConfiguration(input);
    }

    public <T> T getObject(String key, Type type) {
        if (!contains(key)) return null;

        return GSON.fromJson(dataCatcher.get(key), type);
    }

    public byte[] toBytesAsUTF_8() {
        return convertToJsonString().getBytes(StandardCharsets.UTF_8);
    }

    public byte[] toBytes() {
        return convertToJsonString().getBytes();
    }

    public String getName() {
        return this.name;
    }

    public File getFile() {
        return this.file;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFile(File file) {
        this.file = file;
    }


}
