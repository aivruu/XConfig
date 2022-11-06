package net.xconfig.services;

import net.md_5.bungee.api.plugin.Plugin;
import net.xconfig.bukkit.config.BukkitConfigurationModel;
import net.xconfig.bukkit.impls.BukkitConfigurationHandlerImpl;
import net.xconfig.bukkit.impls.BukkitConfigurationModelImpl;
import net.xconfig.bungee.config.BungeeConfigurationModel;
import net.xconfig.bungee.impls.BungeeConfigurationHandlerImpl;
import net.xconfig.bungee.impls.BungeeConfigurationModelImpl;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Interface to handle the instances creation.
 *
 * @author InitSync
 * @version 1.0.4
 * @since 1.0.0
 */
public interface ConfigurationService {
	/**
	 * Returns a new instance of BukkitConfigurationModelImpl.
	 *
	 * @param plugin A JavaPlugin instance required for the files creation.
	 * @return A ManagerModel instance.
	 */
	@Contract ("_ -> new")
	static @NotNull BukkitConfigurationModelImpl bukkitManager(@NotNull JavaPlugin plugin) {
		return new BukkitConfigurationModelImpl(plugin);
	}
	
	/**
	 * Returns a new instance of BukkitConfigurationHandlerImpl.
	 *
	 * @param model A ConfigurationModel instance.
	 * @return A ManagerModel instance.
	 */
	@Contract (value = "_ -> new", pure = true)
	static @NotNull BukkitConfigurationHandlerImpl bukkitHandler(@NotNull BukkitConfigurationModel model) {
		return new BukkitConfigurationHandlerImpl(model);
	}
	
	/**
	 * Returns a new BungeeConfigurationModelImpl object.
	 *
	 * @param plugin A Plugin instance required for the files creation.
	 * @return A BukkitConfigurationModelImpl object.
	 */
	@Contract ("_ -> new")
	static @NotNull BungeeConfigurationModelImpl bungeeManager(@NotNull Plugin plugin) {
		return new BungeeConfigurationModelImpl(plugin);
	}
	
	/**
	 * Returns a new instance of BungeeConfigurationHandlerImpl.
	 *
	 * @param model A BungeeConfigurationModel object.
	 * @return A BungeeConfigurationHandlerImpl object.
	 */
	@Contract (value = "_ -> new", pure = true)
	static @NotNull BungeeConfigurationHandlerImpl bungeeHandler(@NotNull BungeeConfigurationModel model) {
		return new BungeeConfigurationHandlerImpl(model);
	}
}
