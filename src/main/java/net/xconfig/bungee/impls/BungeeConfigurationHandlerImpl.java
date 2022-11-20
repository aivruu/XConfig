package net.xconfig.bungee.impls;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.config.Configuration;
import net.xconfig.bungee.config.BungeeConfigurationHandler;
import net.xconfig.bungee.config.BungeeConfigurationModel;
import net.xconfig.bungee.utils.TextUtils;
import net.xconfig.enums.Action;
import org.apache.commons.lang.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Implementation to handle the values from the configuration at BungeeCord platforms.
 *
 * @author InitSync
 * @version 1.0.6
 * @since 1.0.1
 */
public final class BungeeConfigurationHandlerImpl implements BungeeConfigurationHandler {
	private final BungeeConfigurationModel configuration;
	private final Logger logger;
	
	public BungeeConfigurationHandlerImpl(@NotNull BungeeConfigurationModel configuration) {
		this.configuration = Objects.requireNonNull(configuration, "The ConfigurationModel is null.");
		this.logger = ProxyServer.getInstance().getLogger();
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
				this.configuration.load(fileName);
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
			this.logger.info("Cannot get the string from the path '" + path + "'. Please check the configuration file.");
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
			this.logger.info("Cannot get the object from '" + path + "'. Please check the path.");
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
			this.logger.info("Cannot found the list requested from '" + path + "'. Check the configuration file.");
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
	 * Returns a Configuration object.
	 *
	 * @param fileName Name of file.
	 * @param path     Path required.
	 * @return A Configuration
	 */
	@Override
	public @Nullable Configuration section(@NotNull String fileName, @NotNull String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		Configuration section = this.configuration
			.file(fileName)
			.getSection(path);
		if (section == null) {
			this.logger.severe("The Configuration object from the path '" + path + "' is null.");
			return null;
		}
		
		return section;
	}
	
	/**
	 * Returns a char from the file.
	 *
	 * @param fileName Name of file.
	 * @param path     Path required.
	 * @return A char
	 */
	@Override
	public char character(@NotNull String fileName, @NotNull String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		return this.configuration
			.file(fileName)
			.getChar(path);
	}
}
