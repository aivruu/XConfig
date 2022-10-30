# ⚙️ | XConfig
XConfig is a Spigot Plugin Development library. Simple and easy to use allows you make configuration files for your Spigot/Paper plugins.

[![](https://jitpack.io/v/InitSync/XConfig.svg)](https://jitpack.io/#InitSync/XConfig)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/05d1dab7b9f6420a917c5cf39f07a3da)](https://www.codacy.com/gh/InitSync/XConfig/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=InitSync/XConfig&amp;utm_campaign=Badge_Grade)

# 🛠️ | Installation
XConfig installation is very easy, if you're using a dependency manager such as Maven or Gradle. Or just import the library to BuildPath of your project.

To get the jar, either download it from [GitHub](https://github.com/InitSync/XConfig/releases) or [Spigot](https://www.spigotmc.org/resources/xconfig.105977/). Or just [Build it locally](https://github.com/InitSync/XConfig#--build)

Gradle (Kotlin DSL)
```Gradle
repositories {
  maven("https://jitpack.io")
  mavenCentral()
}

dependencies {
  implementation("com.github.InitSync:XConfig:1.0.1")
}

tasks {
  // Relocates the library package into your project package.
  shadowJar {
    relocate("net.xconfig", "com.yourpackage.xconfig")
  }
}
```

Maven
```Xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io/</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.InitSync</groupId>
    <artifactId>XConfig</artifactId>
    <version>1.0.1</version>
    <scope>compile</scope>
  </dependency>
</dependencies>

<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-shade-plugin</artifactId>
  <version>3.3.0</version>
  <executions>
    <execution>
      <phase>package</phase>
      <goals>
        <goal>shade</goal>
      </goals>
    </execution>
  </executions>
  <configuration>
    <relocations>
      <relocation>
        <pattern>net.xconfig</pattern>
        <!-- Relocates the library package into your project package. -->
        <shadedPattern>com.yourpackage.xconfig</shadedPattern>
      </relocation>
    </relocations>
  </configuration>
</plugin>
```

# ➕ | Contribute
Do you want contribute with the library?

* [Make a Pull Request](https://github.com/InitSync/XConfig/compare)
* [Issues](https://github.com/InitSync/XConfig/issues/new)

# ✅ | Build
If you want build the project locally, download it, you must be had Gradle and Java 8+ for this.

Now for build the project
```
git clone https://github.com/InitSync/XConfig
cd XConfig
./gradlew clean shadowJar
```

The file will be at ```bin/XConfig-release.jar```.

# 🎫 | License
This project is licensed under the GNU General Public License v3.0 license, for more details see the file [License](LICENSE)
