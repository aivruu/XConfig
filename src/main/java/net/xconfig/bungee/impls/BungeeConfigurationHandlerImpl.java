package net.xconfig.bungee.impls;

import com.google.common.base.Preconditions;
import net.md_5.bungee.config.Configuration;
import net.xconfig.bungee.config.BungeeConfigurationHandler;
import net.xconfig.bungee.config.BungeeConfigurationModel;
import net.xconfig.bungee.utils.TextUtils;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Implementation to handle the values from the configuration at BungeeCord platforms.
 *
 * @author InitSync
 * @version 1.1.2
 * @since 1.0.1
 */
public final class BungeeConfigurationHandlerImpl implements BungeeConfigurationHandler {
	private final BungeeConfigurationModel configuration;
	private final Logger logger;
	
	public BungeeConfigurationHandlerImpl(BungeeConfigurationModel configuration, Logger logger) {
		this.configuration = Objects.requireNonNull(configuration, "The ConfigurationModel is null.");
		this.logger = Objects.requireNonNull(logger, "The Logger object is null.");
	}
	
	/**
	 * Set an object inside of file at the specified path.
	 *
	 * @param fileName Name of file.
	 * @param toPath Path for the value.
	 * @param object Object/Value to set.
	 */
	@Override
	public void write(String fileName, String toPath, Object object) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!toPath.isEmpty(), "The path for the value is empty.");
		
		configuration.file(fileName).set(toPath, object);
	}
	
	/**
	 * Returns a String from path requested.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A string.
	 */
	@Override
	public String text(String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		String text = configuration.file(fileName).getString(path);
		if (text == null) {
			logger.severe("Cannot get the text from the path of file '" + fileName + "' because doesn't exist.");
			return null;
		}
		
		return TextUtils.colorize(text);
	}
	
	/**
	 * Returns a String from path requested that can return a default value.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultText Default text if the path not exist.
	 * @return A text string.
	 */
	@Override
	public String text(String fileName, String path, String defaultText) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return TextUtils.colorize(configuration.file(fileName).getString(path, defaultText));
	}
	
	/**
	 * Returns a number.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A number
	 */
	@Override
	public int number(String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(fileName).getInt(path);
	}
	
	/**
	 * Returns an int number or a default value.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultNumber Default number if the path not exist.
	 * @return A number
	 */
	@Override
	public int number(String fileName, String path, int defaultNumber) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(fileName).getInt(path, defaultNumber);
	}
	
	/**
	 * Returns an object from the path.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return An object.
	 */
	@Override
	public Object any(String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		Object object = configuration.file(fileName).get(path);
		if (object == null) {
			logger.severe("Cannot get the object from the path of file '" + fileName + "' because doesn't exist.");
			return null;
		}
		
		return object;
	}
	
	/**
	 * Returns an object from the path or a default value.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultObject Default object if the path not exist.
	 * @return An object.
	 */
	@Override
	public Object any(String fileName, String path, Object defaultObject) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(fileName).get(path, defaultObject);
	}
	
	/**
	 * Returns a list.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A list.
	 */
	@Override
	public List<?> list(String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(fileName).getList(path);
	}
	
	/**
	 * Returns a list from the path requested, or a default value if doesn't exist.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultList Default list if the path not exist.
	 * @return A list.
	 */
	@Override
	public List<?> list(String fileName, String path, List<?> defaultList) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(fileName).getList(path, defaultList);
	}
	
	/**
	 * Returns a text list.
	 *
	 * @param fileName        Name of file.
	 * @param path            Path required.
	 * @return A string list.
	 */
	@Override
	public List<String> textList(String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(fileName).getStringList(path);
	}
	
	/**
	 * Returns a boolean.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A boolean value.
	 */
	@Override
	public boolean condition(String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(fileName).getBoolean(path);
	}
	
	/**
	 * Returns a boolean or a default value if the path doesn't exist.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultBoolean Default boolean value if the path not exist.
	 * @return A boolean value.
	 */
	@Override
	public boolean condition(String fileName, String path, boolean defaultBoolean) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(fileName).getBoolean(path, defaultBoolean);
	}
	
	/**
	 * Returns a boolean value depending if the file contains that path.
	 *
	 * @param fileName Name of file.
	 * @param path     Path required.
	 * @return A boolean value.
	 */
	@Override
	public boolean contains(String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(fileName).contains(path);
	}
	
	/**
	 * Returns a double number.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A double.
	 */
	@Override
	public double doubleNumber(String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(fileName).getDouble(path);
	}
	
	/**
	 * Returns a double number or a default value if the path doesn't exist.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultDoubleNumber Default double value if the path not exist.
	 * @return A double.
	 */
	@Override
	public double doubleNumber(String fileName, String path, double defaultDoubleNumber) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(fileName).getDouble(path, defaultDoubleNumber);
	}
	
	/**
	 * Returns a Configuration object.
	 *
	 * @param fileName Name of file.
	 * @param path     Path required.
	 * @return A Configuration
	 */
	@Override
	public Configuration section(String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(fileName).getSection(path);
	}
	
	/**
	 * Returns a char from the file.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A char
	 */
	@Override
	public char character(String fileName, String path) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(fileName).getChar(path);
	}
	
	/**
	 * Returns a char from the path or a default value if the path doesn't exist.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultChar Default value to return.
	 * @return A char
	 */
	@Override
	public char character(String fileName, String path, char defaultChar) {
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name is empty.");
		Preconditions.checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(fileName).getChar(path, defaultChar);
	}
}
