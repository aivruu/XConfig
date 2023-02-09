package net.xconfig.bungee;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.plugin.Plugin;
import net.xconfig.bungee.model.SimpleConfigurationHandler;
import net.xconfig.bungee.model.SimpleConfigurationManager;
import net.xconfig.bungee.model.config.ConfigurationManager;

/**
 * Interface to get the library classes instances.
 *
 * @author InitSync
 * @version 1.1.5
 * @since 1.1.26
 */
public interface XConfigBungee {
	/**
	 * Returns a new instance of BungeeConfigurationModelImpl object.
	 *
	 * @param plugin A Plugin instance.
	 * @return A BukkitConfigurationModelImpl instance.
	 */
	static SimpleConfigurationManager newConfigurationManager(Plugin plugin) {
		Preconditions.checkNotNull(plugin, "The Plugin instance cannot be null.");
		return new SimpleConfigurationManager(plugin);
	}
	
	/**
	 * Returns a new instance of BungeeConfigurationHandlerImpl object.
	 *
	 * @param manager A BungeeConfigurationModel instance.
	 * @return A BungeeConfigurationHandlerImpl instance.
	 */
	static SimpleConfigurationHandler newConfigurationHandler(ConfigurationManager manager) {
		Preconditions.checkNotNull(manager, "The ConfigurationManager object cannot be null.");
		return new SimpleConfigurationHandler(manager);
	}
}
