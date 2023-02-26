package next.api.kbukkit

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

/**
 * Listener for KBukkit events.
 * @param plugin The KBukkit plugin or Java Plugin.
 * @param listener The listener to register and execute responses to events.
 */
fun eventListener(plugin:JavaPlugin ,listener: Listener) {
    Bukkit.getPluginManager().registerEvents(listener, plugin)
}

open class KBukkitListener() : Listener {
    fun register(plugin: JavaPlugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }
}
