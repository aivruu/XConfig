package net.xconfig.models;

import net.xconfig.config.ConfigurationModel;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class ManagerModel implements ConfigurationModel {
	private final JavaPlugin plugin;
	private final Map<String, File> files;
	private final Map<String, FileConfiguration> configurations;
	
	public ManagerModel(@NotNull JavaPlugin plugin) {
		this.plugin = Objects.requireNonNull(plugin, "The plugin instance is null.");
		this.files = new HashMap<>();
		this.configurations = new HashMap<>();
	}
	
	/**
	 * Returns a FileConfiguration object using the file specified.
	 *
	 * @param fileName Name of file.
	 * @return A FileConfiguration.
	 */
	@Override
	public @Nullable FileConfiguration file(@NotNull String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (!this.configurations.containsKey(fileName)) {
			Bukkit.getLogger().severe("Cannot get the file " + fileName + " because doesn't exist.");
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
		
		if (this.files.containsKey(fileName) && this.configurations.containsKey(fileName)) {
			File file;
			if (folderName.isEmpty()) {
				file = new File(this.plugin.getDataFolder(), fileName);
				if (!file.exists()) this.plugin.saveResource(fileName, false);
			} else {
				file = new File(
					 this.plugin.getDataFolder()
							+ File.separator
							+ folderName
							+ File.separator,
					 fileName
				);
				if (!file.exists()) this.save(folderName, fileName);
			}
			
			this.files.put(fileName, file);
			this.configurations.put(fileName, YamlConfiguration.loadConfiguration(file));
		}
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
			Bukkit.getLogger().severe("Cannot load the file " + fileName + " because doesn't exist.");
			return;
		}
		
		YamlConfiguration.loadConfiguration(this.files.get(fileName));
	}
	
	/**
	 * Reloads a file.
	 *
	 * @param fileName Name of file.
	 */
	@Override
	public void reload(@NotNull String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (!this.files.containsKey(fileName) && !this.configurations.containsKey(fileName)) {
			Bukkit.getLogger().severe("Cannot reload the file " + fileName + " because doesn't exist.");
			return;
		}
		
		try {
			this.configurations
				 .get(fileName)
				 .load(this.files.get(fileName));
		} catch (InvalidConfigurationException | IOException exception) {
			Bukkit.getLogger().severe("Failed to reload the file" + fileName + ".");
			exception.printStackTrace();
		}
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
			Bukkit.getLogger().severe("Cannot reload the file " + fileName + " because doesn't exist.");
			return;
		}
		
		try {
			this.configurations
				 .get(fileName)
				 .save(this.files.get(fileName));
		} catch (IOException exception) {
			Bukkit.getLogger().severe("Failed to save the file" + fileName + ".");
			exception.printStackTrace();
		}
	}
	
	/**
	 * Saves the default content of file.
	 *
	 * @param folderName Name of the folder of file.
	 * @param fileName   Name of file.
	 */
	@Override
	public void save(@NotNull String folderName, @NotNull String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		this.plugin.saveResource(
			 this.plugin.getDataFolder()
			 + File.separator
			 + folderName
			 + File.separator
			 + fileName,
			 false
		);
	}
}
