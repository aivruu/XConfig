package net.xconfig.bungee.impls;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.xconfig.bungee.config.BungeeConfigurationModel;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Implementation that manages the creation of the files.
 *
 * @author InitSync
 * @version 1.0.6
 * @since 1.0.1
 * @see net.xconfig.bungee.config.BungeeConfigurationModel
 */
public final class BungeeConfigurationModelImpl implements BungeeConfigurationModel {
	private final Plugin plugin;
	private final Map<String, File> files;
	private final Map<String, Configuration> configurations;
	
	public BungeeConfigurationModelImpl(@NotNull Plugin plugin) {
		this.plugin = Objects.requireNonNull(plugin, "The Plugin instance is null.");
		this.files = new HashMap<>();
		this.configurations = new HashMap<>();
	}
	
	/**
	 * Returns a Configuration object using the file name, if the file doesn't exist, will be
	 * return null.
	 *
	 * @param fileName Name of file.
	 * @return A Configuration object.
	 */
	@Override
	public @Nullable Configuration file(@NotNull String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (!this.files.containsKey(fileName) && !this.configurations.containsKey(fileName)) {
			this.plugin
				 .getLogger()
				 .severe("Cannot get the file " + fileName + " because doesn't exist.");
			return null;
		}
		
		return this.configurations.get(fileName);
	}
	
	/**
	 * Creates a new file with a folder.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName   Name of file.
	 */
	@Override
	public void create(@NotNull String folderName, @NotNull String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (!this.files.containsKey(fileName) && !this.configurations.containsKey(fileName)) {
			File file;
			InputStream stream = null;
			
			if (folderName.isEmpty()) {
				file = new File(this.plugin.getDataFolder(), fileName);
				if (!file.exists()) {
					stream = this.plugin
						 .getClass()
						 .getClassLoader()
						 .getResourceAsStream(fileName);
				}
			} else {
				file = new File(
					 this.plugin.getDataFolder()
					 + File.separator
					 + folderName
					 + File.separator,
					 fileName);
				if (!file.exists()) {
					stream = this.plugin.getClass()
						 .getClassLoader()
						 .getResourceAsStream(folderName
								+ File.separator
							  + file);
				}
			}
			
			if (stream == null) {
				this.plugin
					 .getLogger()
					 .severe("Internal file " + fileName + " can't be found.");
				return;
			}
			
			try {
				Files.copy(stream, file.toPath());
				this.configurations.put(fileName, ConfigurationProvider.getProvider(YamlConfiguration.class).load(file));
			} catch (Exception ignored) {}
			
			this.files.put(fileName, file);
		}
	}
	
	/**
	 * Creates multiple files.
	 *
	 * @param folderName Name of the folder.
	 * @param files Names of the files.
	 */
	@Override
	public void create(@NotNull String folderName, @NotNull String... files) {
		for (String fileName : files) this.create(folderName, fileName);
	}
	
	/**
	 * Loads an existing file.
	 *
	 * @param fileName Name of file.
	 */
	@Override
	public void load(@NotNull String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (!this.files.containsKey(fileName) && !this.configurations.containsKey(fileName)) {
			this.plugin
				 .getLogger()
				 .severe("Cannot load the file " + fileName + " because doesn't exist.");
			return;
		}
		
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.files.get(fileName));
		} catch (IOException exception) {
			this.plugin
				 .getLogger()
				 .severe("Cannot load the file " + fileName + ".");
			exception.printStackTrace();
		}
	}
	
	/**
	 * Loads various existing files.
	 *
	 * @param files Names of the files.
	 */
	@Override
	public void load(@NotNull String... files) {
		Arrays.asList(files).forEach(this::load);
	}
	
	/**
	 * Saves an existing file.
	 *
	 * @param fileName Name of file.
	 */
	@Override
	public void save(@NotNull String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (!this.files.containsKey(fileName) && !this.configurations.containsKey(fileName)) {
			Bukkit.getLogger().severe("Cannot save the file " + fileName + " because doesn't exist.");
			return;
		}
		
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(
				 this.configurations.get(fileName),
				 this.files.get(fileName)
			);
		} catch (IOException exception) {
			this.plugin
				 .getLogger()
				 .severe("Failed to save the file" + fileName + ".");
			exception.printStackTrace();
		}
	}
	
	@Override
	public void save(@NotNull String folderName, @NotNull String fileName) {}
}
