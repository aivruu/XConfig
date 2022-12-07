package net.xconfig.bungee.config;

import net.md_5.bungee.config.Configuration;

import java.util.List;

/**
 * Interface for the ConfigurationHandler of Bungee platforms.
 *
 * @author InitSync
 * @version 1.1.2
 * @since 1.0.1
 */
public interface BungeeConfigurationHandler {
	/**
	 * Set an object inside of file at the specified path.
	 *
	 * @param fileName Name of file.
	 * @param toPath Path for the value.
	 * @param object Object/Value to set.
	 */
	void write(String fileName, String toPath, Object object);
	
	/**
	 * Returns a String from path requested.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A string.
	 */
	String text(String fileName, String path);
	
	/**
	 * Returns a String from path requested that can return a default value.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultText Default text if the path not exist.
	 * @return A text string.
	 */
	String text(String fileName, String path, String defaultText);
	
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
	 * @return A string list.
	 */
	List<String> textList(String fileName, String path);
	
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
	 * Returns a Configuration object.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A Configuration
	 */
	Configuration section(String fileName, String path);
	
	/**
	 * Returns a char from the path.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A char
	 */
	char character(String fileName, String path);
	
	/**
	 * Returns a char from the path or a default value if the path doesn't exist.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultChar Default value to return.
	 * @return A char
	 */
	char character(String fileName, String path, char defaultChar);
}
