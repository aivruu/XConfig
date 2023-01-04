package net.xconfig.bukkit.config;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.xconfig.bukkit.models.ConfigurationManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static org.bukkit.Bukkit.getLogger;

/**
 * Class that handles internally the files creation.
 *
 * @author InitSync
 * @version 1.1.4
 * @since 1.0.0
 * @see ConfigurationManager
 */
public final class SimpleConfigurationManager
implements ConfigurationManager {
	private final JavaPlugin plugin;
	private final Table<String, String, File> files;
	private final Table<String, String, FileConfiguration> configurations;
	
	public SimpleConfigurationManager(JavaPlugin plugin) {
		this.plugin = Objects.requireNonNull(plugin, "The plugin instance is null.");
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
	public @Nullable FileConfiguration file(@Nonnull String folderName, @Nonnull String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!exists(folderName, fileName)) {
			getLogger().severe("Cannot get the file " + fileName + " because doesn't exist.");
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
	public void build(@Nonnull String folderName, @Nonnull String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
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
	public void buildCustom(@Nonnull String folderName, @Nonnull String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		File dataFolder = plugin.getDataFolder();
		if (!dataFolder.exists()) dataFolder.mkdir();
		
		File file;
		
		if (folderName.isEmpty()) {
			file = new File(dataFolder, fileName);
			if (!file.exists()) {
				try { file.createNewFile(); }
				catch (IOException exception) {
					getLogger().severe("Cannot create the custom file '" + fileName + "'.");
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
					getLogger().severe("Cannot create the custom file '" + fileName + "'.");
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
	public void delete(@Nonnull String folderName, @Nonnull String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!exists(folderName, fileName)) {
			getLogger().severe("Cannot delete the file '" + fileName + "' because doesn't exist.");
			return;
		}
		
		try { files.get(folderName, fileName).delete(); }
		catch (SecurityException exception) {
			getLogger().severe("Cannot delete the file '" + fileName + "'.");
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
	public void reload(@Nonnull String folderName, @Nonnull String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!exists(folderName, fileName)) {
			getLogger().severe("Cannot reload the file '" + fileName + "' because doesn't exist.");
			return;
		}
		
		try { configurations.get(folderName, fileName).load(files.get(folderName, fileName)); }
		catch (InvalidConfigurationException | IOException exception) {
			getLogger().severe("Failed to reload the file" + fileName + ".");
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
	public void save(@Nonnull String folderName, @Nonnull String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!exists(folderName, fileName)) {
			getLogger().severe("Cannot save the file " + fileName + " because doesn't exist.");
			return;
		}
		
		try { configurations.get(folderName, fileName).save(files.get(folderName, fileName)); }
		catch (IOException exception) {
			getLogger().severe("Failed to save the file" + fileName + ".");
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
	public boolean exists(@Nonnull String folderName, @Nonnull String fileName) {
		return files.contains(folderName, fileName) && configurations.contains(folderName, fileName);
	}
}
