package net.xconfig.bukkit.impls;

import com.google.common.base.Preconditions;
import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import net.xconfig.bukkit.config.BukkitConfigurationModel;
import net.xconfig.bukkit.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Class to handle the configuration files and get values from that files.
 *
 * @author InitSync
 * @version 1.1.3
 * @since 1.0.0
 * @see net.xconfig.bukkit.config.BukkitConfigurationHandler
 */
public final class BukkitConfigurationHandlerImpl
implements BukkitConfigurationHandler {
	private final BukkitConfigurationModel configuration;
	private final Logger logger;
	
	public BukkitConfigurationHandlerImpl(BukkitConfigurationModel configuration) {
		this.configuration = Objects.requireNonNull(configuration, "The BukkitConfigurationModel object is null.");
		this.logger = Bukkit.getLogger();
	}
	
	/**
	 * Set an object inside of file at the specified path.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param toPath Path for the value.
	 * @param object Object/Value to set.
	 */
	@Override
	public void write(String folderName, String fileName, String toPath, Object object) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!toPath.isEmpty(), "The path for the value is empty.");
		
		configuration.file(folderName, fileName).set(toPath, object);
	}
	
	/**
	 * Returns a String from path requested.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param colorize Do you want to apply the colors to text content?
	 * @return A string.
	 */
	@Override
	public String text(String folderName, String fileName, String path, boolean colorize) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		String text = configuration.file(folderName, fileName).getString(path);
		if (text == null) {
			logger.severe("Cannot get the text from the path of file '" + fileName + "' because doesn't exist.");
			return null;
		}
		
		return colorize ? TextUtils.colorize(text) : text;
	}
	
	/**
	 * Returns a String from path requested that can return a default value.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultText Default text if the path not exist.
	 * @param colorize Do you want to apply the colors to text content?
	 * @return A text string.
	 */
	@Override
	public String text(
		 String folderName,
		 String fileName,
		 String path,
		 String defaultText,
		 boolean colorize) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		String text = configuration.file(folderName, fileName).getString(path, defaultText);
		return colorize ? TextUtils.colorize(text) : text;
	}
	
	/**
	 * Returns a number.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A number
	 */
	@Override
	public int number(String folderName, String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getInt(path);
	}
	
	/**
	 * Returns an int number or a default value.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultNumber Default number if the path not exist.
	 * @return A number
	 */
	@Override
	public int number(String folderName, String fileName, String path, int defaultNumber) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getInt(path, defaultNumber);
	}
	
	/**
	 * Returns an object from the path.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return An object.
	 */
	@Override
	public Object any(String folderName, String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		Object object = configuration.file(folderName, fileName).get(path);
		if (object == null) {
			logger.severe("Cannot get the object from the path of file '" + fileName + "' because doesn't exist.");
			return null;
		}
		
		return object;
	}
	
	/**
	 * Returns an object from the path or a default value.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultObject Default object if the path not exist.
	 * @return An object.
	 */
	@Override
	public Object any(String folderName, String fileName, String path, Object defaultObject) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		Preconditions.checkArgument(defaultObject != null, "The default object to return can't be null.");
		
		return configuration.file(folderName, fileName).get(path, defaultObject);
	}
	
	/**
	 * Returns a list.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A list.
	 */
	@Override
	public List<?> list(String folderName, String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getList(path);
	}
	
	/**
	 * Returns a list from the path requested, or a default value if doesn't exist.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultList Default list if the path not exist.
	 * @return A list.
	 */
	@Override
	public List<?> list(String folderName, String fileName, String path, List<?> defaultList) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getList(path, defaultList);
	}
	
	/**
	 * Returns a text list.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param colorize Do you want to apply the colors to text content?
	 * @return A string list.
	 */
	@Override
	public List<String> textList(String folderName, String fileName, String path, boolean colorize) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		List<String> textList = configuration.file(folderName, fileName).getStringList(path);
		return colorize ? TextUtils.colorize(textList) : textList;
	}
	
	/**
	 * Returns a boolean.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A boolean value.
	 */
	@Override
	public boolean condition(String folderName, String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getBoolean(path);
	}
	
	/**
	 * Returns a boolean or a default value if the path doesn't exist.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultBoolean Default boolean value if the path not exist.
	 * @return A boolean value.
	 */
	@Override
	public boolean condition(String folderName, String fileName, String path, boolean defaultBoolean) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getBoolean(path, defaultBoolean);
	}
	
	/**
	 * Checks if the file contains the path.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A boolean
	 */
	@Override
	public boolean contains(String folderName, String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).contains(path);
	}
	
	/**
	 * Returns a double number.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A double.
	 */
	@Override
	public double doubleNumber(String folderName, String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getDouble(path);
	}
	
	/**
	 * Returns a double number or a default value if the path doesn't exist.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultDoubleNumber Default double value if the path not exist.
	 * @return A double.
	 */
	@Override
	public double doubleNumber(String folderName, String fileName, String path, double defaultDoubleNumber) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getDouble(path, defaultDoubleNumber);
	}
	
	/**
	 * Returns a ConfigurationSection object.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A ConfigurationSection
	 */
	@Override
	public ConfigurationSection configSection(String folderName, String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getConfigurationSection(path);
	}
}
