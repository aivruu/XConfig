package net.xconfig.services;

import net.xconfig.bukkit.config.BukkitConfigurationModel;
import net.xconfig.bukkit.impls.BukkitConfigurationHandlerImpl;
import net.xconfig.bukkit.impls.BukkitConfigurationModelImpl;
import net.xconfig.bungee.config.BungeeConfigurationModel;
import net.xconfig.bungee.impls.BungeeConfigurationHandlerImpl;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Interface to handle the instances creation.
 *
 * @author InitSync
 * @version 1.0.1
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
	static @NotNull BukkitConfigurationHandlerImpl bukkitHandler(@NotNull BukkitConfigurationModel model) {
		return new BukkitConfigurationHandlerImpl(model);
	}
	
	static BungeeConfigurationHandlerImpl bungeeHandler(@NotNull BungeeConfigurationModel model) {
		return new BungeeConfigurationHandlerImpl(model);
	}
}
