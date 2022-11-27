package net.xconfig.bungee.impls;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.xconfig.bungee.config.BungeeConfigurationModel;
import org.apache.commons.lang.Validate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Implementation that manages the creation of the files.
 *
 * @author InitSync
 * @version 1.0.8
 * @since 1.0.1
 * @see net.xconfig.bungee.config.BungeeConfigurationModel
 */
public final class BungeeConfigurationModelImpl implements BungeeConfigurationModel {
	private final Plugin plugin;
	private final Logger logger;
	private final Map<String, File> files;
	private final Map<String, Configuration> configurations;
	
	public BungeeConfigurationModelImpl(Plugin plugin, Logger logger) {
		this.plugin = Objects.requireNonNull(plugin, "The Plugin instance is null.");
		this.logger = Objects.requireNonNull(logger, "The Logger object is null.");
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
	public Configuration file(String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (!this.files.containsKey(fileName) && !this.configurations.containsKey(fileName)) {
			this.logger.severe("Cannot get the file " + fileName + " because doesn't exist.");
			return null;
		}
		
		return this.configurations.get(fileName);
	}
	
	/**
	 * Creates and loads a new file with a folder.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName   Name of file.
	 */
	@Override
	public void build(String folderName, String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		File dataFolder = this.plugin.getDataFolder();
		File file;
		
		boolean notFolder = folderName.isEmpty();
		if (notFolder) file = new File(dataFolder, fileName);
		else file = new File(dataFolder + File.separator + folderName + File.separator, fileName);
		
		if (!file.exists()) {
			InputStream inputFile = this.plugin.getResourceAsStream(fileName);
			if (inputFile == null) {
				try { file.createNewFile(); }
				catch (IOException exception) {
					this.logger.severe("Cannot create the file '" + fileName + "'.");
					exception.printStackTrace();
					return;
				}
			} else if (!notFolder) {
				inputFile = this.plugin.getResourceAsStream(folderName + File.separator + file);
				if (inputFile == null) {
					this.logger.severe("Internal file " + fileName + " can't be found.");
					return;
				}
			}
			
			try {
				assert inputFile != null;
				Files.copy(inputFile, file.toPath());
				this.configurations.put(fileName, ConfigurationProvider.getProvider(YamlConfiguration.class).load(file));
			} catch (Exception ignored) {}
		}
		
		this.files.put(fileName, file);
	}
	
	/**
	 * Creates and loads multiple files.
	 *
	 * @param folderName Name of the folder.
	 * @param files Names of the files.
	 */
	@Override
	public void build(String folderName, String... files) {
		for (String fileName : files) this.build(folderName, fileName);
	}
	
	/**
	 * Delete a file.
	 *
	 * @param fileName Name of file.
	 */
	@Override
	public void delete(String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		File file = this.files.get(fileName);
		if (file == null) {
			this.logger.severe("Cannot delete the file '" + fileName + "' because doesn't exist.");
			return;
		}
		
		if (!file.delete()) this.logger.severe("Cannot delete the file '" + fileName + "'.");
	}
	
	/**
	 * Delete one or more files.
	 *
	 * @param files Names of files to delete.
	 */
	@Override
	public void delete(String... files) {
		for (String fileName : files) this.delete(fileName);
	}
	
	/**
	 * Saves an existing file.
	 *
	 * @param fileName Name of file.
	 */
	@Override
	public void save(String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (!this.files.containsKey(fileName) && !this.configurations.containsKey(fileName)) {
			this.logger.severe("Cannot save the file " + fileName + " because doesn't exist.");
			return;
		}
		
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(
				this.configurations.get(fileName),
				this.files.get(fileName)
			);
		} catch (IOException exception) {
			this.logger.severe("Failed to save the file" + fileName + ".");
			exception.printStackTrace();
		}
	}
	
	@Override
	public void save(String folderName, String fileName) {}
}
