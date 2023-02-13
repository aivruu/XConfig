package net.xconfig.bungee.model;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.xconfig.bungee.model.config.ConfigurationManager;
import net.xconfig.bungee.model.objects.YamlFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static net.md_5.bungee.api.ProxyServer.getInstance;

/**
 * Implementation that manages the creation of the files.
 *
 * @author InitSync
 * @version 1.1.5
 * @since 1.0.1
 * @see ConfigurationManager
 */
public final class SimpleConfigurationManager implements ConfigurationManager {
	private static SimpleConfigurationManager instance;
	
	private final Plugin plugin;
	private final Map<String, YamlFile> cachedFiles;
	
	private SimpleConfigurationManager(Plugin plugin) {
		this.plugin = plugin;
		cachedFiles = new HashMap<>();
	}
	
	/**
	 * Register a provider for these instance.
	 *
	 * @param plugin A JavaPlugin instance provider.
	 */
	public static SimpleConfigurationManager register(Plugin plugin) {
		checkNotNull(plugin, "The Plugin instance cannot be null.");
		
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
	public Configuration get(String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		final YamlFile file = cachedFiles.get(fileName);
		if (file == null || !file.file().exists()) {
			getInstance().getLogger().severe("Cannot get the file " + fileName + " because doesn't exist.");
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
		if (file == null || !file.file().exists()) {
			getInstance().getLogger().severe("Cannot create the file " + fileName + "!");
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
			getInstance().getLogger().severe("Cannot delete the file '" + fileName + "' because already doesn't exist.");
			return;
		}
		
		try { file.file().delete(); }
		catch (SecurityException exception) {
			getInstance().getLogger().severe("Cannot delete the file '" + fileName + "'.");
			exception.printStackTrace();
		}
		
		file = null;
	}
	
	@Override
	public void reload(String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		final YamlFile file = cachedFiles.get(fileName);
		if (file == null || !file.file().exists()) {
			getInstance().getLogger().severe("Cannot reload the file '" + fileName + "' because doesn't exist.");
			return;
		}
		
		file.load();
	}
	
	@Override
	public void save(String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		final YamlFile file = cachedFiles.get(fileName);
		if (file == null || !file.file().exists()) {
			getInstance().getLogger().severe("Cannot save the file " + fileName + " because doesn't exist.");
			return;
		}
		
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(file.get(), file.file());
		} catch (IOException exception) {
			getInstance().getLogger().severe("Failed to save the file" + fileName + ".");
			exception.printStackTrace();
		}
	}
}
