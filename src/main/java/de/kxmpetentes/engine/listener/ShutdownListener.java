package de.kxmpetentes.engine.listener;

import de.kxmpetentes.engine.DiscordCore;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 06.01.2021 um 02:39
 */

public class ShutdownListener extends ListenerAdapter {

    private final DiscordCore discordCore;

    public ShutdownListener(DiscordCore discordCore) {
        this.discordCore = discordCore;
    }

    @Override
    public void onShutdown(@NotNull ShutdownEvent event) {
        discordCore.getGuildCacheManager().updateGuilds();
    }

    @Override
    public void onDisconnect(@NotNull DisconnectEvent event) {
        discordCore.getGuildCacheManager().updateGuilds();
    }
}
