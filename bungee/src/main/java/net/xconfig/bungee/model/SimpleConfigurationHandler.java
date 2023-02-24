package net.xconfig.bungee.model;

import net.xconfig.bungee.TextUtils;
import net.xconfig.bungee.model.config.ConfigurationHandler;
import net.xconfig.bungee.model.config.ConfigurationManager;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static net.md_5.bungee.api.ProxyServer.getInstance;

/**
 * Implementation to handle the values from the configuration on BungeeCord platforms.
 *
 * @author InitSync
 * @version 1.1.7
 * @since 1.0.1
 */
public final class SimpleConfigurationHandler implements ConfigurationHandler {
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
	public static void register(ConfigurationManager manager) {
		checkNotNull(manager, "The ConfigurationManager object cannot be null.");
		
		instance = new SimpleConfigurationHandler(manager);
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
	public static void unregister() {
		instance = null;
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
			getInstance().getLogger().severe("Cannot get the String value because that path doesn't exist!");
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
			getInstance().getLogger().severe("Cannot get the Object value because that path doesn't exist!");
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
			getInstance().getLogger().severe("Cannot get the List value because that path doesn't exist!");
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
	public char character(String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.get(fileName).getChar(path);
	}
	
	@Override
	public char character(String fileName, String path, char defaultChar) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.get(fileName).getChar(path, defaultChar);
	}
}
