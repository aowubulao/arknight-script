package com.neoniou.ak.util;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.Collections;

/**
 * @author Neo.Zzj
 * @date 2020/5/29
 */
public class ImageUtil {

    private static final String PATH = "C:\\ak-temp\\temp_cut.png";

    private static final String LANGUAGE_PATH = "E:\\program\\tessdata\\tessdata_best";

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static double compare(String image1, String image2) {
        Mat mat1 = convertMat(image1);
        Mat mat2 = convertMat(image2);

        Mat hist1 = new Mat();
        Mat hist2 = new Mat();

        MatOfFloat ranges = new MatOfFloat(0f, 256f);
        MatOfInt histSize = new MatOfInt(1000);

        Imgproc.calcHist(Collections.singletonList(mat1), new MatOfInt(0), new Mat(), hist1, histSize, ranges);
        Imgproc.calcHist(Collections.singletonList(mat2), new MatOfInt(0), new Mat(), hist2, histSize, ranges);

        return Imgproc.compareHist(hist1, hist2, Imgproc.CV_COMP_CORREL);
    }

    private static Mat convertMat(String filename) {
        Mat image0 = Imgcodecs.imread(filename);
        Mat image = new Mat();

        Imgproc.cvtColor(image0, image, Imgproc.COLOR_BGR2GRAY);

        return image0;
    }
}
