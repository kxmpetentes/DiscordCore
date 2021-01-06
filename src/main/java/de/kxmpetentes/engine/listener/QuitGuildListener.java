package de.kxmpetentes.engine.listener;

import de.kxmpetentes.engine.DiscordCore;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 06.01.2021 um 02:43
 */

public class QuitGuildListener extends ListenerAdapter {

    private final DiscordCore discordCore;

    public QuitGuildListener(DiscordCore discordCore) {
        this.discordCore = discordCore;
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {
        discordCore.getGuildCacheManager().removeGuildFromCache(event.getGuild());
    }
}
