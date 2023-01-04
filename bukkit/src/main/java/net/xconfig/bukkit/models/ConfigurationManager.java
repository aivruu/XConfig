package net.xconfig.bukkit.models;

import org.bukkit.configuration.file.FileConfiguration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Interface model for the Bukkit Configuration Manager.
 *
 * @author InitSync
 * @version 1.1.4
 * @since 1.0.0
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
	 * Create and loads multiple custom files.
	 *
	 * @param folderName Name of the folder.
	 * @param files Names of the files.
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
	 * Returns a FileConfiguration object using the file specified.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @return A FileConfiguration.
	 */
	@Nullable FileConfiguration file(@Nonnull String folderName, @Nonnull String fileName);
	
	/**
	 * Creates and load a new file with/without a folder.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void build(@Nonnull String folderName, @Nonnull String fileName);
	
	/**
	 * Creates and load a new custom file with/without a folder.
	 * This method allows create files that is not inside of plugin jar file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void buildCustom(@Nonnull String folderName, @Nonnull String fileName);
	
	/**
	 * Delete a file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void delete(@Nonnull String folderName, @Nonnull String fileName);
	
	/**
	 * Reloads a file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	void reload(@Nonnull String folderName, @Nonnull String fileName);
	
	/**
	 * Saves an existing file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
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
