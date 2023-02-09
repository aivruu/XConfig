package net.xconfig.bukkit;

import com.google.common.base.Preconditions;
import net.xconfig.bukkit.model.SimpleConfigurationHandler;
import net.xconfig.bukkit.model.SimpleConfigurationManager;
import net.xconfig.bukkit.model.config.ConfigurationManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Interface to get the library classes instances.
 *
 * @author InitSync
 * @version 1.1.5
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
		Preconditions.checkNotNull(plugin, "The JavaPlugin instance cannot be null.");
		
		return new SimpleConfigurationManager(plugin);
	}
	
	/**
	 * Returns a new instance of BukkitConfigurationHandlerImpl object.
	 *
	 * @param manager A BukkitConfigurationModel instance.
	 * @return A BukkitConfigurationHandlerImpl instance.
	 */
	static SimpleConfigurationHandler newConfigurationHandler(ConfigurationManager manager) {
		Preconditions.checkNotNull(manager, "The ConfigurationManager instance cannot be null.");
		
		return new SimpleConfigurationHandler(manager);
	}
}
