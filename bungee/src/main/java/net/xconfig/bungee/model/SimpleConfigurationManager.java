package net.xconfig.bungee.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.xconfig.bungee.model.config.ConfigurationManager;

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
public final class SimpleConfigurationManager implements ConfigurationManager {
	private final Plugin plugin;
	private final Table<String, String, File> files;
	private final Table<String, String, Configuration> configurations;
	
	public SimpleConfigurationManager(Plugin plugin) {
		this.plugin = Objects.requireNonNull(plugin, "The Plugin instance is null.");
		this.files = HashBasedTable.create();
		this.configurations = HashBasedTable.create();
	}
	
	@Override
	public Configuration file(String folderName, String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!exists(folderName, fileName)) {
			getInstance().getLogger().severe("Cannot get the file " + fileName + " because doesn't exist.");
			return null;
		}
		
		return configurations.get(folderName, fileName);
	}
	
	@Override
	public void build(String folderName, String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		File file;
		
		if (folderName.isEmpty()) file = new File(plugin.getDataFolder(), fileName);
		else file = new File(plugin.getDataFolder() + File.separator + folderName + File.separator, fileName);
		
		if (!file.exists()) {
			InputStream inputFile;
			if (folderName.isEmpty()) inputFile = plugin.getResourceAsStream(fileName);
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
	
	@Override
	public void buildCustom(String folderName, String fileName) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		
		if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdir();
		
		File file;
		
		if (folderName.isEmpty()) {
			file = new File(plugin.getDataFolder(), fileName);
			if (!file.exists()) {
				try { file.createNewFile(); }
				catch (IOException exception) {
					getInstance().getLogger().severe("Cannot create the custom file '" + fileName + "'.");
					exception.printStackTrace();
					return;
				}
			}
		} else {
			new File(plugin.getDataFolder(), folderName).mkdir();
			file = new File(plugin.getDataFolder() + File.separator + folderName + File.separator, fileName);
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
	
	@Override
	public void delete(String folderName, String fileName) {
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
	
	@Override
	public void reload(String folderName, String fileName) {
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
	
	@Override
	public void save(String folderName, String fileName) {
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
	
	@Override
	public boolean exists(String folderName, String fileName) {
		return files.contains(folderName, fileName) && configurations.contains(folderName, fileName);
	}
}
