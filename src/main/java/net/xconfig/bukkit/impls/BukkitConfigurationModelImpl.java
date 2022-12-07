package net.xconfig.bukkit.impls;

import com.google.common.base.Preconditions;
import net.xconfig.bukkit.config.BukkitConfigurationModel;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Class that handles internally the files creation.
 *
 * @author InitSync
 * @version 1.1.2
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
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!configurations.containsKey(fileName)) {
			logger.severe("Cannot get the file " + fileName + " because doesn't exist.");
			return null;
		}
		
		return configurations.get(fileName);
	}
	
	/**
	 * Creates a new file with a folder.
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
			if (notFolder) plugin.saveResource(fileName, false);
			else plugin.saveResource(folderName + File.separator + fileName, false);
		}
		
		files.put(fileName, file);
		configurations.put(fileName, YamlConfiguration.loadConfiguration(file));
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
		
		try { configurations.get(fileName).load(files.get(fileName)); }
		catch (InvalidConfigurationException | IOException exception) {
			logger.severe("Failed to reload the file" + fileName + ".");
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
		
		try { configurations.get(fileName).save(files.get(fileName)); }
		catch (IOException exception) {
			this.logger.severe("Failed to save the file" + fileName + ".");
			exception.printStackTrace();
		}
	}
}
