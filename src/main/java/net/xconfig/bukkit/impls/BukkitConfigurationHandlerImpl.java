package net.xconfig.bukkit.impls;

import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import net.xconfig.bukkit.config.BukkitConfigurationModel;
import net.xconfig.bungee.utils.TextUtils;
import net.xconfig.enums.Action;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Class to handle the configuration files and get values from that files.
 *
 * @author InitSync
 * @version 1.1.1
 * @since 1.0.0
 * @see net.xconfig.bukkit.config.BukkitConfigurationHandler
 */
public final class BukkitConfigurationHandlerImpl implements BukkitConfigurationHandler {
	private final BukkitConfigurationModel configuration;
	private final Logger logger;
	
	public BukkitConfigurationHandlerImpl(BukkitConfigurationModel configuration, Logger logger) {
		this.configuration = Objects.requireNonNull(configuration, "The BukkitConfigurationModel object is null.");
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
				this.configuration.reload(fileName);
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
	 * @return A string.
	 */
	@Override
	public String text(String fileName, String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		String text = this.configuration
			 .file(fileName)
			 .getString(path);
		if (text == null) {
			this.logger.severe("Cannot get the text from the path of file '" + fileName + "' because doesn't exist.");
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
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return TextUtils.colorize(this.configuration
			 .file(fileName)
			 .getString(path, defaultText));
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
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			 .file(fileName)
			 .getInt(path);
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
	 * @return An object.
	 */
	@Override
	public Object any(String fileName, String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		Object object = this.configuration
			 .file(fileName)
			 .get(path);
		if (object == null) {
			this.logger.severe("Cannot get the object from the path of file '" + fileName + "' because doesn't exist.");
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
	 * @return A list.
	 */
	@Override
	public List<?> list(String fileName, String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			 .file(fileName)
			 .getList(path);
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
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			.file(fileName)
			.getList(path, defaultList);
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
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			.file(fileName)
			.getStringList(path);
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
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			 .file(fileName)
			 .getBoolean(path);
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
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			.file(fileName)
			.getBoolean(path, defaultBoolean);
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
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			.file(fileName)
			.contains(path);
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
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			 .file(fileName)
			 .getDouble(path);
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
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			.file(fileName)
			.getDouble(path, defaultDoubleNumber);
	}
	
	/**
	 * Returns a ConfigurationSection object.
	 *
	 * @param fileName Name of file.
	 * @param path     Path required.
	 * @return A ConfigurationSection
	 */
	@Override
	public ConfigurationSection configSection(String fileName, String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Validate.notEmpty(path, "The path is empty.");
		
		return this.configuration
			.file(fileName)
			.getConfigurationSection(path);
	}
}
