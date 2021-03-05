package de.kxmpetentes.engine.model;

import lombok.Getter;
import lombok.Setter;
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

@Getter
@Setter
public class GuildModel {

    private final Guild guild;
    private final String guildId;

    private String prefix;
    private TextChannel joinChannel;
    private TextChannel quitChannel;
    private Role autoRole;
    private Document guildDocument;

    public GuildModel(Guild guild, String prefix) {
        this.guild = guild;
        this.guildId = guild.getId();

        this.prefix = prefix;
    }

    public GuildModel(Guild guild, String prefix, TextChannel joinChannel, TextChannel quitChannel) {
        this.guild = guild;
        this.guildId = guild.getId();

        this.prefix = prefix;
        this.joinChannel = joinChannel;
        this.quitChannel = quitChannel;
    }

    public GuildModel(Guild guild, String prefix, TextChannel joinChannel, TextChannel quitChannel, Role autoRole) {
        this.guild = guild;
        this.guildId = guild.getId();

        this.prefix = prefix;
        this.joinChannel = joinChannel;
        this.quitChannel = quitChannel;
        this.autoRole = autoRole;
    }

}
