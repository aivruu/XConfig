package net.xconfig.bukkit.model;

import net.xconfig.bukkit.TextUtils;
import net.xconfig.bukkit.model.config.ConfigurationHandler;
import net.xconfig.bukkit.model.config.ConfigurationManager;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.bukkit.Bukkit.getLogger;

/**
 * Class to handle the configuration files and get values from that files.
 *
 * @author InitSync
 * @version 1.1.6
 * @since 1.0.0
 * @see ConfigurationHandler
 */
public class SimpleConfigurationHandler implements ConfigurationHandler {
	private static SimpleConfigurationHandler instance;
	
	private final ConfigurationManager configuration;
	
	private SimpleConfigurationHandler(ConfigurationManager configuration) {
		this.configuration = configuration;
	}
	
	/**
	 * Register a provider for these instance.
	 *
	 * @param manager ConfigurationManager object required for operate the handler.
	 */
	public static SimpleConfigurationHandler register(ConfigurationManager manager) {
		checkNotNull(manager, "The ConfigurationManager object cannot be null.");
		
		return instance = new SimpleConfigurationHandler(manager);
	}
	
	/**
	 * Returns a SimpleConfigurationHandler instance.
	 *
	 * @return The SimpleConfigurationHandler instance or null if there are no a provider registered.
	 */
	public static SimpleConfigurationHandler get() {
		return instance;
	}
	
	/**
	 * Unregister the provider for these instance.
	 */
	public static SimpleConfigurationHandler unregister() {
		return instance = null;
	}
	
	@Override
	public void write(String fileName, String path, Object value) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path for the value is empty.");
		checkNotNull(value, "The value to set can't be null.");
		
		configuration.get(fileName).set(path, value);
	}
	
	@Override
	public String text(String fileName, String path, boolean colorize) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		final String text = configuration.get(fileName).getString(path);
		if (text == null) {
			getLogger().severe("Cannot get the String value because that path doesn't exist!");
			return null;
		}
		
		return colorize ? TextUtils.colorize(text) : text;
	}
	
	@Override
	public String text(String fileName, String path, String defaultText, boolean colorize) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		final String text = configuration.get(fileName).getString(path, defaultText);
		return colorize ? TextUtils.colorize(text) : text;
	}
	
	@Override
	public int number(String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.get(fileName).getInt(path);
	}
	
	@Override
	public int number(String fileName, String path, int defaultNumber) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.get(fileName).getInt(path, defaultNumber);
	}
	
	@Override
	public Object any(String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		final Object object = configuration.get(fileName).get(path);
		if (object == null) {
			getLogger().severe("Cannot get the Object value because that path doesn't exist!");
			return null;
		}
		
		return object;
	}
	
	@Override
	public Object any(String fileName, String path, Object defaultObject) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		checkNotNull(defaultObject, "The default object to return can't be null.");
		
		return configuration.get(fileName).get(path, defaultObject);
	}
	
	@Override
	public List<?> list(String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		final List<?> list = configuration.get(fileName).getList(path);
		if (list == null) {
			getLogger().severe("Cannot get the List value because that path doesn't exist!");
			return null;
		}
		
		return list;
	}
	
	@Override
	public List<?> list(String fileName, String path, List<?> defaultList) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		checkNotNull(defaultList, "The default List object to return can't be null.");
		
		return configuration.get(fileName).getList(path, defaultList);
	}
	
	@Override
	public List<String> textList(String fileName, String path, boolean colorize) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		final List<String> stringList = configuration.get(fileName).getStringList(path);
		return colorize ? TextUtils.colorize(stringList) : stringList;
	}
	
	@Override
	public boolean condition(String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.get(fileName).getBoolean(path);
	}
	
	@Override
	public boolean condition(String fileName, String path, boolean defaultBoolean) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.get(fileName).getBoolean(path, defaultBoolean);
	}
	
	@Override
	public boolean contains(String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.get(fileName).contains(path);
	}
	
	@Override
	public double doubleNumber(String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.get(fileName).getDouble(path);
	}
	
	@Override
	public double doubleNumber(String fileName, String path, double defaultDoubleNumber) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.get(fileName).getDouble(path, defaultDoubleNumber);
	}
	
	@Override
	public ConfigurationSection configSection(String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		final ConfigurationSection section = configuration.get(fileName).getConfigurationSection(path);
		if (section == null) {
			getLogger().severe("Cannot get the ConfigurationSection value because that path doesn't exist!");
			return null;
		}
		
		return section;
	}
}
