plugins {
	id("com.github.johnrengelman.shadow") version("7.1.2")
	`java-library`
	`maven-publish`
}

val directory = property("group") as String
val release = property("version") as String

repositories {
	maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
	maven("https://oss.sonatype.org/content/groups/public/")
	mavenCentral()
}

dependencies {
	compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
	compileOnly("net.md-5:bungeecord-api:1.19-R0.1-SNAPSHOT")
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = "net.xconfig"
			artifactId = "XConfig"
			version = release
			
			from(components["java"])
		}
	}
}

tasks {
	shadowJar {
		archiveFileName.set("XConfig-$release.jar")
		destinationDirectory.set(file("$rootDir/bin/"))
		minimize()
	}
	
	clean {
		delete("$rootDir/bin/")
	}
}
