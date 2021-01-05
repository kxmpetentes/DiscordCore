## JavaDocs and examples will follow soon. This project is just beginning

[![](https://jitpack.io/v/kxmpetentes/DiscordEngine.svg)](https://jitpack.io/#kxmpetentes/DiscordEngine)

Currently it's just a simple CommandHelper for the JDA. But there will be more features soon

Maven

````xml
<repositories>

    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>

</repositories>
````
````xml
<dependencies>

    <!-- DiscordEngine -->
    <dependency>
        <groupId>com.github.kxmpetentes</groupId>
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
