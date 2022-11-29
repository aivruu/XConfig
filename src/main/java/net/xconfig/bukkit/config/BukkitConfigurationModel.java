package net.xconfig.bukkit.config;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Interface model for the Bukkit Configuration Manager.
 *
 * @author InitSync
 * @version 1.1.0
 * @since 1.0.0
 */
public interface BukkitConfigurationModel {
	/**
	 * Returns a FileConfiguration object using the file specified.
	 *
	 * @param fileName Name of file.
	 * @return A FileConfiguration.
	 */
	FileConfiguration file(String fileName);
	
	/**
	 * Creates and load a new file with/without a folder.
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
