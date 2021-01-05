package de.kxmpetentes.engine.test;

import de.kxmpetentes.engine.command.CommandExecuter;
import de.kxmpetentes.engine.command.CommandType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 05.01.2021 um 02:16
 */

public class TestCommand implements CommandExecuter {

    @Override
    public void onCommand(Member member, TextChannel channel, String[] args, Message message) {
        message.reply("juhu").queue();
    }

    @Override
    public String getCommand() {
        return "test";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public boolean showInHelp() {
        return true;
    }

    @Override
    public CommandType getType() {
        return CommandType.UTILS;
    }
}
