package net.xconfig.bungee.impls;

import net.md_5.bungee.config.Configuration;
import net.xconfig.bungee.config.BungeeConfigurationHandler;
import net.xconfig.bungee.config.BungeeConfigurationModel;
import net.xconfig.enums.Action;
import org.apache.commons.lang.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * Implementation to handle the values from the configuration at BungeeCord platforms.
 *
 * @author InitSync
 * @version 1.0.1
 * @since 1.0.1
 */
public final class BungeeConfigurationHandlerImpl implements BungeeConfigurationHandler {
	private final BungeeConfigurationModel configuration;
	
	public BungeeConfigurationHandlerImpl(@NotNull BungeeConfigurationModel configuration) {
		this.configuration = Objects.requireNonNull(configuration, "The ConfigurationModel is null.");
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
				assert toPath != null && !toPath.isEmpty();
				assert object != null;
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
		
		return this.configuration
			.file(fileName)
			.getString(path);
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
			.file(path)
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
		
		return this.configuration
			.file(fileName)
			.get(path);
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
		
		return this.configuration
			.file(fileName)
			.getList(path);
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
		
		return this.configuration
			.file(fileName)
			.getStringList(path);
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
		
		return this.configuration
			.file(fileName)
			.getSection(path);
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
