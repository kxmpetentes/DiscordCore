package de.kxmpetentes.engine;

import de.kxmpetentes.engine.command.CommandManager;
import de.kxmpetentes.engine.manager.ButtonManager;
import de.kxmpetentes.engine.manager.GuildCacheManager;
import de.kxmpetentes.engine.manager.MongoAPI;
import de.kxmpetentes.engine.manager.TopGGManager;
import de.kxmpetentes.engine.model.ConsoleColors;
import de.kxmpetentes.engine.utils.Pair;
import de.kxmpetentes.engine.utils.config.ConfigProvider;
import de.kxmpetentes.engine.utils.config.Configuration;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 04.01.2021 um 21:40
 */

@Getter
@Setter
public class DiscordCore {

    @Getter
    private static DiscordCore instance;

    private String prefix;
    private String botIconURL;
    private CommandManager commandManager;
    private TopGGManager topGGManager;
    private JDA jda;
    private ButtonManager buttonManager;
    private GuildCacheManager guildCacheManager;
    private ConfigProvider configProvider = new ConfigProvider();
    private Logger logger = LoggerFactory.getLogger("DiscordCore");

    public DiscordCore(String prefix) {
        instance = this;

        this.prefix = prefix;
        this.botIconURL = "";
        this.buttonManager = new ButtonManager();
        this.commandManager = new CommandManager();
    }

    public DiscordCore(String prefix, String botIconURL) {
        this(prefix);

        this.botIconURL = botIconURL;
    }

    public DiscordCore(String prefix, String botIconURL, CommandManager commandManager) {
        this(prefix, botIconURL);

        this.commandManager = commandManager;
    }

    public DiscordCore(String prefix, String botIconURL, CommandManager commandManager, String topGGToken, String botID) {
        this(prefix, botIconURL, commandManager);

        this.topGGManager = new TopGGManager(topGGToken, botID);
    }

    public void enableMongoDB() {
        Configuration configuration;

        File file = new File("configuration.json");

        if (!file.exists()) {
            Pair<String, Object> hostPair = new Pair<>("host", "localhost");
            Pair<String, Object> portPair = new Pair<>("port", 27017);
            Pair<String, Object> userPair = new Pair<>("user", "root");
            Pair<String, Object> passwordPair = new Pair<>("password", "SECRET");
            Pair<String, Object> authDatabasePair = new Pair<>("auth-database", "admin");
            Pair<String, Object> databasePair = new Pair<>("database", "bot");

            configuration = new Configuration(
                    hostPair,
                    portPair,
                    userPair,
                    passwordPair,
                    authDatabasePair,
                    databasePair
            );

            logger.info(ConsoleColors.GREEN + "Created MongoDB Configuration!");

            return;
        }


        logger.info(ConsoleColors.RED + "Versuche MongoDB zu verbinden!");
        try {
            configuration = configProvider.getConfigFromFile(file);

            MongoAPI.connect((String) configuration.get("host"), (int) configuration.get("port"), (String) configuration.get("user"),
                    (String) configuration.get("auth-database"), (String) configuration.get("database"), (String) configuration.get("password"));

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if (isMongoDBEnabled()) {
            logger.info("MongoDB Verbunden");
        }
    }

    public boolean isMongoDBEnabled() {
        return MongoAPI.isConnected();
    }
}