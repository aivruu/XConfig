package net.xconfig.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import net.xconfig.bungee.models.ConfigurationManager;
import net.xconfig.bungee.config.SimpleConfigurationHandler;
import net.xconfig.bungee.config.SimpleConfigurationManager;

import javax.annotation.Nonnull;

/**
 * Interface to get the library classes instances.
 *
 * @author InitSync
 * @version 1.1.4
 * @since 1.1.26
 */
public interface XConfigBungee {
	/**
	 * Returns a new instance of BungeeConfigurationModelImpl object.
	 *
	 * @param plugin A Plugin instance.
	 * @return A BukkitConfigurationModelImpl instance.
	 */
	static SimpleConfigurationManager manager(@Nonnull Plugin plugin) {
		return new SimpleConfigurationManager(plugin);
	}
	
	/**
	 * Returns a new instance of BungeeConfigurationHandlerImpl object.
	 *
	 * @param manager A BungeeConfigurationModel instance.
	 * @return A BungeeConfigurationHandlerImpl instance.
	 */
	static SimpleConfigurationHandler handler(@Nonnull ConfigurationManager manager) {
		return new SimpleConfigurationHandler(manager);
	}
}
