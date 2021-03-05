package de.kxmpetentes.engine.manager;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Collections;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 05.01.2021 um 16:55
 */

public class MongoAPI {

    private MongoAPI() {
    }

    private static MongoDatabase mongoDatabase;
    private static MongoClient client;

    public static void connect(String host, int port, String username, String authDatabase, String DB, String password) {
        MongoCredential credential = MongoCredential.createCredential(username, authDatabase, password.toCharArray());
        MongoClientSettings settings = MongoClientSettings.builder().credential(credential).applyToClusterSettings((builder)
                -> builder.hosts(Collections.singletonList(new ServerAddress(host, port)))).build();

        client = MongoClients.create(settings);
        mongoDatabase = client.getDatabase(DB);
    }

    public static boolean isConnected() {
        return mongoDatabase != null;
    }

    public static MongoCollection<Document> getCollection(String collection) {
        return mongoDatabase.getCollection(collection);
    }

    public static MongoClient getClient() {
        return client;
    }

    public static MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public static void disconnect() {
        client.close();
    }
}
