package de.kxmpetentes.engine.model;

import de.kxmpetentes.engine.DiscordCore;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 04.01.2021 um 22:00
 */

public class EmbedModel {

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

    public EmbedModel(String text) {
        this.text = text;
    }

    public EmbedModel(String title, String author, String image, Color color, String text, String thumbnail, String footer) {
        this.title = title;
        this.author = author;
        this.image = image;
        this.color = color;
        this.text = text;
        this.thumbnail = thumbnail;
        this.timestep = OffsetDateTime.now();
        this.footer = footer;
    }

    public EmbedModel createMessage() {
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

    public EmbedModel addField(String name, String value, boolean inline) {
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

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAuthorLink(String authorLink) {
        this.authorLink = authorLink;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public EmbedBuilder getEmbedBuilder() {
        return embedBuilder;
    }

    public void setEmbedBuilder(EmbedBuilder embedBuilder) {
        this.embedBuilder = embedBuilder;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTimestep(TemporalAccessor timestep) {
        this.timestep = timestep;
    }

    public void setFooterIcon(String footerIcon) {
        this.footerIcon = footerIcon;
    }

    public void setFooterIcon() {
        this.footerIcon = DiscordCore.getInstance().getBotIconURL();
    }

}
