package org.xyit.skilfully.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import static org.xyit.skilfully.Method.PublicMethod.*;
import static org.xyit.skilfully.Value.PublicValue.*;

public class Index extends JFrame {


    public static void MainUI() {

        //主窗体
        Main = new JFrame();
        GridBagConstraints gbc = new GridBagConstraints();// 布局管理器


        //创建JPanel等
        CenterPanel = new JPanel(new GridBagLayout());// 创建中心面板，使用GridBagLayout布局管理器
        FunctionList = new JPanel(new GridBagLayout()); // 创建左侧面板，用于放置模式选择器，也使用GridBagLayout
        OutputPanel = new JPanel(new GridBagLayout()); // 创建右侧面板，使用GridBagLayout


        //Settings
        //  - FunctionList
        {
            // 设置FunctionList面板的背景颜色和圆润的边框
            FunctionList.setBackground(new Color(255, 255, 255, 128)); // 设置背景颜色
            FunctionList.setLayout(new GridBagLayout()); // 设置GridBagLayout为布局管理器
        }
        //  - OutputPanel
        {
            OutputPanel.setLayout(new BorderLayout());
            OutputPanel.setBackground(new Color(0,0,0, 0));
        }
        //  - CenterPanel
        {
            CenterPanel.setBackground(new Color(94, 0, 0));
        }



        // Buttons
        {
            // 创建一些图标按钮作为模式选择器
            JButton BUTTON_HOME = new JButton("首页");
            JButton BUTTON_GAMELIST = new JButton("游戏列表");
            JButton BUTTON_DOWNLOADCENTER = new JButton("下载中心");
            JButton BUTTON_SETTING = new JButton("设置");

            // 设置按钮
            setButton(BUTTON_HOME,"Home1.png",gbc,0);
            setButton(BUTTON_GAMELIST,"List1.png",gbc,1);
        }



        //gbc布局参数，一般不动
        {
            //  - 设置布局参数
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.weightx = 0; // 左侧面板宽度固定
            gbc.weighty = 1; // 高度自由变化
            //  - 将左侧面板添加到中心面板
            gbc.gridx = 0; // 第一列
            gbc.gridy = 0; // 第一行
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            CenterPanel.add(FunctionList, gbc);
            //  - 添加一个垂直分隔符以保持左侧面板的宽度固定
            gbc.gridx = 1; // 第二列
            gbc.gridy = 0; // 第一行
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.VERTICAL;
            gbc.weightx = 0;
            CenterPanel.add(Box.createRigidArea(new Dimension(0, 1)), gbc);
            //  - 将右侧面板添加到中心面板
            gbc.gridx = 2; // 第三列
            gbc.gridy = 0; // 第一行
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1; // 右侧面板占据剩余宽度
            gbc.weighty = 1; // 高度自由变化
            CenterPanel.add(OutputPanel, gbc);
        }



        // 主窗体设置
        Main.add(CenterPanel);
        Main.setResizable(false);// 禁止调整大小
        Main.setSize(1100,650);
        Main.setLocationRelativeTo(null);
        Main.setVisible(true);
    }

    // 添加按钮到FunctionList的方法
    private static void addAlignedButtonToFunctionList(JButton button, GridBagConstraints gbc, int gridx, int gridy) {
        gbc.gridx = gridx; // 设置gridx
        gbc.gridy = gridy; // 设置gridy
        gbc.gridwidth = 1; // 设置gridwidth
        gbc.gridheight = 1; // 设置gridheight
        gbc.fill = GridBagConstraints.HORIZONTAL; // 设置填充方式为水平填充
        gbc.anchor = GridBagConstraints.NORTH; // 设置锚点为北，即顶部对齐
        gbc.weightx = 1.0; // 设置水平权重为1.0，允许按钮水平扩展
        gbc.weighty = 0; // 设置垂直权重为0，不扩展按钮高度
        gbc.insets = new Insets(0, 0, 0, 0); // 设置外边距，顶部0像素，底部5像素，左右0像素
        FunctionList.add(button, gbc);
    }

    /**
     * 向FunctionList中添加按钮
     * @param button JButton对象
     * @param purl 图标文件，只要文件名
     * @param gbc gbc管理器
     * @param number 第几个，从0开始
     */
    private static void setButton(JButton button, String purl, GridBagConstraints gbc, int number) {
        purl = ("/images/" + purl);
        URL pIconUrl = Index.class.getResource(purl);
        if (pIconUrl == null) {
            printError("尝试加载资源时失败.");
            System.exit(1);
        }
        ImageIcon pIcon = new ImageIcon(pIconUrl);
        Image pImage = pIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH); // 将图片大小修改为25x25
        ImageIcon scaledIcon = new ImageIcon(pImage);
        button.setIcon(scaledIcon);
        button.setMargin(new Insets(0, 0, 0, 10));
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border roundedBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true);
        // 设置文本和图标的相对位置
        button.setHorizontalTextPosition(AbstractButton.RIGHT); // 文本在图标的右侧
        button.setVerticalTextPosition(AbstractButton.CENTER); // 文本和图标垂直居中对齐

        // 设置图标和文本之间的间隙
        button.setIconTextGap(10); // 可以根据需要调整这个值

        // 设置按钮的内容区域对齐方式为左对齐
        button.setHorizontalAlignment(AbstractButton.LEFT);
        button.setBorder(BorderFactory.createCompoundBorder(roundedBorder, emptyBorder));
        button.setFocusPainted(false);//取消焦点绘制
        button.setBackground(new Color(255, 255, 255));

        // 为每个按钮添加鼠标事件监听器
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                sourceButton.setBackground(new Color(255, 255, 255)); // 原始背景色
                sourceButton.setBackground(new Color(255, 255, 255, 100)); // 设置半透明背景色
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                sourceButton.setBackground(new Color(255, 255, 255)); // 恢复原始背景色
            }
        });

        addAlignedButtonToFunctionList(button, gbc, 0, number);
    }

}
