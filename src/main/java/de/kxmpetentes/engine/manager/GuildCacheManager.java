package de.kxmpetentes.engine.manager;

import com.mongodb.client.model.Filters;
import de.kxmpetentes.engine.DiscordCore;
import de.kxmpetentes.engine.language.LanguageTypes;
import de.kxmpetentes.engine.model.GuildModel;
import net.dv8tion.jda.api.entities.Guild;
import org.bson.Document;

import java.util.HashMap;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 05.01.2021 um 15:08
 */

public class GuildCacheManager {

    private final HashMap<Long, GuildModel> guildCache = new HashMap<>();

    public GuildCacheManager(DiscordCore discordCore) {
        for (Guild guild : discordCore.getJda().getGuilds()) {
            for (Document document : MongoAPI.getCollection("DiscordEngine").find(Filters.eq("settings", guild.getId()))) {
                if (document != null) {

                    String prefix = discordCore.getPrefix();
                    LanguageTypes language = LanguageTypes.DE;

                    if (document.containsKey("prefix")) {
                        prefix = document.getString("prefix");
                    }

                    if (document.containsKey("language")) {
                        language = LanguageTypes.getTypeByID(document.getInteger("language"));
                    }

                    GuildModel guildModel = new GuildModel(guild, prefix, language);
                    guildCache.put(guild.getIdLong(), guildModel);
                }
            }
        }
    }

    public HashMap<Long, GuildModel> getGuildCache() {
        return guildCache;
    }

    public GuildModel getGuildModel(long id) {
        return guildCache.get(id);
    }
}
