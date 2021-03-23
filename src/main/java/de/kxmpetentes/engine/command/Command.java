package de.kxmpetentes.engine.command;

import de.kxmpetentes.engine.command.impl.ICommand;
import lombok.Getter;

/**
 * @author kxmpetentes
 * @see de.kxmpetentes.engine.command.impl.ICommand;
 */
public abstract class Command implements ICommand {

    @Getter
    public final String commandName;
    @Getter
    public final String[] aliases;
    @Getter
    public final boolean guildCommand;

    /**
     * Creates a command
     *
     * @param commandName  string for the command syntax
     * @param guildCommand make the command executable at privatechat
     * @param aliases      aliases for the commandsyntax
     */
    public Command(String commandName, boolean guildCommand, String... aliases) {
        this.commandName = commandName;
        this.guildCommand = guildCommand;
        this.aliases = aliases;
    }

}