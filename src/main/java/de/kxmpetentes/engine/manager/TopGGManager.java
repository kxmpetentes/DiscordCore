package de.kxmpetentes.engine.manager;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import org.discordbots.api.client.DiscordBotListAPI;

import java.util.Collection;
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

    /**
     * Create a new TopGG API instance to manage the DiscordBotListAPI
     *
     * @param token API token for the botlist
     * @param botID botid of your bot
     */
    public TopGGManager(String token, String botID) {
        this.botListAPI = new DiscordBotListAPI.Builder().token(token).botId(botID).build();
    }

    /**
     * @return returns the BotList API instance
     */
    public DiscordBotListAPI getBotListAPI() {
        return botListAPI;
    }

    /**
     * Set the count of your guilds
     *
     * @param guilds GuildServer count
     */
    public void setServerStats(int guilds) {
        getBotListAPI().setStats(guilds);
    }

    /**
     * Sets the count of your guilds by Collection of Guilds
     *
     * @param guildList GuildList from the JDA
     */
    public void setServerStats(Collection<Guild> guildList) {
        getBotListAPI().setStats(guildList.size());
    }

    /**
     * Sets the count of your guilds by JDA
     *
     * @param jda jda instance
     */
    public void setServerStats(JDA jda) {
        getBotListAPI().setStats(jda.getGuilds().size());
    }

    /**
     * Checks the vote of a specified user id
     *
     * @param userID DiscordUser id
     * @return return true if the user has voted
     */
    public boolean hasVoted(String userID) {
        AtomicBoolean vote = new AtomicBoolean(false);

        getBotListAPI().hasVoted(userID).whenComplete(((voted, e) -> vote.set(voted)));

        return vote.get();
    }

    /**
     * Checks the voting multipler
     *
     * @return Returns true if a voting multiplier is active
     */
    public boolean isVotingMultiplier() {
        AtomicBoolean votingMultiplier = new AtomicBoolean(false);

        getBotListAPI().getVotingMultiplier().whenComplete((multiplier, e) -> votingMultiplier.set(multiplier.isWeekend()));

        return votingMultiplier.get();
    }
}
