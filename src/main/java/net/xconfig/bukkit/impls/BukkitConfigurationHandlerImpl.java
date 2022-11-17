package net.xconfig.bukkit.impls;

import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import net.xconfig.bukkit.config.BukkitConfigurationModel;
import net.xconfig.bukkit.utils.TextUtils;
import net.xconfig.enums.Action;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Class to handle the configuration files and get values from that files.
 *
 * @author InitSync
 * @version 1.0.5
 * @since 1.0.0
 * @see net.xconfig.bukkit.config.BukkitConfigurationHandler
 */
public final class BukkitConfigurationHandlerImpl implements BukkitConfigurationHandler {
	private final BukkitConfigurationModel configuration;
	private final Logger logger;
	
	public BukkitConfigurationHandlerImpl(@NotNull BukkitConfigurationModel configuration) {
		this.configuration = Objects.requireNonNull(configuration, "The ConfigurationModel object is null.");
		this.logger = Bukkit.getLogger();
	}
	
	/**
	 * Make some action with the files. Reload, Save or Write a new value.
	 *
	 * @param fileName Name of file.
	 * @param action   Action type.
	 * @param toPath   Path for the value.
	 * @param object   Object/Value to set.
	 */
	@Override
	public void doSomething(
		@NotNull String fileName,
		@NotNull Action action,
		@Nullable String toPath,
		@Nullable Object object
	) {
		Validate.notEmpty(fileName, "The file name is empty.");
		Objects.requireNonNull(action, "The action type is null.");
		
		switch (action) {
			case RELOAD:
				this.configuration.reload(fileName);
				break;
			case SAVE:
				this.configuration.save(fileName);
				break;
			case WRITE:
				if (toPath == null) throw new NullPointerException("The path requested is null.");
				if (object == null) throw new NullPointerException("The object to set is null.");
				
				this.configuration
					.file(fileName)
					.set(toPath, object);
				break;
		}
	}
	
	/**
	 * Returns a String from path requested.
	 *
	 * @param fileName Name of file.
	 * @param path     Path required.
	 * @return A text string.
	 */
	@Override
	public @NotNull String text(@NotNull String fileName, @NotNull String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		String text = this.configuration
			.file(fileName)
			.getString(path);
		if (text == null) {
			this.logger.severe("Cannot get the string from the path '" + path + "'. Please check the configuration file.");
			return "";
		}
		
		return TextUtils.colorize(text);
	}
	
	/**
	 * Returns a integer.
	 *
	 * @param fileName Name of file.
	 * @param path     Path required.
	 * @return A number
	 */
	@Override
	public int number(@NotNull String fileName, @NotNull String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		return this.configuration
			.file(fileName)
			.getInt(path);
	}
	
	/**
	 * Returns an object from the path.
	 *
	 * @param fileName Name of file.
	 * @param path     Path required.
	 * @return An object.
	 */
	@Override
	public @Nullable Object any(@NotNull String fileName, @NotNull String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		Object object = this.configuration
			.file(fileName)
			.get(path);
		if (object == null) {
			this.logger.severe("Cannot get the object from '" + path + "'. Please check the path.");
			return null;
		}
		
		return object;
	}
	
	/**
	 * Returns a list.
	 *
	 * @param fileName Name of file.
	 * @param path     Path required.
	 * @return A list.
	 */
	@Override
	public @NotNull List<?> list(@NotNull String fileName, @NotNull String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		List<?> list = this.configuration
			.file(fileName)
			.getList(path);
		if (list == null) {
			this.logger.severe("Cannot found the list requested from '" + path + "'. Check the configuration file.");
			return Collections.emptyList();
		}
		
		return list;
	}
	
	/**
	 * Returns a text list.
	 *
	 * @param fileName Name of file.
	 * @param path     Path required.
	 * @return A string list.
	 */
	@Override
	public @NotNull List<String> textList(@NotNull String fileName, @NotNull String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		return TextUtils.colorize(this.configuration
			.file(fileName)
			.getStringList(path));
	}
	
	/**
	 * Returns a boolean.
	 *
	 * @param fileName of file.
	 * @param path     Path required.
	 * @return A boolean value.
	 */
	@Override
	public boolean condition(@NotNull String fileName, @NotNull String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		return this.configuration
			.file(fileName)
			.getBoolean(path);
	}
	
	/**
	 * Returns a ConfigurationSection object.
	 *
	 * @param fileName Name of file.
	 * @param path     Path required.
	 * @return A ConfigurationSection
	 */
	@Override
	public @Nullable ConfigurationSection configSection(@NotNull String fileName, @NotNull String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		ConfigurationSection section = this.configuration
			.file(fileName)
			.getConfigurationSection(path);
		if (section == null) {
			this.logger.severe("The ConfigurationSection object requested in '" + path + "' is null, please check the configuration file.");
			return null;
		}
		
		return section;
	}
}
