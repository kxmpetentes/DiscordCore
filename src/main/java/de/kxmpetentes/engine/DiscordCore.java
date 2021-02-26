package de.kxmpetentes.engine;

import de.kxmpetentes.engine.build.MongoDBBotBuilder;
import de.kxmpetentes.engine.command.CommandManager;
import de.kxmpetentes.engine.json.Config;
import de.kxmpetentes.engine.json.ConfigProvider;
import de.kxmpetentes.engine.manager.GuildCacheManager;
import de.kxmpetentes.engine.manager.MongoAPI;
import de.kxmpetentes.engine.manager.TopGGManager;
import de.kxmpetentes.engine.model.ConsoleColors;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;

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
    private boolean mongoDB = false;
    private JDA jda;
    private GuildCacheManager guildCacheManager;

    public DiscordCore(String prefix) {
        instance = this;

        this.prefix = prefix;
        this.botIconURL = "";
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

        ConfigProvider configProvider = new ConfigProvider();
        Config config;

        File file = new File("config.json");

        if (!file.exists()) {
            config = new Config();
            config.put("host", "localhost");
            config.put("port", 27017);
            config.put("user", "root");
            config.put("password", "SECRET");
            config.put("auth-database", "admin");
            config.put("database", "bot");

            System.out.println(ConsoleColors.GREEN + "Created MongoDB Config!");

            this.mongoDB = false;
            return;
        }


        System.out.println(ConsoleColors.RED + "Versuche MongoDB zu verbinden!");
        try {
            config = configProvider.getConfigFromFile(file);

            MongoAPI.connect((String) config.get("host"), (int) config.get("port"), (String) config.get("user"),
                    (String) config.get("auth-database"), (String) config.get("database"), (String) config.get("password"));

            this.mongoDB = true;

        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "MongoDB Client konnte sich nicht verbinden!");
            this.mongoDB = false;
        }

        if (mongoDB) {
            System.out.println(ConsoleColors.GREEN + "MongoDB verbunden!");
        }
    }

}