package net.xconfig.services;

import net.xconfig.config.ConfigurationModel;
import net.xconfig.models.HandlerModel;
import net.xconfig.models.ManagerModel;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Interface to handle the instances creation.
 *
 * @author InitSync
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ConfigurationService {
	/**
	 * Returns a new object of ManagerModel.
	 *
	 * @param plugin A JavaPlugin instance required for the file creation.
	 * @return A ManagerModel instance.
	 */
	@Contract ("_ -> new")
	static @NotNull ManagerModel manager(@NotNull JavaPlugin plugin) {
		return new ManagerModel(plugin);
	}
	
	/**
	 * Returns a new instance of HandlerModel.
	 *
	 * @param model A ConfigurationModel object required to manage the values into the methods class.
	 * @return A HandlerModel object.
	 */
	@Contract (value = "_ -> new", pure = true)
	static @NotNull HandlerModel handler(@NotNull ConfigurationModel model) {
		return new HandlerModel(model);
	}
}
