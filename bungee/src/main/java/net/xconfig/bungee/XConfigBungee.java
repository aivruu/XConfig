package net.xconfig.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import net.xconfig.bungee.config.BungeeConfigurationModel;
import net.xconfig.bungee.impls.BungeeConfigurationHandlerImpl;
import net.xconfig.bungee.impls.BungeeConfigurationModelImpl;

/**
 * Interface to get the library classes instances.
 *
 * @author InitSync
 * @version 1.1.26
 * @since 1.1.26
 */
public interface XConfigBungee {
	/**
	 * Returns a new instance of BungeeConfigurationModelImpl object.
	 *
	 * @param plugin A Plugin instance.
	 * @return A BukkitConfigurationModelImpl instance.
	 */
	static BungeeConfigurationModelImpl manager(Plugin plugin) {
		return new BungeeConfigurationModelImpl(plugin);
	}
	
	/**
	 * Returns a new instance of BungeeConfigurationHandlerImpl object.
	 *
	 * @param manager A BungeeConfigurationModel instance.
	 * @return A BungeeConfigurationHandlerImpl instance.
	 */
	static BungeeConfigurationHandlerImpl handler(BungeeConfigurationModel manager) {
		return new BungeeConfigurationHandlerImpl(manager);
	}
}
