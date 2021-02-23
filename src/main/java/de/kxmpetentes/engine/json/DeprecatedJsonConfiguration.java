package de.kxmpetentes.engine.json;

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

@Deprecated
public class DeprecatedJsonConfiguration {


    public static Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping().create();
    protected static final JsonParser PARSER = new JsonParser();

    protected String name;
    private File file;

    protected JsonObject dataCatcher;

    public DeprecatedJsonConfiguration(String name) {
        this.name = name;
        this.dataCatcher = new JsonObject();
    }

    public DeprecatedJsonConfiguration(String name, JsonObject source) {
        this.name = name;
        this.dataCatcher = source;
    }

    public DeprecatedJsonConfiguration(File file, JsonObject jsonObject) {
        this.file = file;
        this.dataCatcher = jsonObject;
    }

    public DeprecatedJsonConfiguration(String key, String value) {
        this.dataCatcher = new JsonObject();
        this.append(key, value);
    }

    public DeprecatedJsonConfiguration(String key, Object value) {
        this.dataCatcher = new JsonObject();
        this.append(key, value);
    }

    public DeprecatedJsonConfiguration(String key, Number value) {
        this.dataCatcher = new JsonObject();
        this.append(key, value);
    }

    public DeprecatedJsonConfiguration(DeprecatedJsonConfiguration defaults) {
        this.dataCatcher = defaults.dataCatcher;
    }

    public DeprecatedJsonConfiguration(DeprecatedJsonConfiguration defaults, String name) {
        this.dataCatcher = defaults.dataCatcher;
        this.name = name;
    }

    public DeprecatedJsonConfiguration() {
        this.dataCatcher = new JsonObject();
    }

    public DeprecatedJsonConfiguration(JsonObject source) {
        this.dataCatcher = source;
    }

    public JsonObject getDataCatcher() {
        return dataCatcher;
    }

    public boolean contains(String key) {
        return this.dataCatcher.has(key);
    }

    public void append(String key, String value) {
        if (value == null) return;
        this.dataCatcher.addProperty(key, value);
    }

    public void append(String key, Number value) {
        if (value == null) return;
        this.dataCatcher.addProperty(key, value);
    }

    public DeprecatedJsonConfiguration append(String key, Boolean value) {
        if (value == null) return this;
        this.dataCatcher.addProperty(key, value);
        return this;
    }

    public DeprecatedJsonConfiguration append(String key, List<String> value) {
        if (value == null) return this;
        JsonArray jsonElements = new JsonArray();

        for (String b : value) {
            jsonElements.add(b);
        }

        this.dataCatcher.add(key, jsonElements);
        return this;
    }

    public void append(String key, DeprecatedJsonConfiguration value) {
        if (value == null) return;
        this.dataCatcher.add(key, value.dataCatcher);
    }

    @Deprecated
    public DeprecatedJsonConfiguration append(String key, Object value) {
        if (value == null) return this;
        if (value instanceof DeprecatedJsonConfiguration) {
            this.append(key, (DeprecatedJsonConfiguration) value);
            return this;
        }
        this.dataCatcher.add(key, GSON.toJsonTree(value));
        return this;
    }

    public DeprecatedJsonConfiguration appendValues(Map<String, Object> values) {
        for (Map.Entry<String, Object> valuess : values.entrySet()) {
            append(valuess.getKey(), valuess.getValue());
        }
        return this;
    }

    public void remove(String key) {
        this.dataCatcher.remove(key);
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

    public DeprecatedJsonConfiguration getDocument(String key) {
        if (!dataCatcher.has(key)) return null;
        return new DeprecatedJsonConfiguration(dataCatcher.get(key).getAsJsonObject());
    }

    public DeprecatedJsonConfiguration clear() {
        for (String key : keys()) {
            remove(key);
        }
        return this;
    }

    public int size() {
        return this.dataCatcher.size();
    }

    public DeprecatedJsonConfiguration loadProperties(Properties properties) {
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

    public void saveAsConfig(File backend) {
        if (backend == null) return;

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(backend), StandardCharsets.UTF_8)) {
            GSON.toJson(dataCatcher, (writer));
        } catch (IOException ex) {
            ex.getStackTrace();
        }
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

    public static DeprecatedJsonConfiguration loadDocument(File backend) {
        return loadDocument(backend.toPath());
    }

    public DeprecatedJsonConfiguration JsonConfiguration(File backend) throws Exception {
        try {
            return new DeprecatedJsonConfiguration(PARSER.parse(new String(Files.readAllBytes(backend.toPath()), StandardCharsets.UTF_8)).getAsJsonObject());
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public static DeprecatedJsonConfiguration loadDocument(Path backend) {

        try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(backend), StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            JsonObject object = PARSER.parse(bufferedReader).getAsJsonObject();
            return new DeprecatedJsonConfiguration(object);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return new DeprecatedJsonConfiguration();

    }

    public DeprecatedJsonConfiguration loadToExistingDocument(File backend) {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(backend), StandardCharsets.UTF_8)) {

            this.dataCatcher = PARSER.parse(reader).getAsJsonObject();
            this.file = backend;
            return this;
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return new DeprecatedJsonConfiguration();
    }

    public DeprecatedJsonConfiguration loadToExistingDocument(Path path) {
        try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(path), StandardCharsets.UTF_8)) {

            this.dataCatcher = PARSER.parse(reader).getAsJsonObject();
            return this;
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return new DeprecatedJsonConfiguration();
    }

    public static DeprecatedJsonConfiguration load(String input) {
        try (InputStreamReader reader = new InputStreamReader(new StringBufferInputStream(input), "UTF-8")) {
            return new DeprecatedJsonConfiguration(PARSER.parse(new BufferedReader(reader)).getAsJsonObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DeprecatedJsonConfiguration();
    }

    @Override
    public String toString() {
        return convertToJsonString();
    }

    public static DeprecatedJsonConfiguration load(JsonObject input) {
        return new DeprecatedJsonConfiguration(input);
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
