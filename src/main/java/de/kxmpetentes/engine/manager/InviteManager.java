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

    private final Guild guild;
    private final TextChannel channel;

    public InviteManager(Guild guild, TextChannel channel) {
        this.guild = guild;
        this.channel = channel;
    }

    public String getInvite() {

        if (guild.getVanityUrl() != null) {
            return guild.getVanityUrl();
        }

        AtomicReference<String> inviteUrl = new AtomicReference<>("");

        channel.retrieveInvites().queue(invites -> {

            for (Invite invite : invites) {
                if (invite.getMaxAge() == 0 && invite.getMaxUses() == 0 && !invite.isTemporary()) {
                    inviteUrl.set(invite.getUrl());
                }
            }
        });

        if (inviteUrl.get().equals("")) {
            return createInvite();
        }

        return inviteUrl.get();
    }

    public String createInvite() {
        InviteAction action = channel.createInvite().setTemporary(false).setUnique(false).setMaxAge(0);
        return action.complete().getUrl();
    }
}
