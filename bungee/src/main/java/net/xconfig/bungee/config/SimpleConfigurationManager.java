package net.xconfig.bungee.config;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.xconfig.bungee.models.ConfigurationManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static net.md_5.bungee.api.ProxyServer.getInstance;

/**
 * Implementation that manages the creation of the files.
 *
 * @author InitSync
 * @version 1.1.4
 * @since 1.0.1
 * @see ConfigurationManager
 */
public final class SimpleConfigurationManager
implements ConfigurationManager {
	private final Plugin plugin;
	private final Table<String, String, File> files;
	private final Table<String, String, Configuration> configurations;
	
	public SimpleConfigurationManager(Plugin plugin) {
		this.plugin = Objects.requireNonNull(plugin, "The Plugin instance is null.");
		this.files = HashBasedTable.create();
		this.configurations = HashBasedTable.create();
	}
	
	/**
	 * Returns a Configuration object using the file name and their name (if there are a folder), if the file doesn't exist, will be
	 * return null.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @return A Configuration object.
	 */
	@Override
	public @Nullable Configuration file(@Nonnull String folderName, @Nonnull String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!exists(folderName, fileName)) {
			getInstance().getLogger().severe("Cannot get the file " + fileName + " because doesn't exist.");
			return null;
		}
		
		return configurations.get(folderName, fileName);
	}
	
	/**
	 * Creates and loads a new file with a folder.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName   Name of file.
	 */
	@Override
	public void build(@Nonnull String folderName, @Nonnull String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		File dataFolder = plugin.getDataFolder();
		File file;
		
		boolean notFolder = folderName.isEmpty();
		if (notFolder) file = new File(dataFolder, fileName);
		else file = new File(dataFolder + File.separator + folderName + File.separator, fileName);
		
		if (!file.exists()) {
			InputStream inputFile;
			if (notFolder) inputFile = plugin.getResourceAsStream(fileName);
			else inputFile = plugin.getResourceAsStream(folderName + File.separator + file);
			
			if (inputFile == null) {
				getInstance().getLogger().severe("The resource '" + fileName + "' isn't inside of plugin jar file!");
				return;
			}
			
			try { Files.copy(inputFile, file.toPath()); }
			catch (IOException exception) {
				getInstance().getLogger().severe("Cannot create the file '" + fileName + "'.");
				exception.printStackTrace();
			}
		}
		
		files.put(folderName, fileName, file);
		try { configurations.put(fileName, folderName, ConfigurationProvider.getProvider(YamlConfiguration.class).load(file)); }
		catch (IOException exception) {
			getInstance().getLogger().severe("Cannot load the file '" + fileName + "'.");
			exception.printStackTrace();
		}
	}
	
	/**
	 * Creates an loads a new file custom with/without a folder.
	 * This method allows create files that not is inside of plugin jar file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName   Name of file.
	 */
	@Override
	public void buildCustom(@Nonnull String folderName, @Nonnull String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		File dataFolder = plugin.getDataFolder();
		if (!dataFolder.exists()) dataFolder.mkdir();
		
		File file;
		
		if (folderName.isEmpty()) {
			file = new File(dataFolder, fileName);
			if (!file.exists()) {
				try { file.createNewFile(); }
				catch (IOException exception) {
					getInstance().getLogger().severe("Cannot create the custom file '" + fileName + "'.");
					exception.printStackTrace();
					return;
				}
			}
		} else {
			new File(dataFolder, folderName).mkdir();
			file = new File(dataFolder + File.separator + folderName + File.separator, fileName);
			if (!file.exists()) {
				try { file.createNewFile(); }
				catch (IOException exception) {
					getInstance().getLogger().severe("Cannot create the custom file '" + fileName + "'.");
					exception.printStackTrace();
					return;
				}
			}
		}
		
		files.put(folderName, fileName, file);
		try { configurations.put(fileName, folderName, ConfigurationProvider.getProvider(YamlConfiguration.class).load(file)); }
		catch (IOException exception) {
			getInstance().getLogger().severe("Cannot load the file '" + fileName + "'.");
			exception.printStackTrace();
		}
	}
	
	/**
	 * Delete a file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	@Override
	public void delete(@Nonnull String folderName, @Nonnull String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!exists(folderName, fileName)) {
			getInstance().getLogger().severe("Cannot delete the file '" + fileName + "' because doesn't exist.");
			return;
		}
		
		try { files.get(folderName, fileName).delete(); }
		catch (SecurityException exception) {
			getInstance().getLogger().severe("Cannot delete the file '" + fileName + "'.");
			exception.printStackTrace();
		}
		
		files.remove(folderName, fileName);
		configurations.remove(folderName, fileName);
	}
	
	/**
	 * Reloads a file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	@Override
	public void reload(@Nonnull String folderName, @Nonnull String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!exists(folderName, fileName)) {
			getInstance().getLogger().severe("Cannot reload the file '" + fileName + "' because doesn't exist.");
			return;
		}
		
		try { ConfigurationProvider.getProvider(YamlConfiguration.class).load(files.get(folderName, fileName)); }
		catch (IOException exception) {
			getInstance().getLogger().severe("Cannot reload the file '" + fileName + "'.");
			exception.printStackTrace();
		}
	}
	
	/**
	 * Saves an existing file.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 */
	@Override
	public void save(@Nonnull String folderName, @Nonnull String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!exists(folderName, fileName)) {
			getInstance().getLogger().severe("Cannot save the file " + fileName + " because doesn't exist.");
			return;
		}
		
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(
				 configurations.get(folderName, fileName),
				 files.get(folderName, fileName)
			);
		} catch (IOException exception) {
			getInstance().getLogger().severe("Failed to save the file" + fileName + ".");
			exception.printStackTrace();
		}
	}
	
	/**
	 * Returns true if the file specified exists, overwise return false.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName   Name of file.
	 * @return A boolean value.
	 */
	@Override
	public boolean exists(@Nonnull String folderName, @Nonnull String fileName) {
		return files.contains(folderName, fileName) && configurations.contains(folderName, fileName);
	}
}
