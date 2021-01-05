package de.kxmpetentes.engine.command;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 04.01.2021 um 21:47
 */

public class CommandManager {

    private final ArrayList<CommandExecuter> commands;

    public CommandManager() {
        commands = new ArrayList<>();
    }

    public CommandManager(ArrayList<CommandExecuter> commands) {
        this.commands = commands;
    }

    public void addCommand(CommandExecuter commandExecuter) {
        commands.add(commandExecuter);
    }

    public void addCommands(CommandExecuter... commandExecuters) {
        commands.addAll(Arrays.asList(commandExecuters));
    }

    public boolean performedCommand(String command, Member member, TextChannel channel, Message message) {
        String[] args = message.getContentRaw().split(" ");
        if (member.getUser().isBot()) return false;

        System.out.println(getCommands());

        for (CommandExecuter cmd : this.commands) {
            System.out.println(cmd.getCommand());
            System.out.println(command);
            if (cmd.getCommand().equalsIgnoreCase(command)) {

                cmd.onCommand(member, channel, args, message);

                return true;

            }
        }

        return false;
    }

    public ArrayList<CommandExecuter> getCommands() {
        return commands;
    }

}