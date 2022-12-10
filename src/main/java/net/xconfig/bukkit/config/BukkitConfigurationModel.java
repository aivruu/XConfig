package net.xconfig.bukkit.config;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Interface model for the Bukkit Configuration Manager.
 *
 * @author InitSync
 * @version 1.1.23
 * @since 1.0.0
 */
public interface BukkitConfigurationModel {
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
	 * Returns a FileConfiguration object using the file specified.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @return A FileConfiguration.
	 */
	FileConfiguration file(String folderName, String fileName);
	
	/**
	 * Creates and load a new file with/without a folder.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void build(String folderName, String fileName);
	
	/**
	 * Delete a file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void delete(String folderName, String fileName);
	
	/**
	 * Reloads a file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void reload(String folderName, String fileName);
	
	/**
	 * Saves an existing file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void save(String folderName, String fileName);
}
