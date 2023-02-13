package net.xconfig.bukkit.model.config;

import net.xconfig.bukkit.model.objects.YamlFile;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface model for the Bukkit Configuration Manager.
 *
 * @author InitSync
 * @version 1.1.5
 * @since 1.0.0
 */
public interface ConfigurationManager {
	/**
	 * Returns multiple FileConfiguration objects at a list.
	 *
	 * @param files Files to get.
	 * @return A List with the FileConfiguration objects requested.
	 * @see ConfigurationManager#get(String)
	 */
	default List<FileConfiguration> get(String... files) {
		final List<FileConfiguration> cachedConfigurationFiles = new ArrayList<>();
		
		for (String requestedFile : files) {
			cachedConfigurationFiles.add(get(requestedFile));
		}
		
		return cachedConfigurationFiles;
	}
	
	/**
	 * Returns a list of YamlFile objects requested.
	 *
	 * @param files Name of the files.
	 * @return A List with the YamlFile objects from requested files.
	 */
	default List<YamlFile> getYaml(String... files) {
		final List<YamlFile> cachedYamlFiles = new ArrayList<>();
		
		for (String requestedFile : files) {
			cachedYamlFiles.add(getYaml(requestedFile));
		}
		
		return cachedYamlFiles;
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
	 * Returns a FileConfiguration object using the file specified.
	 *
	 * @param fileName Name of file.
	 * @return A FileConfiguration object for that file.
	 */
	FileConfiguration get(String fileName);
	
	/**
	 * Returns the YamlFile object from file requested.
	 *
	 * @param fileName Name of file.
	 * @return The YamlFile object for these file.
	 */
	YamlFile getYaml(String fileName);
	
	/**
	 * Creates and load a custom/normal file with/without a folder.
	 * <p>
	 * This method allows creates files that is not inside of plugin jar file or make normal configuration files..
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param custom The file to create will be custom?
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
