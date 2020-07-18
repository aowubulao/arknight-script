import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

import static com.neoniou.ak.ArkScript.cutImage;

/**
 * @author Neo.Zzj
 * @date 2020/6/2
 */
public class Tess4jTest {

    public void test1() {
        // 识别图片的路径（修改为自己的图片路径）
        String path = "C:\\ak-temp\\temp_cut.png";

        // 语言库位置（修改为跟自己语言库文件夹的路径）
        String languagePath = "E:\\program\\tessdata\\tessdata_best";

        File file = new File(path);
        ITesseract instance = new Tesseract();

        //设置训练库的位置
        instance.setDatapath(languagePath);

        cutImage(1255,24, 170, 45);
        //cutImage(1329,762, 50, 32);
        //chi_sim ：简体中文， eng    根据需求选择语言库
        instance.setLanguage("ita");
        String result = null;
        try {
            long startTime = System.currentTimeMillis();
            result =  instance.doOCR(file);
            long endTime = System.currentTimeMillis();
            System.out.println("Time is：" + (endTime - startTime) + " 毫秒");
        } catch (TesseractException e) {
            e.printStackTrace();
        }

        System.out.println("result: ");
        System.out.println(result);
    }

    public void test2() {
        String s = "6";
        System.out.println(Integer.parseInt(s));
    }
}
