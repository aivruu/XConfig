package net.xconfig.bungee.impls;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.xconfig.bungee.config.BungeeConfigurationModel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Implementation that manages the creation of the files.
 *
 * @author InitSync
 * @version 1.1.3
 * @since 1.0.1
 * @see net.xconfig.bungee.config.BungeeConfigurationModel
 */
public final class BungeeConfigurationModelImpl
implements BungeeConfigurationModel {
	private final Plugin plugin;
	private final Logger logger;
	private final Table<String, String, File> files;
	private final Table<String, String, Configuration> configurations;
	
	public BungeeConfigurationModelImpl(Plugin plugin) {
		this.plugin = Objects.requireNonNull(plugin, "The Plugin instance is null.");
		this.logger = ProxyServer.getInstance().getLogger();
		this.files = HashBasedTable.create();
		this.configurations = HashBasedTable.create();
	}
	
	/**
	 * Returns a Configuration object using the file name and their name (if there are a folder), if the file doesn't exist, will be
	 * return null.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @return A Configuration object.
	 */
	@Override
	public Configuration file(String folderName, String fileName) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!isCreated(folderName, fileName)) {
			logger.severe("Cannot get the file " + fileName + " because doesn't exist.");
			return null;
		}
		
		return configurations.get(folderName, fileName);
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
		
		files.put(folderName, fileName, file);
		try { configurations.put(fileName, folderName, ConfigurationProvider.getProvider(YamlConfiguration.class).load(file)); }
		catch (IOException exception) {
			logger.severe("Cannot load the file '" + fileName + "'.");
			exception.printStackTrace();
		}
	}
	
	/**
	 * Creates an loads a new file custom with/without a folder.
	 * This method allows create files that not is inside of plugin jar file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName   Name of file.
	 */
	@Override
	public void buildCustom(String folderName, String fileName) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		File dataFolder = plugin.getDataFolder();
		if (!dataFolder.exists()) dataFolder.mkdir();
		
		File file;
		
		boolean notFolder = folderName.isEmpty();
		if (notFolder) file = new File(dataFolder, fileName);
		else file = new File(dataFolder + File.separator + folderName + File.separator, fileName);
		
		if (!file.exists()) {
			try { file.createNewFile(); }
			catch (IOException exception) {
				logger.severe("Cannot create the custom file '" + fileName + "'.");
				exception.printStackTrace();
				return;
			}
		}
		
		files.put(folderName, fileName, file);
		try { configurations.put(fileName, folderName, ConfigurationProvider.getProvider(YamlConfiguration.class).load(file)); }
		catch (IOException exception) {
			logger.severe("Cannot load the file '" + fileName + "'.");
			exception.printStackTrace();
		}
	}
	
	/**
	 * Delete a file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	@Override
	public void delete(String folderName, String fileName) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!isCreated(folderName, fileName)) {
			logger.severe("Cannot delete the file '" + fileName + "' because doesn't exist.");
			return;
		}
		
		try { files.get(folderName, fileName).delete(); }
		catch (SecurityException exception) {
			logger.severe("Cannot delete the file '" + fileName + "'.");
			exception.printStackTrace();
		}
		
		files.remove(folderName, fileName);
		configurations.remove(folderName, fileName);
	}
	
	/**
	 * Reloads a file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	@Override
	public void reload(String folderName, String fileName) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!isCreated(folderName, fileName)) {
			logger.severe("Cannot reload the file '" + fileName + "' because doesn't exist.");
			return;
		}
		
		try { ConfigurationProvider.getProvider(YamlConfiguration.class).load(files.get(folderName, fileName)); }
		catch (IOException exception) {
			logger.severe("Cannot reload the file '" + fileName + "'.");
			exception.printStackTrace();
		}
	}
	
	/**
	 * Saves an existing file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	@Override
	public void save(String folderName, String fileName) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!isCreated(folderName, fileName)) {
			logger.severe("Cannot save the file " + fileName + " because doesn't exist.");
			return;
		}
		
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(
				 configurations.get(folderName, fileName),
				 files.get(folderName, fileName)
			);
		} catch (IOException exception) {
			logger.severe("Failed to save the file" + fileName + ".");
			exception.printStackTrace();
		}
	}
	
	/**
	 * Returns true if the file specified exists, overwise return false.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName   Name of file.
	 * @return A boolean value.
	 */
	@Override
	public boolean isCreated(String folderName, String fileName) {
		return files.contains(folderName, fileName) && configurations.contains(folderName, fileName);
	}
}
