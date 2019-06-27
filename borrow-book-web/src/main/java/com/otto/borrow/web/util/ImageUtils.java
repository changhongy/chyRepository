/**
 * Project Name：jfshop
 * File Name：ImageUtils.java
 * Package Name：com.centrin.common.utils
 * Date：2016-10-8 上午11:43:26
 * Copyright (c) 2016, Centrin Data Systems Ltd. All Rights Reserved.
 * 中金数据系统有限公司
 */
/**
 *
 */
package com.otto.borrow.web.util;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Title：
 * @Description：
 * @Package com.centrin.common.utils
 * @ClassName ImageUtils
 * @author john.he
 * @date 2016-10-8 上午11:43:26
 * @version
 */

public class ImageUtils {


    /***
     * 按指定的比例缩放图片
     *
     * @param sourceImagePath
     *            源地址
     * @param destinationPath
     *            改变大小后图片的地址
     * @param scale
     *            缩放比例，如1.2
     */
    public static void scaleImage(String sourceImagePath,
                                  String destinationPath, float scale, String format) {
        try {
            Thumbnails.of(sourceImagePath)
                    .scale(scale)
                    .outputFormat(format)
                    .toFile(destinationPath);
        } catch (IOException e) {
            System.out.println("scaleImage(scale)方法缩放图片时出错了");
            e.printStackTrace();
        }


    }

    /***
     * 将图片缩放到指定的高度或者宽度
     *
     * @param sourceImagePath
     *            图片源地址
     * @param destinationPath
     *            压缩完图片的地址
     * @param width
     *            缩放后的宽度
     * @param height
     *            缩放后的高度
     * @param format
     *            图片格式 例如 jpg
     */
    public static void scaleImage(String sourceImagePath,
                                  String destinationPath, int width, int height, String format) {

        try {
            Thumbnails.of(sourceImagePath)
                    .forceSize(width, height)
                    .outputFormat(format.toLowerCase())
                    .toFile(destinationPath);
        } catch (IOException e) {
            System.out.println("scaleImage(w,h)方法缩放图片时出错了");
            e.printStackTrace();
        }

    }


    /***
     * 将图片缩放到指定的高度或者宽度
     *
     * @param sourceImageBytes
     *            图片源二进制
     * @param destinationPath
     *            压缩完图片的地址
     * @param width
     *            缩放后的宽度
     * @param height
     *            缩放后的高度
     * @param format
     *            图片格式 例如 jpg
     */
    public static void scaleImage(byte[] sourceImageBytes,
                                  String destinationPath, Integer width, Integer height, String format) {
        InputStream in = null;
        InputStream ins = null;
        try {
            in = new ByteArrayInputStream(sourceImageBytes);
            ins = new ByteArrayInputStream(sourceImageBytes);
            BufferedImage image = ImageIO.read(ins);
            Integer imageWidth = image.getWidth();
            Integer imageHeight = image.getHeight();
            if (width != null && height == null) {
                height = (int) ((imageHeight.floatValue() / imageWidth.floatValue()) * width);
            } else if (width == null && height != null) {
                width = (int) ((imageWidth.floatValue() / imageHeight.floatValue()) * height);
            }

            if (width != null && height != null) {
                Thumbnails.of(in)
                        .forceSize(width.intValue(), height.intValue())
                        .outputFormat(format.toLowerCase())
                        .toFile(destinationPath);
            }


        } catch (IOException e) {
            System.out.println("scaleImage(w,h)方法缩放图片时出错了");
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /***
     * 将图片按质量压缩为jpg格式图片
     *
     * @param sourceImagePath
     *            图片源地址
     * @param destinationPath
     *            压缩完图片的地址
     * @param quality
     *            图片质量
     */
    public static void compressImage(String sourceImagePath,
                                     String destinationPath, float quality) {
        try {
            Thumbnails.of(sourceImagePath)
                    .scale(1f)
                    .outputQuality(quality)
                    .outputFormat("jpg")
                    .toFile(destinationPath);
        } catch (IOException e) {
            System.out.println("compressImage方法压缩图片时出错了");
            e.printStackTrace();
        }
    }


    /**
     * @Title: main
     * @Description:
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String s = "d://1.png";
        String d = "d://2.png";
//		ImageUtils.compressImage(s, d, 0.5f);
//		ImageUtils.scaleImage(s, d, 0.5f, "jpg");
        ImageUtils.scaleImage(s, d, 300, 300, "png");
        System.out.println("ok");
    }
}
