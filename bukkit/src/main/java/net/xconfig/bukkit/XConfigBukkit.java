package net.xconfig.bukkit;

import net.xconfig.bukkit.config.BukkitConfigurationModel;
import net.xconfig.bukkit.impls.BukkitConfigurationHandlerImpl;
import net.xconfig.bukkit.impls.BukkitConfigurationModelImpl;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Interface to get the library classes instances.
 *
 * @author InitSync
 * @version 1.1.26
 * @since 1.1.26
 */
public interface XConfigBukkit {
	/**
	 * Returns a new instance of BukkitConfigurationModelImpl object.
	 *
	 * @param plugin A JavaPlugin instance.
	 * @return A BukkitConfigurationModelImpl instance.
	 */
	static BukkitConfigurationModelImpl manager(JavaPlugin plugin) {
		return new BukkitConfigurationModelImpl(plugin);
	}
	
	/**
	 * Returns a new instance of BukkitConfigurationHandlerImpl object.
	 *
	 * @param manager A BukkitConfigurationModel instance.
	 * @return A BukkitConfigurationHandlerImpl instance.
	 */
	static BukkitConfigurationHandlerImpl handler(BukkitConfigurationModel manager) {
		return new BukkitConfigurationHandlerImpl(manager);
	}
}
