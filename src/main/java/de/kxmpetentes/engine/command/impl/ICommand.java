package de.kxmpetentes.engine.command.impl;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 07.03.2021 um 14:14
 */

public interface ICommand {

    /**
     * Executes the command
     *
     * @param event extends the MessageReceivedEvent
     * @param args  message content splittet
     */
    void execute(MessageReceivedEvent event, String... args);

    /**
     * CommandName
     *
     * @return the commandname & commandsyntax
     */
    String getCommandName();

    /**
     * Command aliases
     *
     * @return the command aliases
     */
    String[] getAliases();

    /**
     * Command executable at private chat
     *
     * @return Command executable at private chat
     */
    boolean isGuildCommand();
}

