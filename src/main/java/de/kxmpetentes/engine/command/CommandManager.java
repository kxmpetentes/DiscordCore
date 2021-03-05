package de.kxmpetentes.engine.command;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 04.01.2021 um 21:47
 */

public class CommandManager {

    private final Collection<CommandExecutor> commands;

    public CommandManager() {
        commands = new ArrayList<>();
    }

    /**
     * @param commands arraylist of commands
     */
    public CommandManager(Collection<CommandExecutor> commands) {
        this.commands = commands;
    }

    /**
     * @param commandExecuter add new command to the commandlist
     */
    public void addCommand(CommandExecutor commandExecuter) {
        commands.add(commandExecuter);
    }

    /**
     * @param commandExecuters add an array of commands to the commandlist
     */
    public void addCommands(CommandExecutor... commandExecuters) {
        commands.addAll(Arrays.asList(commandExecuters));
    }

    public boolean performedCommand(String command, Member member, TextChannel channel, Message message) {
        String[] args = message.getContentRaw().split(" ");
        if (member.getUser().isBot()) return false;

        for (CommandExecutor cmd : this.commands) {
            if (cmd.getCommand().equalsIgnoreCase(command)) {

                cmd.onCommand(member, channel, args, message);

                return true;
            }
        }

        return false;
    }

    /**
     * @return all commands
     */
    public Collection<CommandExecutor> getCommands() {
        return commands;
    }

}