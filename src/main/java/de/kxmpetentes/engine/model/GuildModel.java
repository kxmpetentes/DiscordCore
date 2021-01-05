package de.kxmpetentes.engine.model;

import de.kxmpetentes.engine.language.LanguageTypes;
import net.dv8tion.jda.api.entities.Guild;

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

    public GuildModel(Guild guild, String prefix, LanguageTypes language) {
        this.guild = guild;
        this.guildId = guild.getId();

        this.prefix = prefix;
        this.language = language;
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
}
