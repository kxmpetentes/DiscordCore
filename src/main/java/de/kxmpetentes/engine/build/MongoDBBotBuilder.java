package de.kxmpetentes.engine.build;

import de.kxmpetentes.engine.DiscordCore;
import de.kxmpetentes.engine.command.CommandExecuter;
import de.kxmpetentes.engine.manager.BotCreateManager;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 07.01.2021 um 15:22
 */

public class MongoDBBotBuilder {

    private final DiscordCore discordCore;
    private final String token;
    private final OnlineStatus onlineStatus;
    private final Activity activity;

    private ArrayList<GatewayIntent> gatewayIntents;
    private ArrayList<EventListener> eventListeners;
    private ArrayList<CommandExecuter> commandExecuters;

    public MongoDBBotBuilder(DiscordCore discordCore, String token, OnlineStatus onlineStatus, Activity activity) {
        this.discordCore = discordCore;
        this.token = token;
        this.onlineStatus = onlineStatus;
        this.activity = activity;

        discordCore.enableMongoDB();
    }

    public MongoDBBotBuilder(DiscordCore discordCore, String token, OnlineStatus onlineStatus, Activity activity, ArrayList<GatewayIntent> gatewayIntents,
                             ArrayList<EventListener> eventListeners, ArrayList<CommandExecuter> commandExecuters) {
        this.discordCore = discordCore;
        this.token = token;
        this.onlineStatus = onlineStatus;
        this.activity = activity;

        this.gatewayIntents = gatewayIntents;
        this.eventListeners = eventListeners;
        this.commandExecuters = commandExecuters;

        discordCore.enableMongoDB();
    }

    public String getToken() {
        return token;
    }

    public Activity getActivity() {
        return activity;
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }

    public ShardManager getDefaultShardManager() throws LoginException {
        if (gatewayIntents != null && eventListeners != null && commandExecuters != null) {
            return new BotCreateManager(discordCore, token, onlineStatus, activity, gatewayIntents, eventListeners, commandExecuters).getDefaultShardManager();
        } else {
            return new BotCreateManager(token, activity, onlineStatus).getDefaultShardManager();
        }
    }

    public ShardManager getCommandShardManager() throws LoginException {
        if (gatewayIntents != null && eventListeners != null && commandExecuters != null) {
            return new BotCreateManager(discordCore, token, onlineStatus, activity, gatewayIntents, eventListeners, commandExecuters).getCommandShardManager();
        } else {
            return new BotCreateManager(token, activity, onlineStatus).getCommandShardManager();
        }
    }

    public DiscordCore getDiscordCore() {
        return discordCore;
    }

}
