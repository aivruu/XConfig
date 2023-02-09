package net.xconfig.bukkit.model.objects;

import com.google.common.base.Preconditions;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.bukkit.Bukkit.getLogger;

public class YamlFile {
	private static final StringBuilder BUILDER = new StringBuilder();
	
	private final JavaPlugin plugin;
	private final String folderName;
	private final String fileName;
	
	private File file;
	private FileConfiguration configuration;
	
	public YamlFile(JavaPlugin plugin, String folderName, String fileName) {
		this.plugin = Objects.requireNonNull(plugin, "The JavaPlugin instance cannot be null.");
		this.folderName = Objects.requireNonNull(folderName, "The folder name cannot be null.");
		this.fileName = Objects.requireNonNull(fileName, "The file name cannot be null.");
		Preconditions.checkArgument(!fileName.isEmpty(), "The file name cannot be empty.");
	}
	
	/**
	 * Performs the file creation and load it.
	 */
	public void create() {
		if (folderName.isEmpty()) file = new File(plugin.getDataFolder(), fileName);
		else {
			file = new File(BUILDER.append(plugin.getDataFolder())
				 .append(File.separator)
				 .append(folderName)
				 .append(File.separator)
				 .toString(), fileName);
		}
		
		if (!file.exists()) {
			plugin.saveResource(folderName.isEmpty()
				 ? fileName
				 : BUILDER.append(folderName).append(File.separator).append(fileName).toString(), false);
		}
		
		load();
	}
	
	/**
	 * It creates and load as a custom file.
	 */
	public void createAsCustom() {
		final File dataFolder = plugin.getDataFolder();
		if (!dataFolder.exists()) dataFolder.mkdir();
		
		if (folderName.isEmpty()) file = new File(dataFolder, fileName);
		else {
			file = new File(BUILDER.append(dataFolder)
				 .append(File.separator)
				 .append(folderName)
				 .append(File.separator)
				 .toString(), fileName);
		}
		
		if (!file.exists()) {
			try { file.createNewFile(); }
			catch (IOException exception) {
				getLogger().severe("Cannot create the custom file '" + fileName + "'.");
				exception.printStackTrace();
				return;
			}
		}
		
		load();
	}
	
	/**
	 * Load the file with YamlConfiguration.
	 */
	private void load() {
		if (!file.exists()) return;
		
		configuration = YamlConfiguration.loadConfiguration(file);
	}
	
	/**
	 * Returns the folder name.
	 *
	 * @return The folder name, if isn't established will return null.
	 */
	public String getFolderName() {
		return folderName;
	}
	
	/**
	 * Returns the file name.
	 *
	 * @return The file name.
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Returns the File object.
	 *
	 * @return The File object of this file.
	 */
	public File file() {
		return file;
	}
	
	/**
	 * Returns the FileConfiguration object for this file.
	 *
	 * @return The FileConfiguration object.
	 */
	public FileConfiguration get() {
		return configuration;
	}
}
