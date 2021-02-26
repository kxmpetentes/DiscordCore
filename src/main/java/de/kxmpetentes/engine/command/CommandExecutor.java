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

public interface CommandExecutor {

    /**
     * @param member that runs the command
     * @param channel that the command were processed
     * @param args of the command
     * @param message of the command message
     */
    void onCommand(Member member, TextChannel channel, String[] args, Message message);

    /**
     * @return the string of the command
     */
    String getCommand();

    /**
     * @return the description of the command
     */
    String getDescription();

    /**
     * @return the usage/syntax of the command
     */
    String getUsage();

    /**
     * @return the value if the command shown in help command
     */
    boolean showInHelp();

    /**
     * @return commandtype
     */
    CommandType getType();

}
