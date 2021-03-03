````Java
import de.kxmpetentes.engine.command.CommandExecutor;
import de.kxmpetentes.engine.command.CommandType;
import de.kxmpetentes.engine.model.EmbedModel;
import de.kxmpetentes.engine.utils.minecraft.NameHistoryUtil;
import de.kxmpetentes.engine.utils.minecraft.UUIDFetcher;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class NameHistoryCommand implements CommandExecutor {

    @Override
    public void onCommand(Member member, TextChannel channel, String[] args, Message message) {
        try {
            if (args.length >= 1) {
                StringBuilder stringBuilder = new StringBuilder();
                EmbedModel embedModel = new EmbedModel(
                        "",
                        member.getEffectiveName(),
                        "",
                        Color.DARK_GRAY,
                        "",
                        member.getUser().getAvatarUrl(),
                        message.getGuild().getName()
                );

                for (UUIDFetcher uuidFetcher : NameHistoryUtil.Companion.getNameHistory(args[1])) {
                    stringBuilder.append(uuidFetcher.name)
                            .append("\n");
                }
                embedModel.addField("UUID: _" + UUIDFetcher.getUUID(args[1]).toString() + "_", stringBuilder.toString(), false)
                        .createMessage().reply(message);
            } else {
                new EmbedModel(
                        "**Fehler**",
                        member.getEffectiveName(),
                        "",
                        Color.RED,
                        "Bitte benutze " + getUsage() + "!",
                        member.getUser().getAvatarUrl(),
                        message.getGuild()
                ).createMessage().reply(message);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CommandType getType() {
        return CommandType.UTILS;
    }

    @Override
    public String getCommand() {
        return "namehistory";
    }

    @Override
    public String getDescription() {
        return "Hiermit kannst du die Namehistory von einem Minecraft Spieler nachschauen!";
    }

    @Override
    public String getUsage() {
        return "namehistory [IngameName]";
    }
}
````
