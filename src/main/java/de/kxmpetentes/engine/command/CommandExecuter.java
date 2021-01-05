package de.kxmpetentes.engine.command;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 04.01.2021 um 21:42
 */

public interface CommandExecuter {

    void onCommand(Member member, TextChannel channel, String[] args, Message message);

    String getCommand();

    String getDescription();

    String getUsage();

    boolean showInHelp();

    CommandType getType();

}
