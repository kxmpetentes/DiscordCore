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

    /**
     * Connects to the MongoDB server
     *
     * @param host         hostname to connect
     * @param port         port to connect
     * @param username     username to connect
     * @param authDatabase authentication database
     * @param DB           database name
     * @param password     password to authenticate
     */
    public static void connect(String host, int port, String username, String authDatabase, String DB, String password) {
        MongoCredential credential = MongoCredential.createCredential(username, authDatabase, password.toCharArray());
        MongoClientSettings settings = MongoClientSettings.builder().credential(credential).applyToClusterSettings((builder)
                -> builder.hosts(Collections.singletonList(new ServerAddress(host, port)))).build();

        client = MongoClients.create(settings);
        mongoDatabase = client.getDatabase(DB);
    }

    /**
     * Checks if database is connected or exists
     *
     * @return false if the database is not connected
     */
    public static boolean isConnected() {
        return mongoDatabase != null;
    }

    /**
     * Get the Collection of Documents in a specific collection
     *
     * @param collection Collection name
     * @return returns the MongoCollection
     */
    public static MongoCollection<Document> getCollection(String collection) {
        return mongoDatabase.getCollection(collection);
    }

    /**
     * @return Returns the MongoClient
     */
    public static MongoClient getClient() {
        return client;
    }

    /**
     * Get the database
     *
     * @return the database if it exists
     */
    public static MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    /**
     * Disconnects if the database is connected
     */
    public static void disconnect() {
        if (isConnected()) {
            client.close();
        }
    }
}
