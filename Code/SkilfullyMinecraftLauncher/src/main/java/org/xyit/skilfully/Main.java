package org.xyit.skilfully;

import org.xyit.skilfully.Value.PublicValue;

import static org.xyit.skilfully.UI.Index.MainUI;

public class Main {
    public static void main(String[] args) {

        //初始化
        Initialize();

        //启动主界面
        MainUI();

    }

    private static void Initialize() {
        new PublicValue();
    }
}