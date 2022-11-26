package net.xconfig.bukkit.impls;

import net.xconfig.bukkit.config.BukkitConfigurationModel;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Class that handles internally the files creation.
 *
 * @author InitSync
 * @version 1.0.7
 * @since 1.0.0
 * @see BukkitConfigurationModel
 */
public final class BukkitConfigurationModelImpl implements BukkitConfigurationModel {
	private final JavaPlugin plugin;
	private final Logger logger;
	private final Map<String, File> files;
	private final Map<String, FileConfiguration> configurations;
	
	public BukkitConfigurationModelImpl(JavaPlugin plugin, Logger logger) {
		this.plugin = Objects.requireNonNull(plugin, "The plugin instance is null.");
		this.logger = Objects.requireNonNull(logger, "The Logger object is null.");
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
	public FileConfiguration file(String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (!this.configurations.containsKey(fileName)) {
			this.logger.severe("Cannot get the file " + fileName + " because doesn't exist.");
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
	public void create(String folderName, String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (this.files.containsKey(fileName) && this.configurations.containsKey(fileName)) return;

		File file;
		boolean notFolder = folderName.isEmpty();
		if (notFolder) file = new File(this.plugin.getDataFolder(), fileName);
		else {
			file = new File(
				 this.plugin.getDataFolder() +
						File.separator +
						folderName +
						File.separator,
				 fileName
			);
		}
		
		if (!file.exists()) {
			if (this.plugin.getResource(fileName) == null) {
				try { file.createNewFile(); }
				catch (IOException exception) {
					this.logger.severe("Cannot create the file '" + fileName + "'.");
					exception.printStackTrace();
					return;
				}
			} else if (notFolder) this.plugin.saveResource(fileName, false);
			else this.save(folderName, fileName);
			
			this.files.put(fileName, file);
			this.configurations.put(fileName, YamlConfiguration.loadConfiguration(file));
		}
	}
	
	/**
	 * Creates multiple files.
	 *
	 * @param folderName Name of the folder.
	 * @param files Names of the files.
	 */
	@Override
	public void create(String folderName, String... files) {
		for (String fileName : files) this.create(folderName, fileName);
	}
	
	/**
	 * Loads an existing file.
	 *
	 * @param fileName Name of file.
	 */
	@Override
	public void load(String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (!this.files.containsKey(fileName) && !this.configurations.containsKey(fileName)) {
			this.logger.severe("Cannot load the file " + fileName + " because doesn't exist.");
			return;
		}
		
		YamlConfiguration.loadConfiguration(this.files.get(fileName));
	}
	
	/**
	 * Loads various existing files.
	 *
	 * @param files Names of the files.
	 */
	@Override
	public void load(String... files) {
		Arrays.asList(files).forEach(this::load);
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
	 * Reloads a file.
	 *
	 * @param fileName Name of file.
	 */
	@Override
	public void reload(String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (!this.files.containsKey(fileName) && !this.configurations.containsKey(fileName)) {
			this.logger.severe("Cannot reload the file " + fileName + " because doesn't exist.");
			return;
		}
		
		try {
			this.configurations
				 .get(fileName)
				 .load(this.files.get(fileName));
		} catch (InvalidConfigurationException | IOException exception) {
			this.logger.severe("Failed to reload the file" + fileName + ".");
			exception.printStackTrace();
		}
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
			this.configurations
				 .get(fileName)
				 .save(this.files.get(fileName));
		} catch (IOException exception) {
			this.logger.severe("Failed to save the file" + fileName + ".");
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
	public void save(String folderName, String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		this.plugin.saveResource(folderName + File.separator + fileName, false);
	}
}
