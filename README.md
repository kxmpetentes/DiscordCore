## JavaDocs and examples will follow soon. This project is just beginning

![Java CI with Maven](https://github.com/kxmpetentes/DiscordEngine/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)

Currently it's just a simple CommandHelper for the JDA. But there will be more features soon

Maven

````xml
<repositories>

    <repository>
        <id>discordcore-repo</id>
        <url>https://spotifynutzer.jfrog.ip/artifactory/DiscordCore</url>
    </repository>

</repositories>
````
````xml
<dependencies>

    <!-- DiscordEngine -->
    <dependency>
        <groupId>de.kxmpetentes</groupId>
        <artifactId>DiscordEngine</artifactId>
        <version>VERSION</version>
    </dependency>

</dependencies>
````

Gradle
````gradle
allprojects {
	repositories {
		...
		maven { url 'https://spotifynutzer.jfrog.io/artifactory/DiscordCore/' }
	}
}
````
````gradle
dependencies {
        implementation 'de.kxmpetentes:DiscordEngine:VERSION'
}
````

#Utils

NameHistoryCommand

````Java
import de.kxmpetentes.engine.command.CommandExecutor;
import de.kxmpetentes.engine.model.EmbedModel;
import de.kxmpetentes.engine.utils.minecraft.NameHistoryUtil;
import de.kxmpetentes.engine.utils.minecraft.UUIDFetcher;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class NameHistoryCommand implements CommandExecutor {

    @Override
    public void onCommand(Member member, TextChannel channel, String[] args, Message message) {
        try {
            if (args.length >= 1) {
                StringBuilder stringBuilder = new StringBuilder();
                EmbedModel embedModel = new EmbedModel(
                        "",
                        member.getEffectiveName(),
                        "",
                        Color.DARK_GRAY,
                        "",
                        member.getUser().getAvatarUrl(),
                        message.getGuild().getName()
                );

                for (UUIDFetcher uuidFetcher : NameHistoryUtil.Companion.getNameHistory(args[1])) {
                    stringBuilder.append(uuidFetcher.name)
                            .append("\n");
                }
                embedModel.addField("UUID: _" + UUIDFetcher.getUUID(args[1]).toString() + "_", stringBuilder.toString(), false)
                        .createMessage().reply(message);
            } else {
                new EmbedModel(
                        "**Fehler**",
                        member.getEffectiveName(),
                        "",
                        Color.RED,
                        "Bitte benutze " + getUsage() + "!",
                        member.getUser().getAvatarUrl(),
                        message.getGuild()
                ).createMessage().reply(message);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}

````



