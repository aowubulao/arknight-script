package com.neoniou.ak;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import com.neoniou.ak.util.CommonUtil;
import com.neoniou.ak.util.ImageUtil;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Neo.Zzj
 * @date 2020/5/29
 */
public class ArkScript {

    private static final String IMG_PATH = "C:/ak-temp/";
    private static final String VM_SIZE = "1440x810";
    private static final String IMG_NAME = "temp.png";
    private static final String CUT_IMG_NAME = "temp_cut.png";

    public static void main(String[] args) throws Exception {

        System.out.println("-------- Enter the basic information --------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Current physical: ");
        int curPhysical = scanner.nextInt();
        System.out.println("Spend physical: ");
        int spendPhysical = scanner.nextInt();

        // Load sample image url
        URL sample = Thread.currentThread().getContextClassLoader().getResource("sample.png");
        connectAndCheck();

        int times = 0;
        int sleepTime = 70;

        System.out.println("-------- Scripts Start --------");

        while (true) {
            times++;
            System.out.println("--- times: " + times + " ---");
            long startTime = System.currentTimeMillis();

            if (curPhysical < spendPhysical) {
                System.out.println("Stop program...");
                return;
            }
            CommonUtil.executeCmdWithDelay(CommonUtil.buildTapXY(1200, 1300, 700, 800), 3);
            CommonUtil.executeCmdWithDelay(CommonUtil.buildTapXY(1200, 1280, 450, 700), sleepTime - 22);
            while (true) {
                CommonUtil.screenshot(4);
                cutImage(580,614, 80, 80);

                assert sample != null;
                double compare = ImageUtil.compare(IMG_PATH + CUT_IMG_NAME, sample.toString().substring(6));
                if (compare > 0.7) {
                    break;
                }
            }
            CommonUtil.executeCmdWithDelay(CommonUtil.buildTapXY(100,1000, 100,400), 3);

            long endTime = System.currentTimeMillis();
            int spendTime = (int) ((endTime - startTime) / 1000);
            System.out.println("Spend time: " + spendTime + "s");
            if (times == 1) {
                sleepTime = spendTime;
            }
            curPhysical -= spendPhysical;
        }
    }

    public static void cutImage(int x, int y, int width, int height) {
        ImgUtil.cut(
                FileUtil.file(IMG_PATH + IMG_NAME),
                FileUtil.file(IMG_PATH + CUT_IMG_NAME),
                new Rectangle(x,y, width, height)
        );
    }

    private static void connectAndCheck() throws Exception {
        File file = new File(IMG_PATH);
        if (!file.exists()) {
            if (!file.mkdir()) {
                throw new Exception("Create directory fail!");
            }
        }
        CommonUtil.executeCmdWithPrint(AdbConstant.CONNECT);
        String vmSize = CommonUtil.executeCmdWithPrint(AdbConstant.GET_VM_SIZE);
        if (VM_SIZE.equals(vmSize.substring(0, vmSize.indexOf(": ")))) {
            throw new Exception("Vm size is wrong!");
        }
    }
}
