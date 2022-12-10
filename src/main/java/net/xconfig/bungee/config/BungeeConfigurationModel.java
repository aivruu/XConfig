package net.xconfig.bungee.config;

import net.md_5.bungee.config.Configuration;

/**
 * Configuration Interface for the Bungee implementations.
 *
 * @author InitSync
 * @version 1.1.23
 * @since 1.0.1
 */
public interface BungeeConfigurationModel {
	/**
	 * Creates and loads multiple files.
	 *
	 * @param folderName Name of the folder.
	 * @param files Names of the files.
	 */
	default void build(String folderName, String... files) {
		for (String file : files) {
			build(folderName, file);
		}
	}
	
	/**
	 * Delete one or more files.
	 *
	 * @param folderName Name of the folder.
	 * @param files Names of files to delete.
	 */
	default void delete(String folderName, String... files) {
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
	Configuration file(String folderName, String fileName);
	
	/**
	 * Creates and loads a new file with/without a folder.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void build(String folderName, String fileName);
	
	/**
	 * Delete a file.
	 *
	 * @param fileName Name of file.
	 * @param folderName Name of the folder.
	 */
	void delete(String folderName, String fileName);
	
	/**
	 * Reloads a file.
	 *
	 * @param fileName Name of file.
	 * @param folderName Name of the folder.
	 */
	void reload(String folderName, String fileName);
	
	/**
	 * Saves an existing file.
	 *
	 * @param fileName Name of file.
	 * @param folderName Name of the folder.
	 */
	void save(String folderName, String fileName);
}
