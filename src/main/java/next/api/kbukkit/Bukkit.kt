package next.api.kbukkit

import org.bukkit.Bukkit
import org.bukkit.scoreboard.Scoreboard

fun Bukkit.getNewScoreboard(): Scoreboard? = Bukkit.getScoreboardManager()?.newScoreboard
fun Bukkit.getMainScoreboard(): Scoreboard? = Bukkit.getScoreboardManager()?.mainScoreboard
