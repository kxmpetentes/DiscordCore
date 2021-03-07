package de.kxmpetentes.engine.listener;

import de.kxmpetentes.engine.DiscordCore;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 04.01.2021 um 21:52
 */

@RequiredArgsConstructor
public class CommandListener extends ListenerAdapter {

    private final DiscordCore discordCore;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (discordCore.getCommandManager().performCommand(event)) {
            DiscordCore.getInstance().getLogger().info(event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());
        }
    }
}