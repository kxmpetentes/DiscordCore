package de.kxmpetentes.engine.utils.minecraft;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.kxmpetentes.engine.DiscordCore;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 04.03.2021 um 20:14
 */

@Getter
public class UUIDFetcher {

    private static final Gson gson = new GsonBuilder().registerTypeAdapter(UUID.class, new UUIDTypeAdapter()).create();
    private static final String uuidUrl = "https://api.mojang.com/users/profiles/minecraft/%s?at=%d";
    private static final String nameUrl = "https://api.mojang.com/user/profiles/%s/names";
    private static final Map<String, UUID> uuidCache = new HashMap<>();
    private static final Map<UUID, String> nameCache = new HashMap<>();
    private String name;
    private UUID id;

    public static UUID getUUID(String name) {
        return getUUIDAt(name, System.currentTimeMillis());
    }

    public static UUID getUUIDAt(String name, long timestamp) {

        if (uuidCache.containsKey(name.toLowerCase())) {
            return uuidCache.get(name);
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(String.format(uuidUrl, name, timestamp / 1000L)).openConnection();
            connection.setReadTimeout(5000);

            UUIDFetcher uuidData = gson.fromJson(new BufferedReader(new InputStreamReader(connection.getInputStream())), UUIDFetcher.class);
            if (uuidData == null) {
                throw new NullPointerException();
            }

            uuidCache.put(name, uuidData.getId());
            nameCache.put(uuidData.getId(), uuidData.getName());

            return uuidData.getId();
        } catch (Exception e) {
            DiscordCore.getInstance().getLogger().error(e.getMessage(), e.getCause());
            return null;
        }
    }

    public static NameHistory getHistory(UUID uuid) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(String.format(nameUrl, UUIDTypeAdapter.fromUUID(uuid))).openConnection();
            connection.setReadTimeout(5000);
            UUIDFetcher[] nameHistory = gson.fromJson(new BufferedReader(new InputStreamReader(connection.getInputStream())), UUIDFetcher[].class);
            return new NameHistory(uuid, nameHistory);
        } catch (Exception e) {
            DiscordCore.getInstance().getLogger().error(e.getMessage(), e.getCause());
            return null;
        }
    }

}
