package net.xconfig.bungee.models;

import net.md_5.bungee.config.Configuration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Configuration Interface for the Bungee implementations.
 *
 * @author InitSync
 * @version 1.1.4
 * @since 1.0.1
 */
public interface ConfigurationManager {
	/**
	 * Creates and loads multiple files.
	 *
	 * @param folderName Name of the folder.
	 * @param files Names of the files.
	 * @see ConfigurationManager#build(String, String)
	 */
	default void build(@Nonnull String folderName, @Nonnull String... files) {
		for (String file : files) {
			build(folderName, file);
		}
	}
	
	/**
	 * Creates and loads multiple custom files.
	 *
	 * @param folderName Name of the folder.
	 * @param files Name of the files.
	 * @see ConfigurationManager#buildCustom(String, String)
	 */
	default void buildCustom(@Nonnull String folderName, @Nonnull String... files) {
		for (String file : files) {
			buildCustom(folderName, file);
		}
	}
	
	/**
	 * Delete one or more files.
	 *
	 * @param folderName Name of the folder.
	 * @param files Names of files to delete.
	 * @see ConfigurationManager#delete(String, String)
	 */
	default void delete(@Nonnull String folderName, @Nonnull String... files) {
		for (String file : files) {
			delete(folderName, file);
		}
	}
	
	/**
	 * Returns a Configuration object using the file name and their folder name (if there are a folder), if the file doesn't exist, will be
	 * return null.
	 *
	 * @param fileName Name of file.
	 * @param folderName Name of the folder.
	 * @return A Configuration object.
	 */
	@Nullable Configuration file(@Nonnull String folderName, @Nonnull String fileName);
	
	/**
	 * Creates and loads a new file with/without a folder.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void build(@Nonnull String folderName, @Nonnull String fileName);
	
	/**
	 * Creates an loads a new file custom with/without a folder.
	 * This method allows create files that is not inside of plugin jar file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void buildCustom(@Nonnull String folderName, @Nonnull String fileName);
	
	/**
	 * Delete a file.
	 *
	 * @param fileName Name of file.
	 * @param folderName Name of the folder.
	 */
	void delete(@Nonnull String folderName, @Nonnull String fileName);
	
	/**
	 * Reloads a file.
	 *
	 * @param fileName Name of file.
	 * @param folderName Name of the folder.
	 */
	void reload(@Nonnull String folderName, @Nonnull String fileName);
	
	/**
	 * Saves an existing file.
	 *
	 * @param fileName Name of file.
	 * @param folderName Name of the folder.
	 */
	void save(@Nonnull String folderName, @Nonnull String fileName);
	
	/**
	 * Returns true if the file specified exists, overwise return false.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @return A boolean value.
	 */
	boolean exists(@Nonnull String folderName, @Nonnull String fileName);
}
