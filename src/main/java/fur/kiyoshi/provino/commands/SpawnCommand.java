package fur.kiyoshi.provino.commands;

import fur.kiyoshi.provino.Provino;
import fur.kiyoshi.provino.utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @SuppressWarnings({"DataFlowIssue", "PatternVariableCanBeUsed"})
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        World world = Bukkit.getWorld(Provino.getInstance().getConfig().getString("SpawnCoords.world"));
        int x = Provino.getInstance().getConfig().getInt("SpawnCoords.x");
        int y = Provino.getInstance().getConfig().getInt("SpawnCoords.y");
        int z = Provino.getInstance().getConfig().getInt("SpawnCoords.z");
        int pitch = Provino.getInstance().getConfig().getInt("SpawnCoords.pitch");
        int yaw = Provino.getInstance().getConfig().getInt("SpawnCoords.yaw");
        if(!(sender instanceof Player)) {
            sender.sendMessage(Format.color(Provino.getInstance().getConfig().getString("Messages.ConsoleMessage")));
            return true;
        }

        Player player = (Player)sender;

        if(player.hasPermission("provino.command.spawn")) {
            if(args.length == 0) {
                Location location = new Location(world, x, y, z, pitch, yaw);
                if(world != null) {
                    player.teleport(location);
                    player.sendMessage(Format.color(Provino.getInstance().getConfig().getString("Messages.SpawnTp")));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100F, 1F);
                } else {
                    player.sendMessage(Format.color(Provino.getInstance().getConfig().getString("Messages.SpawnObstructed")));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 100F, 1F);
                }
            }
            if(args.length >= 1) {
                if(args[0].equalsIgnoreCase("set")) {
                    if(player.hasPermission("provino.command.spawn.set")) {
                        Provino.getInstance().getConfig().set("SpawnCoords.world", player.getWorld().getName());
                        Provino.getInstance().getConfig().set("SpawnCoords.x", player.getLocation().getBlockX());
                        Provino.getInstance().getConfig().set("SpawnCoords.y", player.getLocation().getBlockY());
                        Provino.getInstance().getConfig().set("SpawnCoords.z", player.getLocation().getBlockZ());
                        Provino.getInstance().getConfig().set("SpawnCoords.pitch", player.getLocation().getPitch());
                        Provino.getInstance().getConfig().set("SpawnCoords.yaw", player.getLocation().getYaw());
                        Provino.getInstance().saveConfig();
                        player.sendMessage(Format.color(Provino.getInstance().getConfig().getString("Messages.SpawnSet")));
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100F, 1F);
                    } else {
                        player.sendMessage(Format.color(Provino.getInstance().getConfig().getString("Messages.AccessDenied")));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 100F, 1F);
                    }
                }
            }
        } else {
            player.sendMessage(Format.color(Provino.getInstance().getConfig().getString("Messages.AccessDenied")));
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 100F, 1F);
        }

        return false;
    }
}
