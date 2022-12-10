package net.xconfig;

import net.md_5.bungee.api.plugin.Plugin;
import net.xconfig.bukkit.config.BukkitConfigurationModel;
import net.xconfig.bukkit.impls.BukkitConfigurationHandlerImpl;
import net.xconfig.bukkit.impls.BukkitConfigurationModelImpl;
import net.xconfig.bungee.config.BungeeConfigurationModel;
import net.xconfig.bungee.impls.BungeeConfigurationHandlerImpl;
import net.xconfig.bungee.impls.BungeeConfigurationModelImpl;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Interface to handle the instances creation.
 *
 * @author InitSync
 * @version 1.1.23
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
	static BukkitConfigurationModelImpl bukkitManager(JavaPlugin plugin) {
		return new BukkitConfigurationModelImpl(plugin);
	}
	
	/**
	 * Returns a new instance of BukkitConfigurationHandlerImpl.
	 *
	 * @param model A ConfigurationModel instance.
	 * @param logger A Logger object.
	 * @return A ManagerModel instance.
	 */
	static BukkitConfigurationHandlerImpl bukkitHandler(BukkitConfigurationModel model) {
		return new BukkitConfigurationHandlerImpl(model);
	}
	
	/**
	 * Returns a new BungeeConfigurationModelImpl object.
	 *
	 * @param plugin A Plugin instance required for the files creation.
	 * @param logger A Logger object.
	 * @return A BukkitConfigurationModelImpl object.
	 */
	static BungeeConfigurationModelImpl bungeeManager(Plugin plugin) {
		return new BungeeConfigurationModelImpl(plugin);
	}
	
	/**
	 * Returns a new instance of BungeeConfigurationHandlerImpl.
	 *
	 * @param model A BungeeConfigurationModel object.
	 * @param logger A Logger object.
	 * @return A BungeeConfigurationHandlerImpl object.
	 */
	static BungeeConfigurationHandlerImpl bungeeHandler(BungeeConfigurationModel model) {
		return new BungeeConfigurationHandlerImpl(model);
	}
}
