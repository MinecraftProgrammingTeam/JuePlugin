package top.mpt.jueplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import top.mpt.jueplugin.JuePlugin;

import java.util.List;

import static top.mpt.jueplugin.JuePlugin.normal;

public class jue implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Plugin config = JuePlugin.getPlugin(JuePlugin.class);
        config.reloadConfig();
        if(args.length != 1){
            sender.sendMessage(normal + ChatColor.RED + "格式错误！正确格式：/jue <玩家名>");
            return false;
        }
        if(Bukkit.getConsoleSender() == sender){
            sender.sendMessage(normal + ChatColor.RED + "控制台禁止撅人！");
            return false;
        }
        Player player = Bukkit.getPlayer(args[0]);
        List<String> jueList = config.getConfig().getStringList("unJueablePlayerList");
        if(player == null){
            sender.sendMessage(normal + ChatColor.RED + "该玩家不在线，请稍后再撅(悲).");
            return false;
        }
        if(jueList.contains(args[0])){
            sender.sendMessage(normal + ChatColor.RED + "该玩家不想让你撅");
            return false;
        }
        if(jueList.contains(sender.getName())){
            sender.sendMessage(normal + ChatColor.RED + "己所不欲，勿施于人");
            return false;
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "effect give " + args[0] + " minecraft:nausea 15 0");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "effect give " + args[0] + " minecraft:blindness 10 0");
        player.sendMessage(ChatColor.YELLOW + "你被" + sender.getName() + "撅了");
        return false;
    }
}
