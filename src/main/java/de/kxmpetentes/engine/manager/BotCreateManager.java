package de.kxmpetentes.engine.manager;

import de.kxmpetentes.engine.DiscordCore;
import de.kxmpetentes.engine.command.CommandExecuter;
import de.kxmpetentes.engine.command.CommandManager;
import de.kxmpetentes.engine.listener.CommandListener;
import de.kxmpetentes.engine.listener.JoinGuildListener;
import de.kxmpetentes.engine.listener.QuitGuildListener;
import de.kxmpetentes.engine.listener.ShutdownListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 07.01.2021 um 15:25
 */

public class BotCreateManager {

    private final String token;
    private final Activity activity;
    private final OnlineStatus onlineStatus;
    private final DefaultShardManagerBuilder defaultShardManagerBuilder;

    private DiscordCore discordCore;
    private ArrayList<CommandExecuter> commandExecuters;
    private ArrayList<EventListener> listener;

    public BotCreateManager(String token, Activity activity, OnlineStatus onlineStatus) {
        this.token = token;
        this.activity = activity;
        this.onlineStatus = onlineStatus;
        this.defaultShardManagerBuilder = DefaultShardManagerBuilder.createDefault(token).setStatus(onlineStatus);
    }

    public BotCreateManager(DiscordCore discordCore, String token, OnlineStatus onlineStatus, Activity activity,
                            ArrayList<GatewayIntent> gatewayIntents, ArrayList<EventListener> listener,
                            ArrayList<CommandExecuter> commandExecuters) {
        this.discordCore = discordCore;
        this.token = token;
        this.onlineStatus = onlineStatus;
        this.activity = activity;
        this.listener = listener;
        this.commandExecuters = commandExecuters;
        this.defaultShardManagerBuilder = DefaultShardManagerBuilder.create(token, gatewayIntents).setStatus(onlineStatus);
    }

    public ShardManager getDefaultShardManager() throws LoginException {
        defaultShardManagerBuilder.setActivity(activity);

        return defaultShardManagerBuilder.build();
    }

    public ShardManager getCommandShardManager() throws LoginException {
        defaultShardManagerBuilder.enableIntents(GatewayIntent.GUILD_MESSAGES);
        defaultShardManagerBuilder.disableCache(CacheFlag.EMOTE, CacheFlag.VOICE_STATE);
        defaultShardManagerBuilder.setMemberCachePolicy(MemberCachePolicy.ONLINE);
        defaultShardManagerBuilder.addEventListeners(new CommandListener(discordCore));
        defaultShardManagerBuilder.addEventListeners(new JoinGuildListener(discordCore));
        defaultShardManagerBuilder.addEventListeners(new QuitGuildListener(discordCore));
        defaultShardManagerBuilder.addEventListeners(new ShutdownListener(discordCore));

        CommandManager commandManager = discordCore.getCommandManager();
        for (CommandExecuter commandExecuter : commandExecuters) {
            commandManager.addCommand(commandExecuter);
        }

        if (!listener.isEmpty()) {
            for (EventListener eventListener : listener) {
                defaultShardManagerBuilder.addEventListeners(eventListener);
            }
        }

        return defaultShardManagerBuilder.build();
    }

    public DiscordCore getDiscordCore() {
        return discordCore;
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }

    public Activity getActivity() {
        return activity;
    }

    public String getToken() {
        return token;
    }

    public ArrayList<CommandExecuter> getCommandExecuters() {
        return commandExecuters;
    }

    public ArrayList<EventListener> getListener() {
        return listener;
    }

    public DefaultShardManagerBuilder getDefaultShardManagerBuilder() {
        return defaultShardManagerBuilder;
    }
}
