package net.xconfig.bungee.models;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Interface for the ConfigurationHandler of Bungee platforms.
 *
 * @author InitSync
 * @version 1.1.4
 * @since 1.0.1
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
	void write(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String toPath, @Nonnull Object object);
	
	/**
	 * Returns a String from path requested.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param colorize Do you want to apply the colors to text content?
	 * @return A string or null if the path doesn't exist.
	 */
	@Nullable String text(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, boolean colorize);
	
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
	@Nonnull String text(
		 @Nonnull String folderName,
		 @Nonnull String fileName,
		 @Nonnull String path,
		 @Nonnull String defaultText,
		 boolean colorize
	);
	
	/**
	 * Returns a number.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A number
	 */
	int number(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path);
	
	/**
	 * Returns an int number or a default value.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultNumber Default number if the path not exist.
	 * @return A number
	 */
	int number(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, int defaultNumber);
	
	/**
	 * Returns an object from the path.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return An object or null if the path doesn't exist.
	 */
	@Nullable Object any(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path);
	
	/**
	 * Returns an object from the path or a default value.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultObject Default object if the path not exist.
	 * @return An object.
	 */
	@Nonnull Object any(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, @Nonnull Object defaultObject);
	
	/**
	 * Returns a list.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A List object or null if the path doesn't exist..
	 */
	@Nullable List<?> list(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path);
	
	/**
	 * Returns a list from the path requested, or a default value if doesn't exist.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultList Default list if the path not exist.
	 * @return A List object.
	 */
	@Nonnull List<?> list(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, @Nonnull List<?> defaultList);
	
	/**
	 * Returns a text list.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param colorize Do you want to apply the colors to text content?
	 * @return A string list.
	 */
	@Nonnull List<String> textList(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, boolean colorize);
	
	/**
	 * Returns a boolean.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A boolean value.
	 */
	boolean condition(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path);
	
	/**
	 * Returns a boolean or a default value if the path doesn't exist.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultBoolean Default boolean value if the path not exist.
	 * @return A boolean value.
	 */
	boolean condition(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, boolean defaultBoolean);
	
	/**
	 * Checks if the file contains the path.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A boolean
	 */
	boolean contains(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path);
	
	/**
	 * Returns a double number.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A double.
	 */
	@Nonnull double doubleNumber(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path);
	
	/**
	 * Returns a double number or a default value if the path doesn't exist.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultDoubleNumber Default double value if the path not exist.
	 * @return A double.
	 */
	@Nonnull double doubleNumber(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, double defaultDoubleNumber);
	
	/**
	 * Returns a char from the path.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A char
	 */
	@Nullable char character(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path);
	
	/**
	 * Returns a char from the path or a default value if the path doesn't exist.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultChar Default value to return.
	 * @return A char
	 */
	@Nonnull char character(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, char defaultChar);
}
