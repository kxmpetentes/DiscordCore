package de.kxmpetentes.engine.manager;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.TextChannel;
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

    // TODO: javadocs

    private final Guild guild;
    private final TextChannel channel;

    public InviteManager(Guild guild) {
        this.guild = guild;
        this.channel = guild.getDefaultChannel();
    }

    public InviteManager(Guild guild, TextChannel channel) {
        this.guild = guild;
        this.channel = channel;
    }

    public boolean hasVanityInvite() {
        return guild.getVanityUrl() != null;
    }

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

    public boolean isPermanentInvite(Invite invite) {
        return invite.getMaxAge() == 0 && invite.getMaxUses() == 0 && !invite.isTemporary();
    }

    private String createInvite() {
        InviteAction action = channel.createInvite().setTemporary(false).setUnique(false).setMaxAge(0);
        return action.complete().getUrl();
    }
}
