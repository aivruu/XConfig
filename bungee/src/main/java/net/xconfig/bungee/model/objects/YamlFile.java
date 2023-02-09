package net.xconfig.bungee.model.objects;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;

import static net.md_5.bungee.api.ProxyServer.getInstance;

public class YamlFile {
	private static final StringBuilder BUILDER = new StringBuilder();
	
	private final Plugin plugin;
	private final String folderName;
	private final String fileName;
	
	private File file;
	private Configuration configuration;
	
	public YamlFile(Plugin plugin, String folderName, String fileName) {
		this.plugin = Objects.requireNonNull(plugin, "The Plugin instance cannot be null.");
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
			InputStream inputFile;
			if (folderName.isEmpty()) inputFile = plugin.getResourceAsStream(fileName);
			else {
				inputFile = plugin.getResourceAsStream(BUILDER.append(folderName)
					 .append(File.separator)
					 .append(fileName)
					 .toString());
			}
			
			if (inputFile == null) {
				getInstance().getLogger().severe("The resource '" + fileName + "' isn't inside of plugin jar file!");
				return;
			}
			
			try { Files.copy(inputFile, file.toPath()); }
			catch (IOException exception) {
				getInstance().getLogger().severe("Cannot create the file '" + fileName + "'.");
				exception.printStackTrace();
				return;
			}
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
				getInstance().getLogger().severe("Cannot create the custom file '" + fileName + "'.");
				exception.printStackTrace();
				return;
			}
		}
		
		load();
	}
	
	/**
	 * Load the file with YamlConfiguration.
	 */
	public void load() {
		if (!file.exists()) return;
		
		try {
			configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		} catch (IOException exception) {
			getInstance().getLogger().severe("Cannot load the file '" + fileName + "'.");
			exception.printStackTrace();
		}
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
	 * Returns the Configuration object for this file.
	 *
	 * @return The Configuration object.
	 */
	public Configuration get() {
		return configuration;
	}
}
