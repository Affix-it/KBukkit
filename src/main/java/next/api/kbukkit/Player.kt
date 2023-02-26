package next.api.kbukkit

import org.bukkit.BanList
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scoreboard.Scoreboard

fun Entity.msg(msg:String) {
    this.sendMessage(msg.tac())
}

fun Entity.msg(msg:String, vararg args: String) {
    this.sendMessage(msg.tac(), *args.onEach { it.tac() })
}

fun Entity.msg(vararg args: String) {
    this.sendMessage(*args.onEach { it.tac() })
}

var Entity.loc
    get() = this.location
    set(value) {
        this.teleport(value)
    }

fun Player.kick(reason: String) {
    this.kickPlayer(reason)
}

fun Player.createScoreboard(block: () -> Scoreboard) {
    this.scoreboard = block.invoke()
}

fun Player.kick() {
    this.kickPlayer("&cUnspecified reason".tac())
}

fun Player.ban(reason: String) {
    Bukkit.getBanList(BanList.Type.NAME).addBan(this.name, reason.tac(), null,null)
}

fun Player.ban(reason: String, type: BanList.Type) {
    Bukkit.getBanList(type).addBan(this.name, reason.tac(), null,null)
}

fun Player.unban(reason: String) {
    Bukkit.getBanList(BanList.Type.NAME).pardon(this.name)
}

fun Player.unbanIp() {
    Bukkit.unbanIP(this.address?.hostName ?: "")
}

fun Entity.tp(location: Location) {
     this.loc = location
}

fun Entity.tp(x: Double, y: Double, z: Double) {
    this.loc = Location(this.world, x, y, z)
}

fun Entity.tp(world: World, x: Double, y: Double, z: Double) {
    this.loc = Location(world, x, y, z)
}

private val frozenEntities = mutableListOf<Entity>()
private fun freeze(entity: Entity) {
    object : BukkitRunnable() {
        val initialPosition = entity.loc
        override fun run() {
            if(!entity.freeze)
                this.cancel()
            entity.loc = initialPosition
        }
    }.runTaskTimer(targetPlugin ?: return, 0L, 0L)
}

var Entity.freeze:Boolean
    get() = frozenEntities.contains(this)
    set(value) {
        if (value) {
            frozenEntities.add(this)
            freeze(this)
        } else {
            frozenEntities.remove(this)
        }
    }


var Player.compassTargetEntity:Entity?
    get() {
        val loc = this.compassTarget
        val world = loc.world
        val nearbyEntities = world?.getNearbyEntities(loc, 255.0,255.0,255.0)
        return nearbyEntities?.firstOrNull { entity -> entity.loc == loc }
    }
    set(value) {
        this.compassTarget = value?.loc ?: this.loc
    }

fun Player.chat(method: () -> String) {
    this.chat(method.invoke())
}

fun Entity.tp(world: String, x: Double, y: Double, z: Double) {
    val worldObj = Bukkit.getWorld(world) ?: this.world
    this.loc = Location(worldObj, x, y, z)
}