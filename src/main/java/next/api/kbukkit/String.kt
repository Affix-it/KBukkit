package next.api.kbukkit

import org.bukkit.ChatColor

fun String.tac(code: Char = '&'):String = ChatColor.translateAlternateColorCodes(code, this)

@Deprecated("Use instead of this function", ReplaceWith("this.tac(code)"), DeprecationLevel.WARNING)
fun String.translateAlternateColorCodes(code: Char = '&'):String = ChatColor.translateAlternateColorCodes(code, this)


