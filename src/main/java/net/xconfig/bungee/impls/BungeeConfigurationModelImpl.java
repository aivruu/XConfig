package net.xconfig.bungee.impls;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.xconfig.bungee.config.BungeeConfigurationModel;

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
 * @version 1.1.2
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
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!files.containsKey(fileName) && !configurations.containsKey(fileName)) {
			logger.severe("Cannot get the file " + fileName + " because doesn't exist.");
			return null;
		}
		
		return configurations.get(fileName);
	}
	
	/**
	 * Creates and loads a new file with a folder.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName   Name of file.
	 */
	@Override
	public void build(String folderName, String fileName) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		File dataFolder = plugin.getDataFolder();
		File file;
		
		boolean notFolder = folderName.isEmpty();
		if (notFolder) file = new File(dataFolder, fileName);
		else file = new File(dataFolder + File.separator + folderName + File.separator, fileName);
		
		if (!file.exists()) {
			InputStream inputFile;
			if (notFolder) inputFile = plugin.getResourceAsStream(fileName);
			else inputFile = plugin.getResourceAsStream(folderName + File.separator + file);
			
			if (inputFile == null) {
				logger.severe("The resource '" + fileName + "' isn't inside of plugin jar file!");
				return;
			}
			
			try { Files.copy(inputFile, file.toPath()); }
			catch (IOException exception) {
				logger.severe("Cannot create the file '" + fileName + "'.");
				exception.printStackTrace();
			}
		}
		
		files.put(fileName, file);
		try { configurations.put(fileName, ConfigurationProvider.getProvider(YamlConfiguration.class).load(file)); }
		catch (IOException exception) {
			logger.severe("Cannot load the file '" + fileName + "'.");
			exception.printStackTrace();
		}
	}
	
	/**
	 * Delete a file.
	 *
	 * @param fileName Name of file.
	 */
	@Override
	public void delete(String fileName) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		File file = files.get(fileName);
		if (file == null) {
			logger.severe("Cannot delete the file '" + fileName + "' because doesn't exist.");
			return;
		}
		
		if (!file.delete()) logger.severe("Cannot delete the file '" + fileName + "'.");
	}
	
	/**
	 * Reloads a file.
	 *
	 * @param fileName Name of file.
	 */
	@Override
	public void reload(String fileName) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!files.containsKey(fileName) && !configurations.containsKey(fileName)) {
			logger.severe("Cannot reload the file " + fileName + " because doesn't exist.");
			return;
		}
		
		try { ConfigurationProvider.getProvider(YamlConfiguration.class).load(files.get(fileName)); }
		catch (IOException exception) {
			logger.severe("Cannot reload the file '" + fileName + "'.");
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
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!files.containsKey(fileName) && !configurations.containsKey(fileName)) {
			logger.severe("Cannot save the file " + fileName + " because doesn't exist.");
			return;
		}
		
		try { ConfigurationProvider.getProvider(YamlConfiguration.class).save(configurations.get(fileName), files.get(fileName)); }
		catch (IOException exception) {
			logger.severe("Failed to save the file" + fileName + ".");
			exception.printStackTrace();
		}
	}
}
