package org.xyit.elysium;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.xyit.elysium.command.GetCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.bukkit.Bukkit.getPluginManager;
import static org.xyit.elysium.var.SendMessage.getMessage;

public final class ElysiumLoginRegister extends JavaPlugin {


    public static void console(String s) {
        CommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(("§3[Elysium]§e[ElysiumLoginRegister] §f" + s));
    }

    @Override
    public void onEnable() {

        // 注册命令
        // 【reg】悬空注释：1-[密码] 2-[确认密码]
        getServer().getPluginCommand("reg").setExecutor(this);
        getServer().getPluginCommand("reg").setTabCompleter((commandSender, command, s, args)
                -> {
            if (args.length == 1) {
                return Arrays.asList("密码");
            }
            if (args.length == 2) {
                return Collections.singletonList("确认密码");
            }
            return Collections.emptyList();
        });
        // 【register】悬空注释：1-[密码] 2-[确认密码]
        getServer().getPluginCommand("register").setExecutor(this);
        getServer().getPluginCommand("register").setTabCompleter((commandSender, command, s, args)
                -> {
            if (args.length == 1) {
                return Arrays.asList("密码");
            }
            if (args.length == 2) {
                return Collections.singletonList("确认密码");
            }
            return Collections.emptyList();
        });


        // 判断是否加载前置插件
//        try {
//            Class.forName("org.xyit.elysium.elysiumlogin");
//
//        } catch (ClassNotFoundException e) {
//            console("§c未找到 §eElysiumLogin §c插件已卸载");
//            console("§cNot found §eElysiumLogin §c. Plugin has been unloaded.");
//            PluginManager pm = getPluginManager();
//            pm.disablePlugin(this);
//        }
        console("正在加载数据库");
        getMessage();
    }

    @Override
    public void onDisable() {
        console("插件已卸载");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return GetCommand.command(sender, command, label, args);
    }

    public static String YamlValueString(String FilePath, String FileName, String Key) {
        File configFile = new File(FilePath, FileName);
        // 检查文件是否存在，如果不存在则控制台报错
        if (!configFile.exists()) {
            console("§c错误的配置文件!");
            return "§c错误的配置文件!";
        }
        // 使用YamlConfiguration加载文件
        YamlConfiguration config;
        config = YamlConfiguration.loadConfiguration(configFile);//读取值
        return config.getString(Key);
    }
}
