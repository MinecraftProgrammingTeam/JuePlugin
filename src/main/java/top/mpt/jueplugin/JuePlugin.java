package top.mpt.jueplugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import top.mpt.jueplugin.commands.jue;
import top.mpt.jueplugin.commands.setjue;

import java.util.Objects;

public final class JuePlugin extends JavaPlugin {

    public static String normal = ChatColor.BLUE + "[JuePlugin] ";
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Objects.requireNonNull(getCommand("jue")).setExecutor(new jue());
        Objects.requireNonNull(getCommand("setjue")).setExecutor(new setjue());

        System.out.println(normal + "插件已启用");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println(normal + "插件已禁用");
    }
}
