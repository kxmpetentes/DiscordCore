package de.kxmpetentes.engine.command;

import de.kxmpetentes.engine.command.impl.ICommand;
import lombok.Getter;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 07.03.2021 um 14:15
 */

public abstract class Command implements ICommand {

    @Getter
    public final String commandName;
    @Getter
    public final String[] aliases;
    @Getter
    public final boolean guildCommand;

    public Command(String commandName, boolean guildCommand, String... aliases) {
        this.commandName = commandName;
        this.guildCommand = guildCommand;
        this.aliases = aliases;
    }

}