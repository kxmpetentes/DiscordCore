package de.kxmpetentes.engine.manager;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.requests.restaction.InviteAction;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 07.01.2021 um 19:14
 */

public class InviteManager {

    private final Guild guild;
    private final GuildChannel channel;

    /**
     * Creates a Invite Manager for a specified guild
     *
     * @param guild guild of the invite manager
     */
    public InviteManager(Guild guild) {
        this.guild = guild;
        this.channel = guild.getDefaultChannel();
    }

    /**
     * Creates a Invite Manager for a specified guild an channel
     *
     * @param guild   guild of the invite manager
     * @param channel channel of the invite manager
     */
    public InviteManager(Guild guild, GuildChannel channel) {
        this.guild = guild;
        this.channel = channel;
    }

    /**
     * Checks if the guild has an vanitiy invite url
     *
     * @return have the guild an vanitiy invite url?
     */
    public boolean hasVanityInvite() {
        return guild.getVanityUrl() != null;
    }

    /**
     * Gets an Invite for the default channel if the channel is null.
     *
     * @return return an InviteUrl
     */
    public String getInvite() {

        if (hasVanityInvite()) {
            return guild.getVanityUrl();
        }

        AtomicReference<String> inviteUrl = new AtomicReference<>("");

        channel.retrieveInvites().queue(invites -> {
            for (Invite invite : invites) {
                if (isPermanentInvite(invite)) {
                    inviteUrl.set(invite.getUrl());
                }
            }
        });

        if (inviteUrl.get().isEmpty()) {
            return createInvite();
        }

        return inviteUrl.get();
    }

    /**
     * Checks if the invite is permanent
     *
     * @param invite invite to check
     * @return returns true if the invite is permanent
     */
    public boolean isPermanentInvite(Invite invite) {
        return invite.getMaxAge() == 0 && invite.getMaxUses() == 0 && !invite.isTemporary();
    }

    /**
     * Creates an Invite for the guild an channel
     *
     * @return returns an InviteUrl
     */
    private String createInvite() {
        InviteAction action = channel.createInvite().setTemporary(false).setUnique(false).setMaxAge(0);
        return action.complete().getUrl();
    }
}
