package net.xconfig.bungee.impls;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.xconfig.bungee.config.BungeeConfigurationModel;
import org.apache.commons.lang.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class BungeeConfigurationModelImpl implements BungeeConfigurationModel {
	private final Plugin plugin;
	private final Map<String, File> files;
	private final Map<String, Configuration> configurations;
	
	public BungeeConfigurationModelImpl(@NotNull Plugin plugin) {
		this.plugin = Objects.requireNonNull(plugin, "The Plugin instance is null.");
		this.files = new HashMap<>();
		this.configurations = new HashMap<>();
	}
	
	/**
	 * Returns a Configuration object using the file name, if the file doesn't exist, will be
	 * return null.
	 *
	 * @param fileName Name of file.
	 * @return A Configuration object.
	 */
	@Override
	public @Nullable Configuration file(@NotNull String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (!this.files.containsKey(fileName) && !this.configurations.containsKey(fileName)) {
			this.plugin
				 .getLogger()
				 .severe("Cannot get the file " + fileName + " because doesn't exist.");
			return null;
		}
		
		return this.configurations.get(fileName);
	}
	
	/**
	 * Creates a new file with a folder.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName   Name of file.
	 */
	@Override
	public void create(@NotNull String folderName, @NotNull String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (!this.files.containsKey(fileName) && !this.configurations.containsKey(fileName)) {
			try {
				
			}
		}
	}
	
	/**
	 * Loads an existing file.
	 *
	 * @param fileName Name of file.
	 */
	@Override
	public void load(@NotNull String fileName) {
	
	}
	
	/**
	 * Reloads a file.
	 *
	 * @param fileName Name of file.
	 */
	@Override
	public void reload(@NotNull String fileName) {
	
	}
	
	/**
	 * Saves an existing file.
	 *
	 * @param fileName Name of file.
	 */
	@Override
	public void save(@NotNull String fileName) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		if (this.fileMap.containsKey(file) && this.configurationMap.containsKey(file)) {
			try {
				ConfigurationProvider.getProvider(YamlConfiguration.class)
					 .save(
							this.configurationMap.get(file),
							this.fileMap.get(file)
					 );
			} catch (IOException exception) {
				LogPrinter.error("&b[BetterAquatic] ",
					 "&cThe API has trowed an error.",
					 "&cCannot save the file: '" + file + "'."
				);
				
				exception.printStackTrace();
			}
			return;
		}
	}
	
	@Override
	public void save(@NotNull String folderName, @NotNull String fileName) {}
	
	@Override
	public void create(@NotNull String folder, @NotNull String file) {
		Validate.notEmpty(file, "The file name is empty.");
		
		if (!this.fileMap.containsKey(file) && !this.configurationMap.containsKey(file)) {
			File ioFile;
			InputStream stream = null;
			
			try {
				if (folder.isEmpty()) {
					ioFile = new File(this.plugin.getDataFolder(), file);
					if (!ioFile.exists()) {
						stream = this.plugin
							 .getClass()
							 .getClassLoader()
							 .getResourceAsStream(file);
					}
				} else {
					ioFile = new File(
						 this.plugin.getDataFolder()
								+ File.separator
								+ folder
								+ File.separator,
						 file
					);
					if (!ioFile.exists()) {
						stream = this.plugin.getClass()
							 .getClassLoader()
							 .getResourceAsStream(this.plugin.getDataFolder()
									+ File.separator
									+ folder
									+ File.separator
									+ folder + file
							 );
					}
				}
				
				if (stream == null) {
					LogPrinter.error("&b[BetterAquatic] ",
						 "The configuration file isn't in the plugin jar file."
					);
					return;
				}
				
				Files.copy(stream, ioFile.toPath());
				
				this.fileMap.put(file, ioFile);
				this.configurationMap.put(file, ConfigurationProvider.getProvider(YamlConfiguration.class).load(ioFile));
			} catch (Exception ignored) {}
		}
	}
	
	@Override
	public void load(@NotNull String file) {
		Validate.notEmpty(file, "The file name is empty.");
		
		if (!this.fileMap.containsKey(file) && !this.configurationMap.containsKey(file)) {
			LogPrinter.error("&b[BetterAquatic] ", "&cThe file: '" + file + "' to load not exists.");
			return;
		}
		
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class)
				 .load(this.fileMap.get(file));
		} catch (IOException exception) {
			LogPrinter.error("&b[BetterAquatic] ",
				 "&cThe API has trowed an error.",
				 "&cCannot load the file: '" + file + "'."
			);
			
			exception.printStackTrace();
		}
	}
	
	@Override
	public void reload(@NotNull String file) {
		if (this.fileMap.containsKey(file) && this.configurationMap.containsKey(file)) {
			try {
				ConfigurationProvider.getProvider(YamlConfiguration.class)
					 .load(this.fileMap.get(file));
			} catch (IOException exception) {
				LogPrinter.error("&b[BetterAquatic] ",
					 "&cThe API has trowed an error.",
					 "&cCannot reload the file: '" + file + "'."
				);
				
				exception.printStackTrace();
			}
			return;
		}
		
		LogPrinter.error("&b[BetterAquatic] ", "&cThe file: &e'" + file + "' doesn't exist.");
	}
	
	@Override
	public void save(@NotNull String file) {
		if (this.fileMap.containsKey(file) && this.configurationMap.containsKey(file)) {
			try {
				ConfigurationProvider.getProvider(YamlConfiguration.class)
					 .save(
							this.configurationMap.get(file),
							this.fileMap.get(file)
					 );
			} catch (IOException exception) {
				LogPrinter.error("&b[BetterAquatic] ",
					 "&cThe API has trowed an error.",
					 "&cCannot save the file: '" + file + "'."
				);
				
				exception.printStackTrace();
			}
			return;
		}
		
		LogPrinter.error("&b[BetterAquatic] ", "&cThe file: &e'" + file + "' doesn't exist.");
	}
}
