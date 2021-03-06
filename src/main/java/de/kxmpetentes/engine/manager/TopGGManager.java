package de.kxmpetentes.engine.manager;

import org.discordbots.api.client.DiscordBotListAPI;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 05.01.2021 um 15:51
 */

public class TopGGManager {

    private final DiscordBotListAPI botListAPI;

    public TopGGManager(String token, String botID) {
        this.botListAPI = new DiscordBotListAPI.Builder().token(token).botId(botID).build();
    }

    public DiscordBotListAPI getBotListAPI() {
        return botListAPI;
    }

    public void setServerStats(int guilds) {
        getBotListAPI().setStats(guilds);
    }

    public boolean hasVoted(String userID) {
        AtomicBoolean vote = new AtomicBoolean(false);

        getBotListAPI().hasVoted(userID).whenComplete(((voted, e) -> vote.set(voted)));

        return vote.get();
    }

    public boolean isVotingMultiplier() {
        AtomicBoolean votingMultiplier = new AtomicBoolean(false);

        getBotListAPI().getVotingMultiplier().whenComplete((multiplier, e) -> votingMultiplier.set(multiplier.isWeekend()));

        return votingMultiplier.get();
    }
}
