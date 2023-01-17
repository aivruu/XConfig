package net.xconfig.bukkit;

import net.xconfig.bukkit.model.SimpleConfigurationHandler;
import net.xconfig.bukkit.model.SimpleConfigurationManager;
import net.xconfig.bukkit.model.config.ConfigurationManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Interface to get the library classes instances.
 *
 * @author InitSync
 * @version 1.1.4
 * @since 1.1.26
 */
public interface XConfigBukkit {
	/**
	 * Returns a new instance of BukkitConfigurationModelImpl object.
	 *
	 * @param plugin A JavaPlugin instance.
	 * @return A BukkitConfigurationModelImpl instance.
	 */
	static SimpleConfigurationManager newConfigurationManager(JavaPlugin plugin) {
		return new SimpleConfigurationManager(plugin);
	}
	
	/**
	 * Returns a new instance of BukkitConfigurationHandlerImpl object.
	 *
	 * @param manager A BukkitConfigurationModel instance.
	 * @return A BukkitConfigurationHandlerImpl instance.
	 */
	static SimpleConfigurationHandler newConfigurationHandler(ConfigurationManager manager) {
		return new SimpleConfigurationHandler(manager);
	}
}
