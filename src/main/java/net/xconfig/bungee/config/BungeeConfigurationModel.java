package net.xconfig.bungee.config;

import net.md_5.bungee.config.Configuration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Configuration Interface for the Bungee implementations.
 *
 * @author InitSync
 * @version 1.0.1
 * @since 1.0.1
 */
public interface BungeeConfigurationModel {
	/**
	 * Returns a Configuration object using the file name, if the file doesn't exist, will be
	 * return null.
	 *
	 * @param fileName Name of file.
	 * @return A Configuration object.
	 */
	@Nullable Configuration file(@NotNull String fileName);
	
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
