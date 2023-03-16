package com.example.demo.web;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * ClassName: testController
 * Author: wangxiaokang
 * Date: 3/14/2023 8:37 AM
 * History:
 * <author>       <time>       <version>       <desc>
 * wangxiaokang   修改时间       1.0.0          备注信息
 */
public class testController {
    public static void main(String[] args) {
        try {
            // 读取原始图片和水印图片
            BufferedImage originalImage = ImageIO.read(new File("C:/Users/admin/Desktop/人工智能2.png"));
            BufferedImage watermarkImage = ImageIO.read(new File("C:/Users/admin/Desktop/水印测试2.png"));

            // 创建Graphics2D对象，用于绘制水印图片
            Graphics2D g2d = originalImage.createGraphics();

            // 设置水印图片的透明度和合成规则
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
            g2d.setComposite(alphaChannel);

            // 将水印图片绘制到原始图片上
            int watermarkWidth = watermarkImage.getWidth();
            int watermarkHeight = watermarkImage.getHeight();
            int x = (originalImage.getWidth() - watermarkWidth) / 2;
            int y = (originalImage.getHeight() - watermarkHeight) / 2;
            g2d.drawImage(watermarkImage, x, y, watermarkWidth, watermarkHeight, null);

            // 保存带有水印的图片
            ImageIO.write(originalImage, "png", new File("output.png"));

            // 释放资源
            g2d.dispose();

            System.out.println("水印添加成功！");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
