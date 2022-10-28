package net.xconfig.models;

import net.xconfig.config.ConfigurationHandler;
import net.xconfig.config.ConfigurationModel;
import net.xconfig.enums.Action;
import net.xconfig.enums.File;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class HandlerModel implements ConfigurationHandler {
	private final ConfigurationModel configuration;
	
	public HandlerModel(@NotNull ConfigurationModel configuration) {
		this.configuration = Objects.requireNonNull(configuration, "The ConfigurationModel object is null.");
	}
	
	/**
	 * Make some action with the files. Reload, Save or Write a new value.
	 *
	 * @param file   File type.
	 * @param action Action type.
	 * @param toPath Path for the value.
	 * @param object Object/Value to set.
	 */
	@Override
	public void doSomething(
		 @NotNull File file,
		 @NotNull Action action,
		 @Nullable String toPath,
		 @Nullable Object object,
		 @Nullable String customFileName
	) {
		Objects.requireNonNull(file, "The file type is null.");
		Objects.requireNonNull(action, "The action type is null.");
		
		switch (file) {
			case CONFIGURATION -> {
				switch (action) {
					case RELOAD -> this.configuration.reload("config.yml");
					case SAVE -> this.configuration.save("config.yml");
					case WRITE -> {
						assert toPath != null;
						this.configuration
							 .file("config.yml")
							 .set(toPath, object);
					}
				}
			}
			case CUSTOM -> {
				assert customFileName != null && !customFileName.isEmpty();
				switch (action) {
					case RELOAD -> this.configuration.reload(customFileName);
					case SAVE -> this.configuration.save(customFileName);
					case WRITE -> {
						assert toPath != null;
						this.configuration
							 .file(customFileName)
							 .set(toPath, object);
					}
				}
			}
		}
	}
	
	/**
	 * Returns a String from path requested.
	 *
	 * @param file           File type.
	 * @param path           Path required.
	 * @param customFileName Name of the custom file.
	 * @return A text string.
	 */
	@Override
	public @NotNull String text(
		 @NotNull File file,
		 @NotNull String path,
		 @Nullable String customFileName
	) {
		Objects.requireNonNull(file, "The file type is null.");
		Validate.notEmpty(path, "The path to get is empty.");
		
		switch (file) {
			case CONFIGURATION -> {
				return this.configuration
					 .file("config.yml")
					 .getString(path);
			}
			case CUSTOM -> {
				assert customFileName != null && !customFileName.isEmpty();
				this.configuration
					 .file(customFileName)
					 .getString(path);
			}
		}
		return "";
	}
	
	/**
	 * Returns a integer.
	 *
	 * @param file           File type.
	 * @param path           Path required.
	 * @param customFileName Name of the custom file.
	 * @return A number
	 */
	@Override
	public int number(
		 @NotNull File file,
		 @NotNull String path,
		 @Nullable String customFileName
	) {
		Objects.requireNonNull(file, "The file type is null.");
		Validate.notEmpty(path, "The path to get is empty.");
		
		switch (file) {
			case CONFIGURATION -> {
				return this.configuration
					 .file("config.yml")
					 .getInt(path);
			}
			case CUSTOM -> {
				assert customFileName != null && !customFileName.isEmpty();
				this.configuration
					 .file(customFileName)
					 .getInt(path);
			}
		}
		return 0;
	}
	
	/**
	 * Returns an object from the path.
	 *
	 * @param file           File type.
	 * @param path           Path required.
	 * @param customFileName Name of the custom file.
	 * @return An object.
	 */
	@Override
	public @Nullable Object any(
		 @NotNull File file,
		 @NotNull String path,
		 @Nullable String customFileName
	) {
		Objects.requireNonNull(file, "The file type is null.");
		Validate.notEmpty(path, "The path to get is empty.");
		
		switch (file) {
			case CONFIGURATION -> {
				return this.configuration
					 .file("config.yml")
					 .get(path);
			}
			case CUSTOM -> {
				assert customFileName != null && !customFileName.isEmpty();
				this.configuration
					 .file(customFileName)
					 .get(path);
			}
		}
		return null;
	}
	
	/**
	 * Returns a list.
	 *
	 * @param file           File type.
	 * @param path           Path required.
	 * @param customFileName Name of the custom file.
	 * @return A list.
	 */
	@Override
	public @NotNull List<?> list(
		 @NotNull File file,
		 @NotNull String path,
		 @Nullable String customFileName
	) {
		Objects.requireNonNull(file, "The file type is null.");
		Validate.notEmpty(path, "The path to get is empty.");
		
		switch (file) {
			case CONFIGURATION -> {
				return this.configuration
					 .file("config.yml")
					 .getList(path);
			}
			case CUSTOM -> {
				assert customFileName != null && !customFileName.isEmpty();
				this.configuration
					 .file(customFileName)
					 .getList(path);
			}
		}
		return Collections.emptyList();
	}
	
	/**
	 * Returns a text list.
	 *
	 * @param file           File type.
	 * @param path           Path required.
	 * @param customFileName Name of the custom file.
	 * @return A string list.
	 */
	@Override
	public @NotNull List<String> textList(
		 @NotNull File file,
		 @NotNull String path,
		 @Nullable String customFileName
	) {
		Objects.requireNonNull(file, "The file type is null.");
		Validate.notEmpty(path, "The path to get is empty.");
		
		switch (file) {
			case CONFIGURATION -> {
				return this.configuration
					 .file("config.yml")
					 .getStringList(path);
			}
			case CUSTOM -> {
				assert customFileName != null && !customFileName.isEmpty();
				this.configuration
					 .file(customFileName)
					 .getStringList(path);
			}
		}
		return Collections.emptyList();
	}
	
	/**
	 * Returns a ConfigurationSection object.
	 *
	 * @param file           File type.
	 * @param path           Path required.
	 * @param customFileName Name of the custom file.
	 * @return A ConfigurationSection
	 */
	@Override
	public @Nullable ConfigurationSection configSection(
		 @NotNull File file,
		 @NotNull String path,
		 @Nullable String customFileName
	) {
		Objects.requireNonNull(file, "The file type is null.");
		Validate.notEmpty(path, "The path to get is empty.");
		
		switch (file) {
			case CONFIGURATION -> {
				return this.configuration
					 .file("config.yml")
					 .getConfigurationSection(path);
			}
			case CUSTOM -> {
				assert customFileName != null && !customFileName.isEmpty();
				this.configuration
					 .file(customFileName)
					 .getConfigurationSection(path);
			}
		}
		return null;
	}
}
