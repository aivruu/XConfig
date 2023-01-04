package net.xconfig.bukkit.config;

import net.xconfig.bukkit.models.ConfigurationHandler;
import net.xconfig.bukkit.models.ConfigurationManager;
import net.xconfig.bukkit.utils.TextUtils;
import org.bukkit.configuration.ConfigurationSection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.bukkit.Bukkit.getLogger;

/**
 * Class to handle the configuration files and get values from that files.
 *
 * @author InitSync
 * @version 1.1.4
 * @since 1.0.0
 * @see ConfigurationHandler
 */
public final class SimpleConfigurationHandler
implements ConfigurationHandler {
	private final ConfigurationManager configuration;
	
	public SimpleConfigurationHandler(ConfigurationManager configuration) {
		this.configuration = Objects.requireNonNull(configuration, "The BukkitConfigurationModel object is null.");
	}
	
	/**
	 * Set an object inside of file at the specified path.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param toPath Path for the value.
	 * @param object Object/Value to set.
	 */
	@Override
	public void write(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String toPath, @Nonnull Object object) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!toPath.isEmpty(), "The path for the value is empty.");
		checkNotNull(object, "The object to set can't be null.");
		
		configuration.file(folderName, fileName).set(toPath, object);
	}
	
	/**
	 * Returns a String from path requested.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param colorize Do you want to apply the colors to text content?
	 * @return A string.
	 */
	@Override
	public @Nullable String text(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, boolean colorize) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		String text = configuration.file(folderName, fileName).getString(path);
		if (text == null) {
			getLogger().severe("Cannot get the text from the path of file '" + fileName + "' because doesn't exist.");
			return null;
		}
		
		return colorize ? TextUtils.colorize(text) : text;
	}
	
	/**
	 * Returns a String from path requested that can return a default value.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultText Default text if the path not exist.
	 * @param colorize Do you want to apply the colors to text content?
	 * @return A text string.
	 */
	@Override
	public @Nonnull String text(
		 @Nonnull String folderName,
		 @Nonnull String fileName,
		 @Nonnull String path,
		 @Nonnull String defaultText,
		 boolean colorize) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		String text = configuration.file(folderName, fileName).getString(path, defaultText);
		return colorize ? TextUtils.colorize(text) : text;
	}
	
	/**
	 * Returns a number.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A number
	 */
	@Override
	public int number(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getInt(path);
	}
	
	/**
	 * Returns an int number or a default value.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultNumber Default number if the path not exist.
	 * @return A number
	 */
	@Override
	public int number(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, int defaultNumber) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getInt(path, defaultNumber);
	}
	
	/**
	 * Returns an object from the path.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return An object.
	 */
	@Override
	public @Nullable Object any(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		Object object = configuration.file(folderName, fileName).get(path);
		if (object == null) {
			getLogger().severe("Cannot get the object from the path of file '" + fileName + "' because doesn't exist.");
			return null;
		}
		
		return object;
	}
	
	/**
	 * Returns an object from the path or a default value.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultObject Default object if the path not exist.
	 * @return An object.
	 */
	@Override
	public @Nonnull Object any(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, @Nonnull Object defaultObject) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		checkNotNull(defaultObject, "The default object to return can't be null.");
		
		return configuration.file(folderName, fileName).get(path, defaultObject);
	}
	
	/**
	 * Returns a list.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A list.
	 */
	@Override
	public @Nullable List<?> list(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		List<?> list = configuration.file(folderName, fileName).getList(path);
		if (list == null) {
			getLogger().severe("Cannot get the List object from the path of '" + fileName + "' file because doesn't exist.");
			return null;
		}
		
		return list;
	}
	
	/**
	 * Returns a list from the path requested, or a default value if doesn't exist.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultList Default list if the path not exist.
	 * @return A list.
	 */
	@Override
	public @Nonnull List<?> list(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, @Nonnull List<?> defaultList) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		checkNotNull(defaultList, "The default List object to return can't be null.");
		
		return configuration.file(folderName, fileName).getList(path, defaultList);
	}
	
	/**
	 * Returns a text list.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param colorize Do you want to apply the colors to text content?
	 * @return A string list.
	 */
	@Override
	public @Nonnull List<String> textList(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, boolean colorize) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		List<String> textList = configuration.file(folderName, fileName).getStringList(path);
		return colorize ? TextUtils.colorize(textList) : textList;
	}
	
	/**
	 * Returns a boolean.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A boolean value.
	 */
	@Override
	public boolean condition(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getBoolean(path);
	}
	
	/**
	 * Returns a boolean or a default value if the path doesn't exist.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultBoolean Default boolean value if the path not exist.
	 * @return A boolean value.
	 */
	@Override
	public boolean condition(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, boolean defaultBoolean) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getBoolean(path, defaultBoolean);
	}
	
	/**
	 * Checks if the file contains the path.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A boolean
	 */
	@Override
	public boolean contains(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).contains(path);
	}
	
	/**
	 * Returns a double number.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A double.
	 */
	@Override
	public double doubleNumber(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getDouble(path);
	}
	
	/**
	 * Returns a double number or a default value if the path doesn't exist.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @param defaultDoubleNumber Default double value if the path not exist.
	 * @return A double.
	 */
	@Override
	public double doubleNumber(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path, double defaultDoubleNumber) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		return configuration.file(folderName, fileName).getDouble(path, defaultDoubleNumber);
	}
	
	/**
	 * Returns a ConfigurationSection object.
	 *
	 * @param folderName Name of the folder.
	 * @param fileName Name of file.
	 * @param path Path required.
	 * @return A ConfigurationSection
	 */
	@Override
	public @Nullable ConfigurationSection configSection(@Nonnull String folderName, @Nonnull String fileName, @Nonnull String path) {
		checkArgument(!fileName.isEmpty(), "The file name is empty.");
		checkArgument(!path.isEmpty(), "The path is empty.");
		
		ConfigurationSection section = configuration.file(folderName, fileName).getConfigurationSection(path);
		if (section == null) {
			getLogger().severe("Cannot get the ConfigurationSection object from the path of '" + fileName + "' file because doesn't exist.");
			return null;
		}
		
		return section;
	}
}
