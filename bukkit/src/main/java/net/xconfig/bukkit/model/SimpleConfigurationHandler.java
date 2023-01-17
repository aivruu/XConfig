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
 * @version 1.1.4
 * @since 1.0.0
 * @see ConfigurationHandler
 */
public class SimpleConfigurationHandler implements ConfigurationHandler {
	private final ConfigurationManager configuration;
	
	public SimpleConfigurationHandler(ConfigurationManager configuration) {
		this.configuration = Objects.requireNonNull(configuration, "The BukkitConfigurationModel object is null.");
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
			getLogger().severe("Cannot get the text from the path of file '" + fileName + "' because doesn't exist.");
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
			getLogger().severe("Cannot get the object from the path of file '" + fileName + "' because doesn't exist.");
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
		
		List<?> list = configuration.file(folderName, fileName).getList(path);
		if (list == null) {
			getLogger().severe("Cannot get the List object from the path of '" + fileName + "' file because doesn't exist.");
			return null;
		}
		
		return list;
	}
	
	@Override
	public List<?> list(String folderName, String fileName, String path, List<?> defaultList) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		checkNotNull(defaultList, "The default List object to return can't be null.");
		
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
	public ConfigurationSection configSection(String folderName, String fileName, String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		ConfigurationSection section = configuration.file(folderName, fileName).getConfigurationSection(path);
		if (section == null) {
			getLogger().severe("Cannot get the ConfigurationSection object from the path of '" + fileName + "' file because doesn't exist.");
			return null;
		}
		
		return section;
	}
}
