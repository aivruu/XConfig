package net.xconfig.bungee.config;

import net.md_5.bungee.config.Configuration;

/**
 * Configuration Interface for the Bungee implementations.
 *
 * @author InitSync
 * @version 1.0.8
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
	Configuration file(String fileName);
	
	/**
	 * Creates and loads a new file with/without a folder.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void build(String folderName, String fileName);
	
	/**
	 * Creates and loads multiple files.
	 *
	 * @param folderName Name of the folder.
	 * @param files Names of the files.
	 */
	void build(String folderName, String... files);
	
	/**
	 * Delete a file.
	 *
	 * @param fileName Name of file.
	 */
	void delete(String fileName);
	
	/**
	 * Delete one or more files.
	 *
	 * @param files Names of files to delete.
	 */
	void delete(String... files);
	
	/**
	 * Reloads a file.
	 *
	 * @param fileName Name of file.
	 */
	void reload(String fileName);
	
	/**
	 * Saves an existing file.
	 *
	 * @param fileName Name of file.
	 */
	void save(String fileName);
	
	/**
	 * Saves the default content of file.
	 *
	 * @param folderName Name of the folder of file.
	 * @param fileName Name of file.
	 */
	void save(String folderName, String fileName);
}
