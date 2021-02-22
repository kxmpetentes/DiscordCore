package de.kxmpetentes.engine;

import de.kxmpetentes.engine.command.CommandManager;
import de.kxmpetentes.engine.json.JsonConfiguration;
import de.kxmpetentes.engine.manager.GuildCacheManager;
import de.kxmpetentes.engine.manager.MongoAPI;
import de.kxmpetentes.engine.manager.TopGGManager;
import de.kxmpetentes.engine.model.ConsoleColors;
import de.kxmpetentes.engine.model.DeprecatedConsoleColors;
import net.dv8tion.jda.api.JDA;

import java.io.File;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 04.01.2021 um 21:40
 */

public class DiscordCore {

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
        instance = this;

        this.prefix = prefix;
        this.botIconURL = botIconURL;
        this.commandManager = new CommandManager();
    }

    public DiscordCore(String prefix, String botIconURL, CommandManager commandManager) {
        instance = this;

        this.prefix = prefix;
        this.botIconURL = botIconURL;
        this.commandManager = commandManager;
    }

    public DiscordCore(String prefix, String botIconURL, CommandManager commandManager, String topGGToken, String botID) {
        instance = this;

        this.prefix = prefix;
        this.botIconURL = botIconURL;
        this.commandManager = commandManager;
        this.topGGManager = new TopGGManager(topGGToken, botID);
    }

    public static DiscordCore getInstance() {
        return instance;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getBotIconURL() {
        return botIconURL;
    }

    public void setBotIconURL(String botIconURL) {
        this.botIconURL = botIconURL;
    }

    public TopGGManager getTopGGManager() {
        return topGGManager;
    }

    public void setTopGGManager(TopGGManager topGGManager) {
        this.topGGManager = topGGManager;
    }

    public void enableMongoDB() {

        JsonConfiguration jsonConfiguration;
        File file = new File("config.json");

        if (!file.exists()) {
            jsonConfiguration = new JsonConfiguration();
            jsonConfiguration.append("host", "localhost");
            jsonConfiguration.append("port", 27017);
            jsonConfiguration.append("user", "root");
            jsonConfiguration.append("password", "bot");
            jsonConfiguration.append("auth-database", "admin");
            jsonConfiguration.append("database", "bot");

            jsonConfiguration.saveAsConfig(file);
            System.out.println(ConsoleColors.GREEN + "Created MongoDB Config!");

            this.mongoDB = false;
            return;
        }

        jsonConfiguration = JsonConfiguration.loadDocument(file);

        System.out.println(DeprecatedConsoleColors.RED + "Versuche MongoDB zu verbinden!");
        try {
            MongoAPI.connect(jsonConfiguration.getString("host"), jsonConfiguration.getInt("port"), jsonConfiguration.getString("user"),
                    jsonConfiguration.getString("auth-database"), jsonConfiguration.getString("database"), jsonConfiguration.getString("password"));

            this.mongoDB = true;

        } catch (Exception e) {
            System.out.println(DeprecatedConsoleColors.RED + "MongoDB Client konnte sich nicht verbinden!");
            this.mongoDB = false;
        }

        if (mongoDB) {
            System.out.println(DeprecatedConsoleColors.GREEN + "MongoDB verbunden!");
        }
    }

    public boolean isMongoDBEnabled() {
        return mongoDB;
    }

    public void setJda(JDA jda) {
        this.jda = jda;
    }

    public JDA getJda() {
        return jda;
    }

    public void setGuildCacheManager(GuildCacheManager guildCacheManager) {
        this.guildCacheManager = guildCacheManager;
    }

    public GuildCacheManager getGuildCacheManager() {
        return guildCacheManager;
    }
}