package de.kxmpetentes.engine.utils.minecraft;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UUIDFetcher {


    private static final Gson gson = new GsonBuilder().registerTypeAdapter(UUID.class, new UUIDTypeAdapter()).create();
    private static final String UUID_URL = "https://api.mojang.com/users/profiles/minecraft/%s?at=%d";
    private static final String NAME_URL = "https://api.mojang.com/user/profiles/%s/names";
    private static final Map<String, UUID> uuidCache = new HashMap<String, UUID>();
    private static final Map<UUID, String> nameCache = new HashMap<UUID, String>();
    public String name;
    public UUID id;

    public static UUID getUUID(String name) {
        return UUIDFetcher.getUUIDAt(name, System.currentTimeMillis());
    }

    public static UUID getUUIDAt(String name, long timestamp) {
        if (uuidCache.containsKey(name = name.toLowerCase())) {
            return uuidCache.get(name);
        }
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(String.format(UUID_URL, name, timestamp / 1000L)).openConnection();
            connection.setReadTimeout(5000);
            UUIDFetcher data = gson.fromJson(new BufferedReader(new InputStreamReader(connection.getInputStream())), UUIDFetcher.class);
            if (data == null) {
                return null;
            }
            uuidCache.put(name, data.id);
            nameCache.put(data.id, data.name);
            return data.id;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static NameHistory getHistory(UUID uuid) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(String.format(NAME_URL, UUIDTypeAdapter.fromUUID(uuid))).openConnection();
            connection.setReadTimeout(5000);
            UUIDFetcher[] nameHistory = gson.fromJson(new BufferedReader(new InputStreamReader(connection.getInputStream())), UUIDFetcher[].class);
            return new NameHistory(uuid, nameHistory);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
