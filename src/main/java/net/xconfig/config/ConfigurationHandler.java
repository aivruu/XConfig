package net.xconfig.config;

import net.xconfig.enums.Action;
import net.xconfig.enums.File;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Interface model that handles the configuration handler.
 *
 * @author InitSync
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ConfigurationHandler {
	/**
	 * Make some action with the files. Reload, Save or Write a new value.
	 *
	 * @param file File type.
	 * @param action Action type.
	 * @param toPath Path for the value.
	 * @param object Object/Value to set.
	 */
	void doSomething(
		 @NotNull File file,
		 @NotNull Action action,
		 @Nullable String toPath,
		 @Nullable Object object,
		 @Nullable String customFileName
	);
	
	/**
	 * Returns a String from path requested.
	 *
	 * @param file File type.
	 * @param path Path required.
	 * @param customFileName Name of the custom file.
	 * @return A text string.
	 */
	@NotNull String text(@NotNull File file, @NotNull String path, @Nullable String customFileName);
	
	/**
	 * Returns a integer.
	 *
	 * @param file File type.
	 * @param path Path required.
	 * @param customFileName Name of the custom file.
	 * @return A number
	 */
	int number(@NotNull File file, @NotNull String path, @Nullable String customFileName);
	
	/**
	 * Returns an object from the path.
	 *
	 * @param file File type.
	 * @param path Path required.
	 * @param customFileName Name of the custom file.
	 * @return An object.
	 */
	@Nullable Object any(@NotNull File file, @NotNull String path, @Nullable String customFileName);
	
	/**
	 * Returns a list.
	 *
	 * @param file File type.
	 * @param path Path required.
	 * @param customFileName Name of the custom file.
	 * @return A list.
	 */
	@NotNull List<?> list(@NotNull File file, @NotNull String path, @Nullable String customFileName);
	
	/**
	 * Returns a text list.
	 *
	 * @param file File type.
	 * @param path Path required.
	 * @param customFileName Name of the custom file.
	 * @return A string list.
	 */
	@NotNull List<String> textList(
		 @NotNull File file,
		 @NotNull String path,
		 @Nullable String customFileName
	);
	
	/**
	 * Returns a ConfigurationSection object.
	 *
	 * @param file File type.
	 * @param path Path required.
	 * @param customFileName Name of the custom file.
	 * @return A ConfigurationSection
	 */
	@Nullable ConfigurationSection configSection(
		 @NotNull File file,
		 @NotNull String path,
		 @Nullable String customFileName
	);
}
