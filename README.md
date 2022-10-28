# ⚙️ | XConfig
XConfig is a Spigot Plugin Development library. Simple and easy to use allows you make configuration files for your Spigot/Paper plugins.

# 🛠️ | Installation
XConfig installation is very easy, if you're using a dependency manager such as Maven or Gradle. Or just import the library to BuildPath of your project.

To get the jar, either download it from [GitHub](https://github.com/InitSync/XConfig/releases) or [Spigot](https://www.spigotmc.org/). Or just [Build it locally](https://github.com/InitSync/XConfig#contribute)

Gradle (Kotlin DSL)
```Gradle
repositories {
  maven("https://jitpack.io")
  mavenCentral()
}

dependencies {
  implementation("com.github.InitSync:XConfig:LATEST")
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
    <version>LATEST</version>
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
        <pattern>fr.mrmicky.fastboard</pattern>
        <!-- Relocates the library package into your project package. -->
        <shadedPattern>com.yourpackage.fastboard</shadedPattern>
      </relocation>
    </relocations>
  </configuration>
</plugin>
```

# ➕ | Contribute
Do you want contribute with the library?

* [Make a Pull Request](https://github.com/InitSync/XConfig/compare)
* [Issues](https://github.com/InitSync/XConfig/issues/new)
