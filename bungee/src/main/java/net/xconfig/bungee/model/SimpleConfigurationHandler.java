package net.xconfig.bungee.model;

import net.xconfig.bungee.TextUtils;
import net.xconfig.bungee.model.config.ConfigurationHandler;
import net.xconfig.bungee.model.config.ConfigurationManager;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static net.md_5.bungee.api.ProxyServer.getInstance;

/**
 * Implementation to handle the values from the configuration on BungeeCord platforms.
 *
 * @author InitSync
 * @version 1.1.4
 * @since 1.0.1
 */
public final class SimpleConfigurationHandler implements ConfigurationHandler {
	private final ConfigurationManager configuration;
	
	public SimpleConfigurationHandler(ConfigurationManager configuration) {
		this.configuration = Objects.requireNonNull(configuration, "The ConfigurationModel is null.");
	}
	
	@Override
	public void write(String folderName, String fileName, String path, Object object) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path for the value is empty.");
		checkNotNull(object, "The object to set can't be null.");
		
		configuration.file(folderName, fileName).set(path, object);
	}
	
	@Override
	public String text(String folderName, String fileName, String path, boolean colorize) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		String text = configuration.file(folderName, fileName).getString(path);
		if (text == null) {
			getInstance().getLogger().severe("Cannot get the text from the path of file '" + fileName + "' because doesn't exist.");
			return null;
		}
		
		return colorize ? TextUtils.colorize(text) : text;
	}
	
	@Override
	public String text(String folderName, String fileName, String path, String defaultText, boolean colorize) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		String text = configuration.file(folderName, fileName).getString(path, defaultText);
		return colorize ? TextUtils.colorize(text) : text;
	}
	
	@Override
	public int number(String folderName, String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getInt(path);
	}
	
	@Override
	public int number(String folderName, String fileName, String path, int defaultNumber) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getInt(path, defaultNumber);
	}
	
	@Override
	public Object any(String folderName, String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		Object object = configuration.file(folderName, fileName).get(path);
		if (object == null) {
			getInstance().getLogger().severe("Cannot get the object from the path of file '" + fileName + "' because doesn't exist.");
			return null;
		}
		
		return object;
	}
	
	@Override
	public Object any(String folderName, String fileName, String path, Object defaultObject) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		checkNotNull(defaultObject, "The default object to return can't be null.");
		
		return configuration.file(folderName, fileName).get(path, defaultObject);
	}
	
	@Override
	public List<?> list(String folderName, String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getList(path);
	}
	
	@Override
	public List<?> list(String folderName, String fileName, String path, List<?> defaultList) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		checkNotNull(defaultList, "The default List object can't be null.");
		
		return configuration.file(folderName, fileName).getList(path, defaultList);
	}
	
	@Override
	public List<String> textList(String folderName, String fileName, String path, boolean colorize) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		List<String> stringList = configuration.file(folderName, fileName).getStringList(path);
 		return colorize ? TextUtils.colorize(stringList) : stringList;
	}
	
	@Override
	public boolean condition(String folderName, String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getBoolean(path);
	}
	
	@Override
	public boolean condition(String folderName, String fileName, String path, boolean defaultBoolean) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getBoolean(path, defaultBoolean);
	}
	
	@Override
	public boolean contains(String folderName, String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).contains(path);
	}
	
	@Override
	public double doubleNumber(String folderName, String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getDouble(path);
	}
	
	@Override
	public double doubleNumber(String folderName, String fileName, String path, double defaultDoubleNumber) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getDouble(path, defaultDoubleNumber);
	}
	
	@Override
	public char character(String folderName, String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getChar(path);
	}
	
	@Override
	public char character(String folderName, String fileName, String path, char defaultChar) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getChar(path, defaultChar);
	}
}
