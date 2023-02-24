package net.xconfig.bukkit.model.config;

import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

/**
 * Interface model that handles the configuration handler for Bukkit.
 *
 * @author InitSync
 * @version 1.1.7
 * @since 1.0.0
 */
public interface ConfigurationHandler {
	/**
	 * Set an object inside of file at the specified path.
	 *
	 * @param fileName Name of file.
	 * @param path Path for the value.
	 * @param value Value to set.
	 */
	void write(String fileName, String path, Object value);
	
	/**
	 * Returns a String from path requested.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param colorize Do you want to apply the colors to text content?
	 * @return A string.
	 */
	String text(String fileName, String path, boolean colorize);
	
	/**
	 * Returns a String from path requested that can return a default value.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultText Default text if the path not exist.
	 * @param colorize Do you want to apply the colors to text content?
	 * @return A text string.
	 */
	String text(String fileName, String path, String defaultText, boolean colorize);
	
	/**
	 * Returns a number.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A number
	 */
	int number(String fileName, String path);
	
	/**
	 * Returns an int number or a default value.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultNumber Default number if the path not exist.
	 * @return A number
	 */
	int number(String fileName, String path, int defaultNumber);
	
	/**
	 * Returns an object from the path.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return An object.
	 */
	Object any(String fileName, String path);
	
	/**
	 * Returns an object from the path or a default value.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultObject Default object if the path not exist.
	 * @return An object.
	 */
	Object any(String fileName, String path, Object defaultObject);
	
	/**
	 * Returns a list.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A list.
	 */
	List<?> list(String fileName, String path);
	
	/**
	 * Returns a list from the path requested, or a default value if doesn't exist.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultList Default list if the path not exist.
	 * @return A list.
	 */
	List<?> list(String fileName, String path, List<?> defaultList);
	
	/**
	 * Returns a text list.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param colorize Do you want to apply the colors to text content?
	 * @return A string list.
	 */
	List<String> textList(String fileName, String path, boolean colorize);
	
	/**
	 * Returns a boolean.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A boolean value.
	 */
	boolean condition(String fileName, String path);
	
	/**
	 * Returns a boolean or a default value if the path doesn't exist.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultBoolean Default boolean value if the path not exist.
	 * @return A boolean value.
	 */
	boolean condition(String fileName, String path, boolean defaultBoolean);
	
	/**
	 * Checks if the file contains the path.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A boolean
	 */
	boolean contains(String fileName, String path);
	
	/**
	 * Returns a double number.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A double.
	 */
	double doubleNumber(String fileName, String path);
	
	/**
	 * Returns a double number or a default value if the path doesn't exist.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultDoubleNumber Default double value if the path not exist.
	 * @return A double.
	 */
	double doubleNumber(String fileName, String path, double defaultDoubleNumber);
	
	/**
	 * Returns a ConfigurationSection object.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A ConfigurationSection
	 */
	ConfigurationSection configSection(String fileName, String path);
}
