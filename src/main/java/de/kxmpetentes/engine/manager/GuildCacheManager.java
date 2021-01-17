package de.kxmpetentes.engine.manager;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import de.kxmpetentes.engine.DiscordCore;
import de.kxmpetentes.engine.language.LanguageTypes;
import de.kxmpetentes.engine.model.GuildModel;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bson.Document;

import java.util.HashMap;
import java.util.Timer;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 05.01.2021 um 15:08
 */

public class GuildCacheManager {

    private final HashMap<Long, GuildModel> guildCache = new HashMap<>();
    private final DiscordCore discordCore;

    public GuildCacheManager(DiscordCore discordCore, JDA jda) {
        this.discordCore = discordCore;

        for (Guild guild : jda.getGuilds()) {
            for (Document document : MongoAPI.getCollection("DiscordEngine").find(Filters.eq("settings", guild.getId()))) {
                if (document != null) {

                    String prefix = discordCore.getPrefix();
                    LanguageTypes language = LanguageTypes.DE;
                    TextChannel joinChannel = null;
                    TextChannel quitChannel = null;
                    Role autoRole = null;

                    if (document.containsKey("prefix")) {
                        prefix = document.getString("prefix");
                    }

                    if (document.containsKey("language")) {
                        language = LanguageTypes.getTypeByID(document.getInteger("language"));
                    }

                    if (document.containsKey("joinChannel")) {
                        joinChannel = guild.getTextChannelById(document.getString("joinChannel"));
                    }

                    if (document.containsKey("quitChannel")) {
                        quitChannel = guild.getTextChannelById(document.getString("quitChannel"));
                    }

                    if (document.containsKey("autoRole")) {
                        autoRole = guild.getRoleById(document.getString("autoRole"));
                    }

                    GuildModel guildModel = new GuildModel(guild, prefix, language, joinChannel, quitChannel, autoRole);
                    guildCache.put(guild.getIdLong(), guildModel);
                } else {

                    GuildModel guildModel = new GuildModel(guild, discordCore.getPrefix(), LanguageTypes.DE);
                    addNewGuild(guildModel);
                    guildCache.put(guild.getIdLong(), guildModel);

                }
            }
        }
    }

    public HashMap<Long, GuildModel> getGuildCache() {
        return guildCache;
    }

    public GuildModel getGuildModel(long guildId) {

        if (guildCache.containsKey(guildId)) {
            return guildCache.get(guildId);
        } else {
            addGuildToCache(discordCore.getJda().getGuildById(guildId));
            getGuildModel(guildId);

        }

        return null;
    }

    public void addGuildToCache(Guild guild) {
        GuildModel guildModel = new GuildModel(guild, discordCore.getPrefix(), LanguageTypes.DE);
        guildCache.put(guild.getIdLong(), guildModel);

        addNewGuild(guildModel);
    }

    public void removeGuildFromCache(Guild guild) {
        GuildModel guildModel = guildCache.get(guild.getIdLong());

        updateGuild(guildModel);
        guildCache.remove(guild.getIdLong());
    }

    public void addNewGuild(GuildModel guildModel) {

        Document document = new Document();
        document.append("settings", guildModel.getGuildId());
        document.append("prefix", guildModel.getPrefix());
        document.append("language", guildModel.getLanguage().getId());
        document.append("joinChannel", "");
        document.append("quitChannel", "");
        document.append("autoRole", "");

        MongoAPI.getCollection("DiscordEngine").insertOne(document);
    }

    public void updateGuild(GuildModel guildModel) {
        MongoAPI.getCollection("DiscordEngine").updateOne(Filters.eq("settings", guildModel.getGuildId()), Updates.combine(
                Updates.set("prefix", guildModel.getPrefix()),
                Updates.set("language", guildModel.getLanguage().getId()),
                Updates.set("joinChannel", guildModel.getJoinChannel().getId()),
                Updates.set("quitChannel", guildModel.getQuitChannel().getId()),
                Updates.set("autoRole", guildModel.getAutoRole())
        ));
    }

    public void updateGuilds() {
        for (GuildModel guildModel : guildCache.values()) {
            updateGuild(guildModel);
        }
    }
}
