package com.neoniou.ak.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RuntimeUtil;
import com.neoniou.ak.AdbConstant;

import java.util.Date;
import java.util.Random;

/**
 * @author Neo.Zzj
 * @date 2020/5/29
 */
public class CommonUtil {

    public static String executeCmd(String command) {
        return RuntimeUtil.execForStr(command);
    }

    public static String executeCmdWithDelay(String command, int delaySeconds) {
        String ret = RuntimeUtil.execForStr(command);
        CommonUtil.sleep(delaySeconds);
        return ret;
    }

    public static String executeCmdWithPrint(String command) {
        String res = executeCmd(command);
        System.out.println(new DateTime(new Date()) + ": " + res);
        return res;
    }

    public static int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max- min + 1) + min;
    }

    public static void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String buildTapXY(int minX, int maxX, int minY, int maxY) {
        return AdbConstant.TAP + " " + CommonUtil.getRandom(minX, maxX) + " " + CommonUtil.getRandom(minY, maxY);
    }

    public static void screenshot(int delaySeconds) {
        CommonUtil.executeCmd(AdbConstant.SCREEN_SHOT);
        CommonUtil.executeCmd(AdbConstant.PULL);
        CommonUtil.sleep(delaySeconds);
    }
}
