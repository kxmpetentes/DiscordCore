package de.kxmpetentes.engine;

import de.kxmpetentes.engine.command.CommandManager;
import de.kxmpetentes.engine.manager.TopGGManager;

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
}