## JavaDocs and examples will follow soon. This project is just beginning

[![](https://jitpack.io/v/kxmpetentes/DiscordEngine.svg)](https://jitpack.io/#kxmpetentes/DiscordEngine) ![Java CI with Maven](https://github.com/kxmpetentes/DiscordEngine/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)

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
````
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
````
````
dependencies {
        implementation 'com.github.kxmpetentes:DiscordEngine:VERSION'
}
````
