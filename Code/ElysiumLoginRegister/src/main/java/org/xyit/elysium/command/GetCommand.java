package org.xyit.elysium.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import static org.xyit.elysium.ElysiumLoginRegister.YamlValueString;
import static org.xyit.elysium.Password.Password.CheckIn;
import static org.xyit.elysium.var.PublicVar.*;

public class GetCommand {

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static boolean command(CommandSender sender, Command command, String label, String[] args) {

        //主命令【elysiumloginregister】

        if (label.equalsIgnoreCase("elysiumloginregister")) {
            sender.sendMessage(prefix + "ElysiumLoginRegister 版本v1.0 By Skilfully Made Group");
        }


        //主命令【reg】

        if (label.equalsIgnoreCase("reg")) {
            //获取内容
            if (!(sender instanceof Player)) {
                // 不是玩家
                sender.sendMessage(prefix + onlyPlayer);
                return true;
            }
            if (args.length == 2) {
                CheckIn(sender,command,label,args);
            } else {
                sender.sendMessage(prefix + Invalid_Parameters);
                return true;
            }
        }


        //主命令【register】

        if (label.equalsIgnoreCase("register")) {
            //获取内容
            if (!(sender instanceof Player)) {
                // 不是玩家
                sender.sendMessage(prefix + onlyPlayer);
                return true;
            }
            if (args.length == 2) {
                CheckIn(sender,command,label,args);
            } else {
                sender.sendMessage(prefix + Invalid_Parameters);
                return true;
            }
        }

        return true;
    }
}
