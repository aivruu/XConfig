plugins {
	id("com.github.johnrengelman.shadow") version("7.1.2")
	`java-library`
	`maven-publish`
}

val release = property("version") as String

repositories {
	maven("https://oss.sonatype.org/content/groups/public/")
	mavenCentral()
}

dependencies {
	compileOnly("net.md-5:bungeecord-api:1.19-R0.1-SNAPSHOT")
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = "net.xconfig.bungee"
			artifactId = "bungee"
			version = release
			
			from(components["java"])
		}
	}
}

tasks {
	shadowJar {
		archiveFileName.set("XConfig-bungee-$release.jar")
		destinationDirectory.set(file("$rootDir/bin/"))
	}
	
	clean {
		delete("$rootDir/bin/")
	}
}