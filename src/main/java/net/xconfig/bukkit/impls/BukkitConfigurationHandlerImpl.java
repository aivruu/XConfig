package net.xconfig.bukkit.impls;

import net.xconfig.bukkit.config.BukkitConfigurationHandler;
import net.xconfig.bukkit.config.BukkitConfigurationModel;
import net.xconfig.enums.Action;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * Class to handle the configuration files and get values from that files.
 *
 * @author InitSync
 * @version 1.0.3
 * @since 1.0.0
 * @see net.xconfig.bukkit.config.BukkitConfigurationHandler
 */
public final class BukkitConfigurationHandlerImpl implements BukkitConfigurationHandler {
	private final BukkitConfigurationModel configuration;
	
	public BukkitConfigurationHandlerImpl(@NotNull BukkitConfigurationModel configuration) {
		this.configuration = Objects.requireNonNull(configuration, "The ConfigurationModel object is null.");
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
	 * Returns a ConfigurationSection object.
	 *
	 * @param fileName Name of file.
	 * @param path     Path required.
	 * @return A ConfigurationSection
	 */
	@Override
	public @Nullable ConfigurationSection configSection(@NotNull String fileName, @NotNull String path) {
		Validate.notEmpty(fileName, "The file name is empty.");
		
		return this.configuration
			.file(fileName)
			.getConfigurationSection(path);
	}
}
