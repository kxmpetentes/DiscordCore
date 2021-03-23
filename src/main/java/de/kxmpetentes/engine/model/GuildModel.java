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

    /**
     * Creates a guildModel object
     *
     * @param guild  guild for this guildobject
     * @param prefix bot commandprefix or the guild commandprefix
     */
    public GuildModel(Guild guild, String prefix) {
        this.guild = guild;
        this.guildId = guild.getId();

        this.prefix = prefix;
    }

    /**
     * Creates a guildModel object
     *
     * @param guild       guild for this guildobject
     * @param prefix      bot commandprefix or the guild commandprefix
     * @param joinChannel the channel that sends a welcome message to the user that joined the guild
     * @param quitChannel the channel that sends a quit message
     */
    public GuildModel(Guild guild, String prefix, TextChannel joinChannel, TextChannel quitChannel) {
        this(guild, prefix);

        this.joinChannel = joinChannel;
        this.quitChannel = quitChannel;
    }

    /**
     * Creates a guildModel object
     *
     * @param guild       guild for this guildobject
     * @param prefix      bot commandprefix or the guild commandprefix
     * @param joinChannel the channel that sends a welcome message to the user that joined the guild
     * @param quitChannel the channel that sends a quit message
     * @param autoRole    the role that the bot adds to user they join
     */
    public GuildModel(Guild guild, String prefix, TextChannel joinChannel, TextChannel quitChannel, Role autoRole) {
        this(guild, prefix, joinChannel, quitChannel);

        this.autoRole = autoRole;
    }

}
