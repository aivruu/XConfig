package net.xconfig;

import net.md_5.bungee.api.plugin.Plugin;
import net.xconfig.bukkit.config.BukkitConfigurationModel;
import net.xconfig.bukkit.impls.BukkitConfigurationHandlerImpl;
import net.xconfig.bukkit.impls.BukkitConfigurationModelImpl;
import net.xconfig.bungee.config.BungeeConfigurationModel;
import net.xconfig.bungee.impls.BungeeConfigurationHandlerImpl;
import net.xconfig.bungee.impls.BungeeConfigurationModelImpl;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Interface to handle the instances creation.
 *
 * @author InitSync
 * @version 1.0.8
 * @since 1.0.6
 */
public interface XConfig {
	/**
	 * Returns a new instance of BukkitConfigurationModelImpl.
	 *
	 * @param plugin A JavaPlugin instance required for the files creation.
	 * @param logger A Logger object.
	 * @return A ManagerModel instance.
	 */
	static BukkitConfigurationModelImpl bukkitManager(JavaPlugin plugin, Logger logger) {
		return new BukkitConfigurationModelImpl(plugin, logger);
	}
	
	/**
	 * Returns a new instance of BukkitConfigurationHandlerImpl.
	 *
	 * @param model A ConfigurationModel instance.
	 * @param logger A Logger object.
	 * @return A ManagerModel instance.
	 */
	static BukkitConfigurationHandlerImpl bukkitHandler(BukkitConfigurationModel model, Logger logger) {
		return new BukkitConfigurationHandlerImpl(model, logger);
	}
	
	/**
	 * Returns a new BungeeConfigurationModelImpl object.
	 *
	 * @param plugin A Plugin instance required for the files creation.
	 * @param logger A Logger object.
	 * @return A BukkitConfigurationModelImpl object.
	 */
	static BungeeConfigurationModelImpl bungeeManager(Plugin plugin, Logger logger) {
		return new BungeeConfigurationModelImpl(plugin, logger);
	}
	
	/**
	 * Returns a new instance of BungeeConfigurationHandlerImpl.
	 *
	 * @param model A BungeeConfigurationModel object.
	 * @param logger A Logger object.
	 * @return A BungeeConfigurationHandlerImpl object.
	 */
	static BungeeConfigurationHandlerImpl bungeeHandler(BungeeConfigurationModel model, Logger logger) {
		return new BungeeConfigurationHandlerImpl(model, logger);
	}
}
