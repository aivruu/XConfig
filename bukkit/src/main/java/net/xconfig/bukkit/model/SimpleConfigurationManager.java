package net.xconfig.bukkit.model;

import net.xconfig.bukkit.model.config.ConfigurationManager;
import net.xconfig.bukkit.model.objects.YamlFile;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.bukkit.Bukkit.getLogger;

/**
 * Class that handles internally the file creation.
 *
 * @author InitSync
 * @version 1.1.6
 * @since 1.0.0
 * @see ConfigurationManager
 */
public class SimpleConfigurationManager implements ConfigurationManager {
	private static SimpleConfigurationManager instance;
	
	private final JavaPlugin plugin;
	private final Map<String, YamlFile> cachedFiles;
	
	private SimpleConfigurationManager(JavaPlugin plugin) {
		this.plugin = plugin;
		cachedFiles = new HashMap<>();
	}
	
	/**
	 * Register a provider for these instance.
	 *
	 * @param plugin A JavaPlugin instance provider.
	 */
	public static SimpleConfigurationManager register(JavaPlugin plugin) {
		checkNotNull(plugin, "The JavaPlugin instance cannot be null.");
		
		return instance = new SimpleConfigurationManager(plugin);
	}
	
	/**
	 * Returns a SimpleConfigurationHandler instance.
	 *
	 * @return The SimpleConfigurationHandler instance or null if there are no a provider registered.
	 */
	public static SimpleConfigurationManager get() {
		return instance;
	}
	
	/**
	 * Unregister the provider for these instance.
	 */
	public static SimpleConfigurationManager unregister() {
		return instance = null;
	}
	
	@Override
	public FileConfiguration get(String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		final YamlFile file = cachedFiles.get(fileName);
		if (file == null || !file.file().exists()) {
			getLogger().severe("Cannot get the file " + fileName + " because doesn't exist.");
			return null;
		}
		
		return file.get();
	}
	
	@Override
	public YamlFile getYaml(String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		return cachedFiles.getOrDefault(fileName, null);
	}
	
	@Override
	public void build(String folderName, String fileName, boolean custom) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		final YamlFile file = cachedFiles.put(fileName, new YamlFile(plugin, folderName, fileName));
		if (file == null) {
			getLogger().severe("Cannot create the file " + fileName + "!");
			return;
		}
		
		if (custom) {
			file.createAsCustom();
			return;
		}
		
		file.create();
	}
	
	@Override
	public void delete(String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		YamlFile file = cachedFiles.remove(fileName);
		if (file == null || !file.file().exists()) {
			getLogger().severe("Cannot delete the file " + fileName + " because already doesn't exist.");
			return;
		}
		
		try { file.file().delete(); }
		catch (SecurityException exception) {
			getLogger().severe("Cannot delete the file '" + fileName + "'.");
			exception.printStackTrace();
		}
		
		file = null;
	}
	
	@Override
	public void reload(String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		final YamlFile file = cachedFiles.get(fileName);
		if (file == null || !file.file().exists()) {
			getLogger().severe("Cannot reload the file " + fileName + " because doesn't exist.");
			return;
		}
		
		try { file.get().load(file.file()); }
		catch (InvalidConfigurationException | IOException exception) {
			getLogger().severe("Failed to load the file" + fileName + ".");
			exception.printStackTrace();
		}
	}
	
	@Override
	public void save(String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		final YamlFile file = cachedFiles.get(fileName);
		if (file == null || !file.file().exists()) {
			getLogger().severe("Cannot save the file " + fileName + " because doesn't exist.");
			return;
		}
		
		try { file.get().save(file.file()); }
		catch (IOException exception) {
			getLogger().severe("Failed to save the file" + fileName + ".");
			exception.printStackTrace();
		}
	}
}
