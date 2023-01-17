package net.xconfig.bukkit.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.xconfig.bukkit.model.config.ConfigurationManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

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
public class SimpleConfigurationManager implements ConfigurationManager {
	private final JavaPlugin plugin;
	private final Table<String, String, File> files;
	private final Table<String, String, FileConfiguration> configurations;
	
	public SimpleConfigurationManager(JavaPlugin plugin) {
		this.plugin = Objects.requireNonNull(plugin, "The plugin instance is null.");
		this.files = HashBasedTable.create();
		this.configurations = HashBasedTable.create();
	}
	
	@Override
	public FileConfiguration file(String folderName, String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!exists(folderName, fileName)) {
			getLogger().severe("Cannot get the file " + fileName + " because doesn't exist.");
			return null;
		}
		
		return configurations.get(folderName, fileName);
	}
	
	@Override
	public void build(String folderName, String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		File file;
		
		if (folderName.isEmpty()) file = new File(plugin.getDataFolder(), fileName);
		else file = new File(plugin.getDataFolder() + File.separator + folderName + File.separator, fileName);
		
		if (!file.exists()) {
			if (folderName.isEmpty()) plugin.saveResource(fileName, false);
			else plugin.saveResource(folderName + File.separator + fileName, false);
		}
		
		files.put(folderName, fileName, file);
		configurations.put(folderName, fileName, YamlConfiguration.loadConfiguration(file));
	}
	
	@Override
	public void buildCustom(String folderName, String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdir();
		
		File file;
		
		if (folderName.isEmpty()) {
			file = new File(plugin.getDataFolder(), fileName);
			if (!file.exists()) {
				try { file.createNewFile(); }
				catch (IOException exception) {
					getLogger().severe("Cannot create the custom file '" + fileName + "'.");
					exception.printStackTrace();
					return;
				}
			}
		} else {
			new File(plugin.getDataFolder(), folderName).mkdir();
			file = new File(plugin.getDataFolder() + File.separator + folderName + File.separator, fileName);
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
	
	@Override
	public void delete(String folderName, String fileName) {
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
	
	@Override
	public void reload(String folderName, String fileName) {
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
	
	@Override
	public void save(String folderName, String fileName) {
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
	
	@Override
	public boolean exists(String folderName, String fileName) {
		return files.contains(folderName, fileName) && configurations.contains(folderName, fileName);
	}
}
