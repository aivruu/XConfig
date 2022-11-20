package net.xconfig.bungee.config;

import net.md_5.bungee.config.Configuration;
import net.xconfig.enums.Action;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Interface for the ConfigurationHandler of Bungee platforms.
 *
 * @author InitSync
 * @version 1.0.6
 * @since 1.0.1
 */
public interface BungeeConfigurationHandler {
	/**
	 * Make some action with the files. Reload, Save or Write a new value.
	 *
	 * @param fileName Name of file.
	 * @param action Action type.
	 * @param toPath Path for the value.
	 * @param object Object/Value to set.
	 */
	void doSomething(
		 @NotNull String fileName,
		 @NotNull Action action,
		 @Nullable String toPath,
		 @Nullable Object object
	);
	
	/**
	 * Returns a String from path requested.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A text string.
	 */
	@NotNull String text(@NotNull String fileName, @NotNull String path);
	
	/**
	 * Returns a integer.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A number
	 */
	int number(@NotNull String fileName, @NotNull String path);
	
	/**
	 * Returns an object from the path.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return An object.
	 */
	@Nullable Object any(@NotNull String fileName, @NotNull String path);
	
	/**
	 * Returns a list.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A list.
	 */
	@NotNull List<?> list(@NotNull String fileName, @NotNull String path);
	
	/**
	 * Returns a text list.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A string list.
	 */
	@NotNull List<String> textList(@NotNull String fileName, @NotNull String path);
	
	/**
	 * Returns a boolean.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A boolean value.
	 */
	boolean condition(@NotNull String fileName, @NotNull String path);
	
	/**
	 * Returns a Configuration object.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A Configuration
	 */
	@Nullable Configuration section(@NotNull String fileName, @NotNull String path);
	
	/**
	 * Returns a char from the file.
	 *
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A char
	 */
	char character(@NotNull String fileName, @NotNull String path);
}
