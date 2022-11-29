package net.xconfig.bukkit.config;

import net.xconfig.enums.Action;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

/**
 * Interface model that handles the configuration handler for Bukkit.
 *
 * @author InitSync
 * @version 1.1.0
 * @since 1.0.0
 */
public interface BukkitConfigurationHandler {
	/**
	 * Make some action with the files. Reload, Save or Write a new value.
	 *
	 * @param fileName Name of file.
	 * @param action Action type.
	 * @param toPath Path for the value.
	 * @param object Object/Value to set.
	 */
	void doSomething(String fileName, Action action, String toPath, Object object);
	
	/**
	 * Returns a String from path requested.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultText Default text if the path not exist.
	 * @return A text string.
	 */
	String text(String fileName, String path, String defaultText);
	
	/**
	 * Returns a integer.
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
	 * @param defaultObject Default object if the path not exist.
	 * @return An object.
	 */
	Object any(String fileName, String path, Object defaultObject);
	
	/**
	 * Returns a list.
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
	 * @param defaultBoolean Default boolean value if the path not exist.
	 * @return A boolean value.
	 */
	boolean condition(String fileName, String path, boolean defaultBoolean);
	
	/**
	 * Returns a boolean value depending if the file contains that path.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A boolean value.
	 */
	boolean contains(String fileName, String path);
	
	/**
	 * Returns a double value.
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
