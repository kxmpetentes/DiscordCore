package de.kxmpetentes.engine.test;

import de.kxmpetentes.engine.DiscordCore;
import de.kxmpetentes.engine.build.DefaultBotBuilder;
import de.kxmpetentes.engine.command.CommandExecuter;
import de.kxmpetentes.engine.command.CommandManager;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.ArrayList;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 05.01.2021 um 00:45
 */

public class Start {

    public static void main(String[] args) {
        String token = "NzYyMDMyMDkwMTY5ODY4MzQ4.X3jPsA.IHV6TcjCqZG35LSruNIvn7y-JGo";
        ArrayList<EventListener> eventListeners = new ArrayList<>();
        ArrayList<GatewayIntent> gatewayIntents = new ArrayList<>();
        ArrayList<CommandExecuter> commandExecuters = new ArrayList<>();

        gatewayIntents.add(GatewayIntent.GUILD_MESSAGES);
        commandExecuters.add(new TestCommand());
        CommandManager commandManager = new CommandManager(commandExecuters);

        DiscordCore discordCore = new DiscordCore("!", "", commandManager);
        DefaultBotBuilder botBuilder = new DefaultBotBuilder(discordCore, token,
                OnlineStatus.DO_NOT_DISTURB, Activity.listening("Test"), gatewayIntents, eventListeners);

    }
}
