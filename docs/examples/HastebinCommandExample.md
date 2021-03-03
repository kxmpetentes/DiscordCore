````Java
import de.kxmpetentes.engine.DiscordCore;
import de.kxmpetentes.engine.command.CommandExecutor;
import de.kxmpetentes.engine.command.CommandType;
import de.kxmpetentes.engine.model.EmbedModel;
import de.kxmpetentes.engine.utils.hastebin.Hastebin;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class HastebinCommandExample extends CommandExecutor {

    @Override
    public void onCommand(Member member, TextChannel channel, String[] args, Message message) {
        if (args.length <= 1) {
            new EmbedModel(
                    "**Haste**",
                    message.getAuthor().getName(),
                    null,
                    Color.DARK_GRAY,
                    new Hastebin()
                            .post(message.getContentRaw().substring(getCommand().length()
                                    + DiscordCore.getInstance().getGuildCacheManager().getGuildModel(message.getGuild())
                                    .getPrefix().length()), "https://haste.spotifynutzer.xyz/", false),
                    null,
                    message.getGuild().getName()
            ).createMessage().reply(message);
        }
    }

    @Override
    public String getCommand() {
        return "haste";
    }

    @Override
    public String getUsage() {
        return "haste [Message]";
    }

    @Override
    public String getDescription() {
        return "Damit kannst du einen Haste Paste erstellen!";
    }

    @Override
    public CommandType getType() {
        return CommandType.FUN;
    }
}
````