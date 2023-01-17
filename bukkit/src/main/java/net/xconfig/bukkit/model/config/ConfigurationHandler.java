package net.xconfig.bukkit.model.config;

import org.bukkit.configuration.ConfigurationSection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Interface model that handles the configuration handler for Bukkit.
 *
 * @author InitSync
 * @version 1.1.4
 * @since 1.0.0
 */
public interface ConfigurationHandler {
	/**
	 * Set an object inside of file at the specified path.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param toPath Path for the value.
	 * @param object Object/Value to set.
	 */
	void write(String folderName, String fileName, String toPath, Object object);
	
	/**
	 * Returns a String from path requested.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param colorize Do you want to apply the colors to text content?
	 * @return A string.
	 */
	String text(String folderName, String fileName, String path, boolean colorize);
	
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
	String text(String folderName, String fileName, String path, String defaultText, boolean colorize);
	
	/**
	 * Returns a number.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A number
	 */
	int number(String folderName, String fileName, String path);
	
	/**
	 * Returns an int number or a default value.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultNumber Default number if the path not exist.
	 * @return A number
	 */
	int number(String folderName, String fileName, String path, int defaultNumber);
	
	/**
	 * Returns an object from the path.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return An object.
	 */
	Object any(String folderName, String fileName, String path);
	
	/**
	 * Returns an object from the path or a default value.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultObject Default object if the path not exist.
	 * @return An object.
	 */
	Object any(String folderName, String fileName, String path, Object defaultObject);
	
	/**
	 * Returns a list.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A list.
	 */
	List<?> list(String folderName, String fileName, String path);
	
	/**
	 * Returns a list from the path requested, or a default value if doesn't exist.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultList Default list if the path not exist.
	 * @return A list.
	 */
	List<?> list(String folderName, String fileName, String path, List<?> defaultList);
	
	/**
	 * Returns a text list.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param colorize Do you want to apply the colors to text content?
	 * @return A string list.
	 */
	List<String> textList(String folderName, String fileName, String path, boolean colorize);
	
	/**
	 * Returns a boolean.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A boolean value.
	 */
	boolean condition(String folderName, String fileName, String path);
	
	/**
	 * Returns a boolean or a default value if the path doesn't exist.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultBoolean Default boolean value if the path not exist.
	 * @return A boolean value.
	 */
	boolean condition(String folderName, String fileName, String path, boolean defaultBoolean);
	
	/**
	 * Checks if the file contains the path.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A boolean
	 */
	boolean contains(String folderName, String fileName, String path);
	
	/**
	 * Returns a double number.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A double.
	 */
	double doubleNumber(String folderName, String fileName, String path);
	
	/**
	 * Returns a double number or a default value if the path doesn't exist.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultDoubleNumber Default double value if the path not exist.
	 * @return A double.
	 */
	double doubleNumber(String folderName, String fileName, String path, double defaultDoubleNumber);
	
	/**
	 * Returns a ConfigurationSection object.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A ConfigurationSection
	 */
	ConfigurationSection configSection(String folderName, String fileName, String path);
}
