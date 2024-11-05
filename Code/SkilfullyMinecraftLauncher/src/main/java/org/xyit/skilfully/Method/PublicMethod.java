package org.xyit.skilfully.Method;

public class PublicMethod {

    /**
     * INFO信息输出，自动换行
     * @param value String值
     */
    public static void printInfo(String value) {
        System.out.print("\u001B[37m[CONSOLE][INFO] " + value + "\u001B[37m\n");
    }

    /**
     * ERROR信息输出，自动换行
     * @param value String值
     */
    public static void printError(String value) {
        System.out.print("\u001B[37m[CONSOLE]\u001B[31m[ERROR] " + value + "\u001B[37m\n");
    }

    /**
     * WARN信息输出，自动换行
     * @param value String值
     */
    public static void printWarn(String value) {
        System.out.print("\u001B[37m[CONSOLE]\u001B[33m[WARN] " + value + "\u001B[37m\n");
    }

}
