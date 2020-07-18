package com.neoniou.ak;

/**
 * @author Neo.Zzj
 * @date 2020/5/29
 */
public interface AdbConstant {

    String CONNECT = "adb connect 127.0.0.1:7555";

    String GET_VM_SIZE = "adb shell wm size";

    String SCREEN_SHOT = "adb shell screencap -p /sdcard/screen.png";

    String PULL = "adb pull /sdcard/screen.png C:/ak-temp/temp.png";

    String TAP = "adb shell input tap";
}
