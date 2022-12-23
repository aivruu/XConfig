package net.xconfig.bukkit.config;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Interface model for the Bukkit Configuration Manager.
 *
 * @author InitSync
 * @version 1.1.26
 * @since 1.0.0
 */
public interface BukkitConfigurationModel {
	/**
	 * Creates and loads multiple files.
	 *
	 * @param folderName Name of the folder.
	 * @param files Names of the files.
	 * @see BukkitConfigurationModel#build(String, String)
	 */
	default void build(String folderName, String... files) {
		for (String file : files) {
			build(folderName, file);
		}
	}
	
	/**
	 * Create and loads multiple custom files.
	 *
	 * @param folderName Name of the folder.
	 * @param files Names of the files.
	 * @see BukkitConfigurationModel#buildCustom(String, String)
	 */
	default void buildCustom(String folderName, String... files) {
		for (String file : files) {
			buildCustom(folderName, file);
		}
	}
	
	/**
	 * Delete one or more files.
	 *
	 * @param folderName Name of the folder.
	 * @param files Names of files to delete.
	 * @see BukkitConfigurationModel#delete(String, String)
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
	 * Creates and load a new custom file with/without a folder.
	 * This method allows create files that is not inside of plugin jar file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void buildCustom(String folderName, String fileName);
	
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
	
	/**
	 * Returns true if the file specified exists, overwise return false.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @return A boolean value.
	 */
	boolean isCreated(String folderName, String fileName);
}
