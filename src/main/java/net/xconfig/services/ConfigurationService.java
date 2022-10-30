package net.xconfig.services;

import net.xconfig.config.ConfigurationModel;
import net.xconfig.impls.BukkitConfigurationHandlerImpl;
import net.xconfig.impls.BukkitConfigurationModelImpl;
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
	 * Returns a new instance of ManagerModel.
	 *
	 * @param plugin A JavaPlugin instance required for the files creation.
	 * @return A ManagerModel instance.
	 */
	@Contract ("_ -> new")
	static @NotNull BukkitConfigurationModelImpl bukkitManager(@NotNull JavaPlugin plugin) {
		return new BukkitConfigurationModelImpl(plugin);
	}
	
	/**
	 * Returns a new instance of ManagerModel.
	 *
	 * @param model A ConfigurationModel instance.
	 * @return A ManagerModel instance.
	 */
	@Contract (value = "_ -> new", pure = true)
	static @NotNull BukkitConfigurationHandlerImpl bukkitHandler(@NotNull ConfigurationModel model) {
		return new BukkitConfigurationHandlerImpl(model);
	}
}
