package de.kxmpetentes.engine.model;

import de.kxmpetentes.engine.language.LanguageTypes;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bson.Document;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 05.01.2021 um 15:09
 */

public class GuildModel {

    private final Guild guild;
    private final String guildId;

    private String prefix;
    private LanguageTypes language;
    private TextChannel joinChannel;
    private TextChannel quitChannel;
    private Role autoRole;
    private Document guildDocument;


    public GuildModel(Guild guild, String prefix, LanguageTypes language) {
        this.guild = guild;
        this.guildId = guild.getId();

        this.prefix = prefix;
        this.language = language;
    }

    public GuildModel(Guild guild, String prefix, LanguageTypes language, TextChannel joinChannel, TextChannel quitChannel) {
        this.guild = guild;
        this.guildId = guild.getId();

        this.prefix = prefix;
        this.language = language;
        this.joinChannel = joinChannel;
        this.quitChannel = quitChannel;
    }

    public GuildModel(Guild guild, String prefix, LanguageTypes language, TextChannel joinChannel, TextChannel quitChannel, Role autoRole) {
        this.guild = guild;
        this.guildId = guild.getId();

        this.prefix = prefix;
        this.language = language;
        this.joinChannel = joinChannel;
        this.quitChannel = quitChannel;
        this.autoRole = autoRole;
    }

    public Guild getGuild() {
        return guild;
    }

    public String getGuildId() {
        return guildId;
    }

    public String getPrefix() {
        return prefix;
    }

    public LanguageTypes getLanguage() {
        return language;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setLanguage(LanguageTypes language) {
        this.language = language;
    }

    public TextChannel getJoinChannel() {
        return joinChannel;
    }

    public TextChannel getQuitChannel() {
        return quitChannel;
    }

    public void setJoinChannel(TextChannel joinChannel) {
        this.joinChannel = joinChannel;
    }

    public void setQuitChannel(TextChannel quitChannel) {
        this.quitChannel = quitChannel;
    }

    public Role getAutoRole() {
        return autoRole;
    }

    public void setAutoRole(Role autoRole) {
        this.autoRole = autoRole;
    }

    public Document getGuildDocument() {
        return guildDocument;
    }

    public void setGuildDocument(Document guildDocument) {
        this.guildDocument = guildDocument;
    }
}
