package next.api.kbukkit

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

/**
 * KBukkit command executor.
 * @param command The command to execute.
 * @param commandExecutor The class or lambda to execute the command.
 */
fun commandExecutor(command:String, commandExecutor: CommandExecutor) {
    Bukkit.getPluginCommand(command)?.setExecutor(commandExecutor)
}

/**
 * TabCompleter for commands.
 * @param command The command to execute.
 * @param tabCompleter The class or lambda to execute the tab completion.
 */
fun tabComplete(command: String, tabCompleter: TabCompleter) {
    Bukkit.getPluginCommand(command)?.tabCompleter = tabCompleter
}

/**
 * Listens for the player execution of the command.
 * @author NextDev
 */
open class KCommandExecutorPlayer : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender is Player) {
            onPlayerCommand(sender as Player, command, label, args)
        }
        return true
    }

    open fun onPlayerCommand(player: Player, command: Command,label: String, args: Array<out String>): Boolean {
        return true
    }
}

/**
 * Command sender for commands.
 * With tab completion.
 * @author NextDev
 */
open class KCommandExecutor : CommandExecutor, TabCompleter, KCommandExecutorPlayer() {
    /**
     * Command listener
     * @param command The command to execute.
     * @param sender The sender of the command.
     * @param args The arguments of the command.
     * @param label The label of the command.
     * @return The result of the command execution.
     */
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return true
    }

    /**
     * Listener for commands executed by the player.
     * @param player The sender of the command.
     * @param command The command to execute.
     * @param label The label of the command.
     * @param args The arguments of the command.
     * @return The result of the command execution.
     */
    override fun onPlayerCommand(player: Player, command: Command, label: String, args: Array<out String>): Boolean {
        return super.onPlayerCommand(player, command, label, args)
    }

    /**
     * Tab completion
     * @param sender The sender of the command.
     * @param command The command to complete.
     * @param label The label of the command.
     * @param args The arguments to complete.
     * @return a list of suggestions.
     */
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String> {
        return mutableListOf()
    }
}
