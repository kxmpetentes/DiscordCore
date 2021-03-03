````Java
package de.kxmpetentes.engine;

import de.kxmpetentes.engine.command.CommandExecutor;
import de.kxmpetentes.engine.command.CommandType;
import de.kxmpetentes.engine.manager.GuildCacheManager;
import de.kxmpetentes.engine.model.EmbedModel;
import de.kxmpetentes.engine.model.GuildModel;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class CustomPrefixCommand implements CommandExecutor {

    @Override
    public void onCommand(Member member, TextChannel channel, String[] args, Message message) {
        Guild guild = message.getGuild();
        GuildCacheManager guildCacheManager = DiscordCore.getInstance().getGuildCacheManager();
        GuildModel guildModel = guildCacheManager.getGuildModel(guild);

        if (args.length != 1) {
            new EmbedModel("Wrong usage!",
                    null,
                    member.getUser().getAvatarUrl(),
                    Color.RED,
                    "Use: " + guildModel.getPrefix() + getUsage(),
                    null,
                    guild.getName()).createMessage().sendToTextChannel(channel);
            
            return;
        }

        if (!member.hasPermission(Permission.ADMINISTRATOR)) {
            String prefix = args[0];
            
            guildModel.setPrefix(prefix);
            guildCacheManager.updateGuild(guildModel);
            
            new EmbedModel("Prefix updated",
                    null,
                    member.getUser().getAvatarUrl(),
                    Color.green,
                    "Prefix set to: " + prefix,
                    null,
                    guild.getName()).createMessage().sendToTextChannel(channel);
            
        }
    }

    @Override
    public String getCommand() {
        return "prefix";
    }

    @Override
    public String getDescription() {
        return "Sets the prefix of this server";
    }

    @Override
    public String getUsage() {
        return "prefix <Prefix>";
    }

    @Override
    public boolean showInHelp() {
        return true;
    }

    @Override
    public CommandType getType() {
        return CommandType.SETUP;
    }
}
````