package de.kxmpetentes.engine.example;

import de.kxmpetentes.engine.DiscordCore;
import de.kxmpetentes.engine.command.CommandExecuter;
import de.kxmpetentes.engine.command.CommandType;
import de.kxmpetentes.engine.manager.GuildCacheManager;
import de.kxmpetentes.engine.model.GuildModel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 06.01.2021 um 02:51
 */

public class PrefixCommand implements CommandExecuter {
    /**
     * @param member  that runs the command
     * @param channel that the command were processed
     * @param args    of the command
     * @param message of the command message
     */
    @Override
    public void onCommand(Member member, TextChannel channel, String[] args, Message message) {

        GuildCacheManager guildCacheManager = DiscordCore.getInstance().getGuildCacheManager();
        GuildModel guildModel = guildCacheManager.getGuildModel(member.getGuild().getIdLong());

        if (args.length == 2) {

            guildModel.setPrefix(args[1]);
            guildCacheManager.updateGuild(guildModel);

        }

        channel.sendMessage(guildModel.getPrefix()).queue();
    }

    /**
     * @return the string of the command
     */
    @Override
    public String getCommand() {
        return "prefix";
    }

    /**
     * @return the description of the command
     */
    @Override
    public String getDescription() {
        return null;
    }

    /**
     * @return the usage/syntax of the command
     */
    @Override
    public String getUsage() {
        return null;
    }

    /**
     * @return the value if the command shown in help command
     */
    @Override
    public boolean showInHelp() {
        return false;
    }

    /**
     * @return commandtype
     */
    @Override
    public CommandType getType() {
        return null;
    }
}
