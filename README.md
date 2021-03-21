## JavaDocs and examples will follow soon. This project is just beginning

![Java CI with Maven](https://github.com/kxmpetentes/DiscordEngine/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)
[![Scrutinizer Code Quality](https://scrutinizer-ci.com/g/kxmpetentes/DiscordCore/badges/quality-score.png?b=master)](https://scrutinizer-ci.com/g/kxmpetentes/DiscordCore/?branch=master)
[![CodeFactor](https://www.codefactor.io/repository/github/kxmpetentes/discordcore/badge/master)](https://www.codefactor.io/repository/github/kxmpetentes/discordcore/overview/master)
[![Discord Bots](https://top.gg/api/widget/status/765850867508445215.svg)](https://top.gg/bot/765850867508445215)

Currently it's just a simple CommandHelper for the JDA. But there will be more features soon

Maven

````xml
<repositories>

    <!-- Discord Core Repo -->
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

<h3>Docs</h3>

[Documentation & Examples](https://github.com/kxmpetentes/DiscordCore/wiki)

