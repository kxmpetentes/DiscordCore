package de.kxmpetentes.engine.listener;

import de.kxmpetentes.engine.DiscordCore;
import de.kxmpetentes.engine.manager.GuildCacheManager;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 05.01.2021 um 15:28
 */

public class OnReadyListener extends ListenerAdapter {

    private final DiscordCore discordCore;

    public OnReadyListener(DiscordCore discordCore) {
        this.discordCore = discordCore;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        if (discordCore.isMongoDBEnabled()) {
            discordCore.setGuildCacheManager(new GuildCacheManager(discordCore));
        }
    }
}
