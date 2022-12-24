package net.xconfig.bukkit.impls;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.xconfig.bukkit.config.BukkitConfigurationModel;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Class that handles internally the files creation.
 *
 * @author InitSync
 * @version 1.1.3
 * @since 1.0.0
 * @see BukkitConfigurationModel
 */
public final class BukkitConfigurationModelImpl
implements BukkitConfigurationModel {
	private final JavaPlugin plugin;
	private final Logger logger;
	private final Table<String, String, File> files;
	private final Table<String, String, FileConfiguration> configurations;
	
	public BukkitConfigurationModelImpl(JavaPlugin plugin) {
		this.plugin = Objects.requireNonNull(plugin, "The plugin instance is null.");
		this.logger = Bukkit.getLogger();
		this.files = HashBasedTable.create();
		this.configurations = HashBasedTable.create();
	}
	
	/**
	 * Returns a FileConfiguration object using the file specified.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @return A FileConfiguration.
	 */
	@Override
	public FileConfiguration file(String folderName, String fileName) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!isCreated(folderName, fileName)) {
			logger.severe("Cannot get the file " + fileName + " because doesn't exist.");
			return null;
		}
		
		return configurations.get(folderName, fileName);
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
		
		files.put(folderName, fileName, file);
		configurations.put(folderName, fileName, YamlConfiguration.loadConfiguration(file));
	}
	
	/**
	 * Creates and load a new custom file with/without a folder.
	 * This method allows create files that is not inside of plugin jar file.
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
		
		if (folderName.isEmpty()) {
			file = new File(dataFolder, fileName);
			if (!file.exists()) {
				try { file.createNewFile(); }
				catch (IOException exception) {
					logger.severe("Cannot create the custom file '" + fileName + "'.");
					exception.printStackTrace();
					return;
				}
			}
		} else {
			new File(dataFolder, folderName).mkdir();
			file = new File(dataFolder + File.separator + folderName + File.separator, fileName);
			if (!file.exists()) {
				try { file.createNewFile(); }
				catch (IOException exception) {
					logger.severe("Cannot create the custom file '" + fileName + "'.");
					exception.printStackTrace();
					return;
				}
			}
		}
		
		files.put(folderName, fileName, file);
		configurations.put(folderName, fileName, YamlConfiguration.loadConfiguration(file));
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
		
		try { configurations.get(folderName, fileName).load(files.get(folderName, fileName)); }
		catch (InvalidConfigurationException | IOException exception) {
			logger.severe("Failed to reload the file" + fileName + ".");
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
		
		try { configurations.get(folderName, fileName).save(files.get(folderName, fileName)); }
		catch (IOException exception) {
			this.logger.severe("Failed to save the file" + fileName + ".");
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
