package net.xconfig.services;

import net.xconfig.config.ConfigurationModel;
import net.xconfig.models.HandlerModel;
import net.xconfig.models.ManagerModel;
import org.bukkit.plugin.java.JavaPlugin;
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
	 * Returns a new instance of ManagerModel.
	 *
	 * @param plugin A JavaPlugin instance required for the files creation.
	 * @return A ManagerModel instance.
	 */
	static @NotNull ManagerModel manager(@NotNull JavaPlugin plugin) {
		return new ManagerModel(plugin);
	}
	
	/**
	 * Returns a new instance of ManagerModel.
	 *
	 * @param model A ConfigurationModel instance.
	 * @return A ManagerModel instance.
	 */
	static @NotNull HandlerModel handler(@NotNull ConfigurationModel model) {
		return new HandlerModel(model);
	}
}
