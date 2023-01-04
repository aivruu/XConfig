package net.xconfig.bukkit;

import net.xconfig.bukkit.config.ConfigurationManager;
import net.xconfig.bukkit.impls.SimpleConfigurationHandler;
import net.xconfig.bukkit.impls.SimpleConfigurationManager;
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
	static SimpleConfigurationManager manager(JavaPlugin plugin) {
		return new SimpleConfigurationManager(plugin);
	}
	
	/**
	 * Returns a new instance of BukkitConfigurationHandlerImpl object.
	 *
	 * @param manager A BukkitConfigurationModel instance.
	 * @return A BukkitConfigurationHandlerImpl instance.
	 */
	static SimpleConfigurationHandler handler(ConfigurationManager manager) {
		return new SimpleConfigurationHandler(manager);
	}
}
