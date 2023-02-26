package next.api.kbukkit

import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

// KBukkit API
var targetPlugin: Plugin? = null

/**
 * KBukkit plugin.
 * @author NextDev
 */
open class KBukkit : JavaPlugin() {
    companion object Options{
        fun registerPlugin(plugin: JavaPlugin) {
            targetPlugin = plugin
        }
    }
}

fun test() {

}



