package net.xconfig.bungee.model.config;

import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration Interface for the Bungee implementations.
 *
 * @author InitSync
 * @version 1.1.5
 * @since 1.0.1
 */
public interface ConfigurationManager {
	/**
	 * Returns multiple Configuration objects at a list.
	 *
	 * @param files Files to get.
	 * @return A List with the Configuration objects requested.
	 * @see ConfigurationManager#get(String)
	 */
	default List<Configuration> get(String... files) {
		final List<Configuration> cachedConfigurationFiles = new ArrayList<>();
		
		for (String fileRequested : files) {
			cachedConfigurationFiles.add(get(fileRequested));
		}
		
		return cachedConfigurationFiles;
	}
	
	/**
	 * Creates and loads multiple files or custom files.
	 *
	 * @param folderName Name of the folder.
	 * @param custom The files to create will be custom?
	 * @param files Names of the files.
	 * @see ConfigurationManager#build(String, boolean, String...)
	 */
	default void build(String folderName, boolean custom, String... files) {
		for (String file : files) {
			build(folderName, file, custom);
		}
	}
	
	/**
	 * Delete one or more files.
	 *
	 * @param files Files to delete.
	 * @see ConfigurationManager#delete(String)
	 */
	default void delete(String... files) {
		for (String file : files) {
			delete(file);
		}
	}
	
	/**
	 * Reload one or more configuration files.
	 *
	 * @param files Files to reload.
	 * @see ConfigurationManager#reload(String)
	 */
	default void reload(String... files) {
		for (String file : files) {
			reload(file);
		}
	}
	
	/**
	 * Saves multiple configuration files.
	 *
	 * @param files Files to save.
	 * @see ConfigurationManager#save(String)
	 */
	default void save(String... files) {
		for (String file : files) {
			save(file);
		}
	}
	
	/**
	 * Returns a Configuration object using the file specified.
	 *
	 * @param fileName Name of file.
	 * @return A FileConfiguration object for that file.
	 */
	Configuration get(String fileName);
	
	/**
	 * Creates and load a custom/normal file with/without a folder.
	 * <p>
	 * This method allows creates files that is not inside of plugin jar file or make normal configuration files..
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void build(String folderName, String fileName, boolean custom);
	
	/**
	 * Delete a file.
	 *
	 * @param fileName Name of file.
	 */
	void delete(String fileName);
	
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
}
