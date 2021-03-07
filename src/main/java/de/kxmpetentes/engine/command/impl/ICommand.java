package de.kxmpetentes.engine.command.impl;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 07.03.2021 um 14:14
 */

public interface ICommand {

    void execute(MessageReceivedEvent event, String... args);

    String getCommandName();

    String[] getAliases();

    boolean isGuildCommand();
}

