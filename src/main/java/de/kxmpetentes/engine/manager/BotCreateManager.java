package de.kxmpetentes.engine.manager;

import de.kxmpetentes.engine.DiscordCore;
import de.kxmpetentes.engine.command.Command;
import de.kxmpetentes.engine.command.CommandManager;
import de.kxmpetentes.engine.listener.CommandListener;
import de.kxmpetentes.engine.listener.JoinGuildListener;
import de.kxmpetentes.engine.listener.QuitGuildListener;
import de.kxmpetentes.engine.listener.ShutdownListener;
import lombok.Getter;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.Collection;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 07.01.2021 um 15:25
 */

@Getter
public class BotCreateManager {

    private final String token;
    private final Activity activity;
    private final OnlineStatus onlineStatus;
    private final DefaultShardManagerBuilder defaultShardManagerBuilder;

    private DiscordCore discordCore;
    private Collection<Command> commandExecuters;
    private Collection<EventListener> listener;

    /**
     * Creates a new Discord bot
     *
     * @param token        bot token
     * @param activity     activity for the bot
     * @param onlineStatus online status for the bot
     */
    public BotCreateManager(String token, Activity activity, OnlineStatus onlineStatus) {
        this.token = token;
        this.activity = activity;
        this.onlineStatus = onlineStatus;
        this.defaultShardManagerBuilder = DefaultShardManagerBuilder.createDefault(token).setStatus(onlineStatus);
    }

    /**
     * Creates a new Discord bot
     *
     * @param discordCore      the discordCore instance
     * @param token            bot token
     * @param onlineStatus     activity for the bot
     * @param activity         online status for the bot
     * @param gatewayIntents   gatewayIntents for the discord restapi
     * @param listener         listener extends EventListener
     * @param commandExecuters commandExecutor extends Command
     */
    public BotCreateManager(DiscordCore discordCore, String token, OnlineStatus onlineStatus, Activity activity,
                            Collection<GatewayIntent> gatewayIntents, Collection<EventListener> listener,
                            Collection<Command> commandExecuters) {
        this.discordCore = discordCore;
        this.token = token;
        this.onlineStatus = onlineStatus;
        this.activity = activity;
        this.listener = listener;
        this.commandExecuters = commandExecuters;
        this.defaultShardManagerBuilder = DefaultShardManagerBuilder.create(token, gatewayIntents).setStatus(onlineStatus);
    }

    /**
     * Creates a default shardmanager
     *
     * @return default shardmanager
     * @throws LoginException if the token is invalid
     */
    public ShardManager getDefaultShardManager() throws LoginException {
        defaultShardManagerBuilder.setActivity(activity);

        return defaultShardManagerBuilder.build();
    }

    /**
     * Gets the command shardmanager
     *
     * @return ShardManager for Commands
     * @throws LoginException if the token is invalid
     */
    public ShardManager getCommandShardManager() throws LoginException {
        defaultShardManagerBuilder.enableIntents(GatewayIntent.GUILD_MESSAGES);
        defaultShardManagerBuilder.disableCache(CacheFlag.EMOTE, CacheFlag.VOICE_STATE);
        defaultShardManagerBuilder.setMemberCachePolicy(MemberCachePolicy.ONLINE);

        defaultShardManagerBuilder.addEventListeners(new CommandListener(discordCore));

        if (discordCore.isMongoDBEnabled()) {
            defaultShardManagerBuilder.addEventListeners(new JoinGuildListener(discordCore));
            defaultShardManagerBuilder.addEventListeners(new QuitGuildListener(discordCore));
            defaultShardManagerBuilder.addEventListeners(new ShutdownListener(discordCore));
        }

        CommandManager commandManager = discordCore.getCommandManager();
        for (Command commandExecuter : commandExecuters) {
            commandManager.addCommand(commandExecuter);
        }

        if (!listener.isEmpty()) {
            for (EventListener eventListener : listener) {
                defaultShardManagerBuilder.addEventListeners(eventListener);
            }
        }

        return defaultShardManagerBuilder.build();
    }

}
