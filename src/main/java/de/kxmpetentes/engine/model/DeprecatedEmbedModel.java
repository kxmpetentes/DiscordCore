package de.kxmpetentes.engine.model;

import de.kxmpetentes.engine.DiscordCore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.time.temporal.TemporalAccessor;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 04.01.2021 um 22:00
 */

@Deprecated
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class DeprecatedEmbedModel {

    private String author;
    private String title;
    private String image;
    private String thumbnail;
    private String footer;
    private String footerIcon;
    private Color color;
    private String text;
    private EmbedBuilder embedBuilder = new EmbedBuilder();
    private TemporalAccessor timestep;
    private String authorLink;

    public DeprecatedEmbedModel createMessage() {
        embedBuilder.setTitle(title);
        embedBuilder.setAuthor(author, authorLink);
        embedBuilder.setImage(image);
        embedBuilder.setColor(color);
        embedBuilder.setDescription(text);
        embedBuilder.setFooter(footer, footerIcon);
        embedBuilder.setThumbnail(thumbnail);
        embedBuilder.setTimestamp(timestep);

        return this;
    }

    public DeprecatedEmbedModel addField(String name, String value, boolean inline) {
        embedBuilder.addField(name, value, inline);

        return this;
    }

    public void sendPrivate(PrivateChannel privateChannel) {
        privateChannel.sendMessage(this.embedBuilder.build()).queue();
    }

    public void sendTextChannel(TextChannel channel) {
        channel.sendMessage(this.embedBuilder.build()).queue();
    }

    public void reply(Message message) {
        message.reply(embedBuilder.build()).queue();
    }

    public void reply(Message message, EmbedBuilder embedBuilder) {
        message.reply(embedBuilder.build()).queue();
    }

    public void setFooterIcon() {
        this.footerIcon = DiscordCore.getInstance().getBotIconURL();
    }

}
