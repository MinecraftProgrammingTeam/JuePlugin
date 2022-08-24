package top.mpt.jueplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import top.mpt.jueplugin.JuePlugin;

import java.util.List;

import static top.mpt.jueplugin.JuePlugin.normal;

public class setjue implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Plugin config = JuePlugin.getPlugin(JuePlugin.class);
        List<String> jueList = config.getConfig().getStringList("unJueablePlayerList");
        if(Bukkit.getConsoleSender() == sender){
            sender.sendMessage(normal + ChatColor.RED + "控制台设置个锤子的jue，怕有人jue你？");
            return false;
        }

        if(args.length != 1){
            sender.sendMessage(normal + ChatColor.RED + "格式错误！/setjue on/off 启用/禁用被jue");
        }
        else if(args[0].equals("on")){
            if(!jueList.contains(sender.getName())){
                sender.sendMessage(normal + ChatColor.RED + "您已经允许被jue了，请勿重复开启");
                return false;
            }
            jueList.remove(sender.getName());
            config.getConfig().set("unJueablePlayerList", jueList);
            sender.sendMessage(normal + ChatColor.YELLOW + "已允许其他玩家撅你");
        }
        else if(args[0].equals("off")){
            if(jueList.contains(sender.getName())){
                sender.sendMessage(normal + ChatColor.RED + "您已经禁止被jue了，请勿重复开启");
                return false;
            }
            jueList.add(sender.getName());
            config.getConfig().set("unJueablePlayerList", jueList);
            sender.sendMessage(normal + ChatColor.GRAY + "已禁止其他玩家撅你");
        }
        else{
            sender.sendMessage(normal + ChatColor.RED + "格式错误！/setjue on/off 启用/禁用被jue");
        }
        config.saveConfig();
        return false;
    }
}
