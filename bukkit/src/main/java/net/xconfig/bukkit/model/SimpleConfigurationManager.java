package net.xconfig.bukkit.model;

import net.xconfig.bukkit.model.config.ConfigurationManager;
import net.xconfig.bukkit.model.objects.YamlFile;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static org.bukkit.Bukkit.getLogger;

/**
 * Class that handles internally the file creation.
 *
 * @author InitSync
 * @version 1.1.5
 * @since 1.0.0
 * @see ConfigurationManager
 */
public class SimpleConfigurationManager implements ConfigurationManager {
	private final JavaPlugin plugin;
	private final Map<String, YamlFile> cachedFiles;
	
	public SimpleConfigurationManager(JavaPlugin plugin) {
		this.plugin = Objects.requireNonNull(plugin, "The plugin instance is null.");
		cachedFiles = new HashMap<>();
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
