package net.xconfig.bungee.impls;

import net.md_5.bungee.config.Configuration;
import net.xconfig.bungee.config.BungeeConfigurationHandler;
import net.xconfig.bungee.config.BungeeConfigurationModel;
import net.xconfig.bungee.utils.TextUtils;
import net.xconfig.enums.Action;
import org.apache.commons.lang.Validate;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Implementation to handle the values from the configuration at BungeeCord platforms.
 *
 * @author InitSync
 * @version 1.0.7
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
	 * Make some action with the files. Reload, Save or Write a new value.
	 *
	 * @param fileName Name of file.
	 * @param action   Action type.
	 * @param toPath   Path for the value.
	 * @param object   Object/Value to set.
	 */
	@Override
	public void doSomething(String fileName, Action action, String toPath, Object object) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Objects.requireNonNull(action, "The action type is null.");
		
		switch (action) {
			case RELOAD:
				this.configuration.load(fileName);
				break;
			case SAVE:
				this.configuration.save(fileName);
				break;
			case WRITE:
				if (toPath == null) {
					this.logger.severe("The path requested is null.");
					break;
				}
				
				if (object == null) {
					this.logger.severe("The object to set is null.");
					break;
				}
				
				this.configuration
					.file(fileName)
					.set(toPath, object);
				break;
		}
	}
	
	/**
	 * Returns a String from path requested.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultText Default text if the path not exist.
	 * @return A text string.
	 */
	@Override
	public String text(String fileName, String path, String defaultText) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return TextUtils.colorize(this.configuration
			.file(fileName)
			.getString(path, defaultText));
	}
	
	/**
	 * Returns a integer.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultNumber Default number if the path not exist.
	 * @return A number
	 */
	@Override
	public int number(String fileName, String path, int defaultNumber) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			.file(fileName)
			.getInt(path, defaultNumber);
	}
	
	/**
	 * Returns an object from the path.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultObject Default object if the path not exist.
	 * @return An object.
	 */
	@Override
	public Object any(String fileName, String path, Object defaultObject) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			.file(fileName)
			.get(path, defaultObject);
	}
	
	/**
	 * Returns a list.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultList Default list if the path not exist.
	 * @return A list.
	 */
	@Override
	public List<?> list(String fileName, String path, List<?> defaultList) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			.file(fileName)
			.getList(path, defaultList);
	}
	
	/**
	 * Returns a text list.
	 *
	 * @param fileName Name of file.
	 * @param path     Path required.
	 * @return A string list.
	 */
	@Override
	public List<String> textList(String fileName, String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return TextUtils.colorize(this.configuration
			.file(fileName)
			.getStringList(path));
	}
	
	/**
	 * Returns a boolean.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultBoolean Default boolean value if the path not exist.
	 * @return A boolean value.
	 */
	@Override
	public boolean condition(String fileName, String path, boolean defaultBoolean) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			.file(fileName)
			.getBoolean(path, defaultBoolean);
	}
	
	/**
	 * Checks if the file contains the path.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A boolean
	 */
	@Override
	public boolean contains(String fileName, String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			.file(fileName)
			.contains(path);
	}
	
	/**
	 * Returns a double value.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultDoubleNumber Default double value if the path not exist.
	 * @return A double.
	 */
	@Override
	public double doubleNumber(String fileName, String path, double defaultDoubleNumber) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			.file(fileName)
			.getDouble(path, defaultDoubleNumber);
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
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			.file(fileName)
			.getSection(path);
	}
	
	/**
	 * Returns a char from the file.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultChar Default char value if the path not exist.
	 * @return A char
	 */
	@Override
	public char character(String fileName, String path, char defaultChar) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			.file(fileName)
			.getChar(path, defaultChar);
	}
}
