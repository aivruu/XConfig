package net.xconfig.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ConfigurationModel {
	/**
	 * Returns a FileConfiguration object using the file specified.
	 *
	 * @param fileName Name of file.
	 * @return A FileConfiguration.
	 */
	@Nullable FileConfiguration get(@NotNull String fileName);
	
	/**
	 * Creates a new file with a folder.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void create(@NotNull String folderName, @NotNull String fileName);
	
	/**
	 * Loads an existing file.
	 *
	 * @param fileName Name of file.
	 */
	void load(@NotNull String fileName);
	
	/**
	 * Reloads a file.
	 *
	 * @param fileName Name of file.
	 */
	void reload(@NotNull String fileName);
	
	/**
	 * Saves an existing file.
	 *
	 * @param fileName Name of file.
	 */
	void save(@NotNull String fileName);
	
	/**
	 * Saves the default content of file.
	 *
	 * @param folderName Name of the folder of file.
	 * @param fileName Name of file.
	 */
	void save(@NotNull String folderName, @NotNull String fileName);
}
