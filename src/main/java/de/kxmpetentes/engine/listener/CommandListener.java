package de.kxmpetentes.engine.listener;

import de.kxmpetentes.engine.DiscordCore;
import de.kxmpetentes.engine.language.LanguageTypes;
import de.kxmpetentes.engine.manager.GuildCacheManager;
import de.kxmpetentes.engine.model.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 04.01.2021 um 21:52
 */

public class CommandListener extends ListenerAdapter {

    private final DiscordCore discordCore;

    private String prefix;
    private GuildCacheManager guildCacheManager = null;

    public CommandListener(DiscordCore discordCore) {
        this.discordCore = discordCore;
        this.prefix = discordCore.getPrefix();
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        Guild guild = event.getGuild();
        JDA jda = event.getJDA();

        if (discordCore.isMongoDBEnabled() && discordCore.getJda() == null && this.guildCacheManager == null) {
            discordCore.setJda(jda);

            this.guildCacheManager = new GuildCacheManager(discordCore, jda);
            discordCore.setGuildCacheManager(this.guildCacheManager);
        }

        GuildModel guildModel = null;
        if (this.guildCacheManager != null) {
            guildModel = this.guildCacheManager.getGuildModel(event.getGuild().getIdLong());
        }

        if (guildModel == null) {
            guildModel = new GuildModel(guild, discordCore.getPrefix(), LanguageTypes.DE);
        }
        
        prefix = guildModel.getPrefix();
        if (guildModel.getPrefix() == null) {
            prefix = discordCore.getPrefix();
        }

        String message = event.getMessage().getContentDisplay();
        Member member = event.getMessage().getMember();

        TextChannel channel = event.getChannel();

        if (message.toLowerCase().startsWith(prefix)) {
            String[] args = message.substring(prefix.length()).split(" ");
            if (args.length > 0) {
                try {
                    if (discordCore.getCommandManager().performedCommand(args[0], member, channel, event.getMessage())) {
                        System.out.println(ConsoleColors.RED + guild.getName() + ": " + member.getUser().getName() + DeprecatedConsoleColors.WHITE + ": " + event.getMessage().getContentDisplay() + DeprecatedConsoleColors.RESET);
                    }
                } catch (Exception e) {
                    new EmbedModel("**Fehler!**",
                            null,
                            null,
                            Color.red,
                            "Exception: " + e.getClass().getName() + "\n",
                            null,
                            guild.getName()).createMessage().sendToTextChannel(channel);

                    e.printStackTrace();
                }
            }
        }

    }
}