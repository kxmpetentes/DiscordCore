package de.kxmpetentes.engine.build;

import de.kxmpetentes.engine.DiscordCore;
import de.kxmpetentes.engine.command.CommandListener;
import de.kxmpetentes.engine.model.ConsoleColors;
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
 * Erstellt am: 04.01.2021 um 22:25
 */

public class DefaultBotBuilder {

    private final String token;
    private final OnlineStatus onlineStatus;
    private final Activity activity;
    private final ArrayList<GatewayIntent> gatewayIntents;
    private final ArrayList<EventListener> eventListeners;
    private final DiscordCore discordCore;

    private ShardManager shardManager;

    public DefaultBotBuilder(DiscordCore discordCore, String token, OnlineStatus onlineStatus, Activity activity, ArrayList<GatewayIntent> gatewayIntents, ArrayList<EventListener> eventListeners) {
        this.discordCore = discordCore;
        this.token = token;
        this.onlineStatus = onlineStatus;
        this.activity = activity;
        this.gatewayIntents = gatewayIntents;
        this.eventListeners = eventListeners;

        try {
            init();
        } catch (LoginException e) {
            System.out.println(ConsoleColors.RED + "Client konnte sich nicht verbinden!");
        }
    }

    private void init() throws LoginException {

        DefaultShardManagerBuilder defaultShardManagerBuilder = DefaultShardManagerBuilder.create(token, gatewayIntents);
        defaultShardManagerBuilder.disableCache(CacheFlag.EMOTE, CacheFlag.VOICE_STATE);
        defaultShardManagerBuilder.setMemberCachePolicy(MemberCachePolicy.ONLINE);
        defaultShardManagerBuilder.setActivity(activity);
        defaultShardManagerBuilder.setStatus(onlineStatus);
        defaultShardManagerBuilder.addEventListeners(new CommandListener(discordCore));

        if (!eventListeners.isEmpty()) {
            for (EventListener eventListener : eventListeners) {
                defaultShardManagerBuilder.addEventListeners(eventListener);
            }
        }

        shardManager = defaultShardManagerBuilder.build();
    }

    public String getToken() {
        return token;
    }

    public Activity getActivity() {
        return activity;
    }

    public ArrayList<EventListener> getEventListeners() {
        return eventListeners;
    }

    public ArrayList<GatewayIntent> getGatewayIntents() {
        return gatewayIntents;
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public DiscordCore getDiscordCore() {
        return discordCore;
    }
}
