package de.kxmpetentes.engine.listener;

import de.kxmpetentes.engine.DiscordCore;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 06.01.2021 um 02:01
 */

public class JoinGuildListener extends ListenerAdapter {

    private final DiscordCore discordCore;

    public JoinGuildListener(DiscordCore discordCore) {
        this.discordCore = discordCore;
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        discordCore.getGuildCacheManager().addGuildToCache(event.getGuild());
    }
}
