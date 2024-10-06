package org.xyit.elysium.Password;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import static org.xyit.elysium.ElysiumLoginRegister.YamlValueString;
import static org.xyit.elysium.command.GetCommand.bytesToHex;
import static org.xyit.elysium.var.PublicVar.*;

public class Password {

    public static void CheckIn(CommandSender sender, Command command, String label, String[] args) {
        String Key1 = args[0];
        String Key2 = args[1];
        if (Objects.equals(Key1, Key2)) {
            String value = Key1;
            String hash256 = "";
            try {
                // 获取SHA-256 MessageDigest实例
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                // 更新MessageDigest对象
                md.update(value.getBytes());
                // 获取散列值
                byte[] digest = md.digest();
                // 将散列值转换为十六进制字符串
                hash256 = bytesToHex(digest);
                // 得到hash256
            } catch (NoSuchAlgorithmException e) {
                sender.sendMessage(ServerError);
                return;
            }
            //将 hash 256 写入数据库

            try {
                String UrlSetting = YamlValueString("plugins\\ElysiumLogin","setting.yml","SQLite.Path");
                String thisurl;
                if (Objects.equals(UrlSetting, "this")) {
                    //选中本地目录
                    thisurl = "plugins/ElysiumLogin";
                } else {
                    thisurl = UrlSetting;
                }
                String database = YamlValueString("plugins\\ElysiumLogin","setting.yml","SQLite.Database");
                String url = ("jdbc:sqlite:" + thisurl + "/" + database + ".db");
                Class.forName("org.sqlite.JDBC");
                //连接数据库
                Connection connection = DriverManager.getConnection(url);
                //判断是否已经注册
                String sql = "SELECT * FROM Data WHERE PlayerName = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
                preparedStatement1.setString(1, sender.getName()); // 设置查询参数

                ResultSet resultSet1 = preparedStatement1.executeQuery();

                if (resultSet1.next()) {
                    sender.sendMessage(prefix + already);
                    return;
                } else {
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Data (PlayerName, PlayerPassword) VALUES (?, ?)");
                    {

                        preparedStatement.setString(1, sender.getName());
                        preparedStatement.setString(2, hash256);

                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            sender.sendMessage(prefix + successfully);
                            return;
                        } else {
                            sender.sendMessage(prefix + ServerError);
                            return;
                        }
                    }
                }
            } catch (ClassNotFoundException | java.sql.SQLException ignored) {
                sender.sendMessage(prefix + ServerError);
                return;
            }

        } else {
            sender.sendMessage(prefix + noSame);
            return;
        }
    }

}
