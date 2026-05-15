package com.wallet.api;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;


/**
 * 給图片添加文字水印
 *
 * @author liqiang
 *
 */
public class WaterMarkUtils {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 原图位置, 输出图片位置, 水印文字颜色, 水印文字
        new WaterMarkUtils().mark("http://file.weirui0755.com/weirui/template/39F86290BCE54D4490139128E744DC59.png",
                "C:/Users/83888/Desktop/4444.jpg", "99.55545","MBC010001111111","#53389E");
    }

    /**
     * 图片添加水印
     *
     * @param srcImgPath
     *            需要添加水印的图片的路径
     * @param outImgPath
     *            添加水印后图片输出路径
     *            水印文字的颜色
     * @param waterMarkContent
     *            水印的文字
     */
    public void mark(String srcImgPath, String outImgPath, String waterMarkContent,String footContent,String colour) {

        BigDecimal bigDecimal = new BigDecimal(waterMarkContent);
        //判断价格是整数还是小数
        if(!(new BigDecimal(bigDecimal.intValue()).compareTo(bigDecimal)==0)){
            Double  ba= new BigDecimal(waterMarkContent).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
            waterMarkContent=ba.toString();
        }
        String coin="USDM";
        try {
            URL url = null;
            InputStream in = null;
            try
            {
                url = new URL(srcImgPath);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();// 利用HttpURLConnection对象,我们可以从网络中获取网页数据.
                conn.setDoInput(true);
                conn.connect();
                in = conn.getInputStream(); // 得到网络返回的输入流
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }

            // 读取原图片信息
           // File srcImgFile = new File(srcImgPath);
            Image srcImg = ImageIO.read(in);
            int srcImgWidth = srcImg.getWidth(null);
            int srcImgHeight = srcImg.getHeight(null);
            // 加底部水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            Font font = new Font("Lato", Font.BOLD, 60);
            Font tokenId = new Font("Lato", Font.CENTER_BASELINE, 25);
            Font youTokenId = new Font("Lato", Font.CENTER_BASELINE, 20);
            Font ss = new Font("Lato", Font.PLAIN, 40);
            Font zprice = new Font("Lato", Font.BOLD, 94);
            Font pri = new Font("Arial", Font.PLAIN, 20);
           // Font font = new Font("宋体", Font.PLAIN, 100);
            g.setColor(Color.decode(colour)); // 根据图片的背景设置水印颜色
            g.setFont(font);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);


            int   x = srcImgWidth - getWatermarkLength(waterMarkContent, g) -675;
            int  y = srcImgHeight - 52;


            // int x = (srcImgWidth - getWatermarkLength(watermarkStr, g)) / 2;
            // int y = srcImgHeight / 2;ITALIC
            g.drawString(waterMarkContent, x, y);
            g.dispose();

            //加TokenId
            Graphics2D p = bufImg.createGraphics();
            p.setColor(Color.decode("#42307D"));
            p.setFont(tokenId);
            p.setRenderingHint(RenderingHints.KEY_ANTIALIASING,

                    RenderingHints.VALUE_ANTIALIAS_ON);
            int a = srcImgWidth - getWatermarkLength(footContent, p) -650;
            int b = srcImgHeight - 436;
            // int x = (srcImgWidth - getWatermarkLength(watermarkStr, g)) / 2;
            // int y = srcImgHeight / 2;
            p.drawString(footContent.replace("", " ").trim(), a, b);
            p.dispose();


            //加右边中间TokenId
            Graphics2D token = bufImg.createGraphics();
            token.setColor(Color.decode("#323750"));
            token.setFont(youTokenId);
            token.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            int lll = srcImgWidth - getWatermarkLength(footContent, token) -205;
            int kk = srcImgHeight +40;
            token.rotate(Math.toRadians(90),(double)bufImg.getWidth(),(double)bufImg.getHeight());
            // int x = (srcImgWidth - getWatermarkLength(watermarkStr, g)) / 2;
            // int y = srcImgHeight / 2;

            token.drawString(footContent.replace("", " ").trim(), lll, kk);
            token.dispose();


            //加左下方币种名称
            Graphics2D pp = bufImg.createGraphics();
            pp.setColor(Color.decode("#563AA8")); // 根据图片的背景设置水印颜色
            pp.setFont(ss);
            pp.setRenderingHint(RenderingHints.KEY_ANTIALIASING,

                    RenderingHints.VALUE_ANTIALIAS_ON);
            int aa = srcImgWidth - getWatermarkLength(coin,pp) -555;
            int bb = srcImgHeight - 50;
            // int x = (srcImgWidth - getWatermarkLength(watermarkStr, g)) / 2;
            // int y = srcImgHeight / 2;
            pp.drawString(coin, aa, bb);
            pp.dispose();



            //加中间价格
            Graphics2D dd = bufImg.createGraphics();
            dd.setColor(Color.decode("#290F71")); // 根据图片的背景设置水印颜色
            dd.setFont(zprice);
            dd.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Double d= Double.parseDouble(waterMarkContent);
            int ddd=0;
            int ccc=0;
            if(d<1000&&d<=99&&d>9){
                ddd = srcImgWidth - getWatermarkLength(waterMarkContent,dd) -130;
                ccc = srcImgHeight - 260;
            }
            if(d<10){
                ddd = srcImgWidth - getWatermarkLength(waterMarkContent,dd) -150;
                ccc = srcImgHeight - 260;
            }
            if(d<1000&&d>99){
                ddd = srcImgWidth - getWatermarkLength(waterMarkContent,dd) -115;
                ccc = srcImgHeight - 260;
            }
            if(d>999){
                ddd = srcImgWidth - getWatermarkLength(waterMarkContent,dd) -95;
                ccc = srcImgHeight - 260;
            }


            // int x = (srcImgWidth - getWatermarkLength(watermarkStr, g)) / 2;
            // int y = srcImgHeight / 2;
            dd.drawString(waterMarkContent, ddd, ccc);
            dd.dispose();



            //加中间名称
            Graphics2D qew = bufImg.createGraphics();
            qew.setColor(Color.decode("#323750")); // 根据图片的背景设置水印颜色
            qew.setFont(pri);
            qew.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            int qqq = srcImgWidth - getWatermarkLength(coin,qew)- 170;
            int  www = srcImgHeight - 217;
            // int x = (srcImgWidth - getWatermarkLength(watermarkStr, g)) / 2;
            // int y = srcImgHeight / 2;
            qew.drawString("MBC"+"  "+coin, qqq, www);
            qew.dispose();

            //加中间价格
            Graphics2D ttt = bufImg.createGraphics();
            ttt.setColor(Color.decode("#323750")); // 根据图片的背景设置水印颜色
            ttt.setFont(pri);
            ttt.setRenderingHint(RenderingHints.KEY_ANTIALIASING,

                    RenderingHints.VALUE_ANTIALIAS_ON);
            int bbb = srcImgWidth - getWatermarkLength(waterMarkContent,ttt) -238;
            int  vvv = srcImgHeight - 217;
            // int x = (srcImgWidth - getWatermarkLength(watermarkStr, g)) / 2;
            // int y = srcImgHeight / 2;
            ttt.drawString(waterMarkContent, bbb, vvv);
            ttt.dispose();






            Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");
            if (iter.hasNext()) {
                ImageWriter writer = iter.next();
                ImageWriteParam param = writer.getDefaultWriteParam();
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(1.0f); //最高质量
                File file=new File(outImgPath);
                FileImageOutputStream out = new FileImageOutputStream(file);
                writer.setOutput(out);
                // writer.write(bi);
                writer.write(null, new IIOImage((BufferedImage) bufImg, null, null), param);
                out.close();
                writer.dispose();
            }





        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将BufferedImage转换为InputStream
     * @param image
     * @return
     */
    public InputStream bufferedImageToInputStream(BufferedImage image){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", os);
            InputStream input = new ByteArrayInputStream(os.toByteArray());
            return input;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取水印文字总长度
     *
     * @param waterMarkContent
     *            水印的文字
     * @param g
     * @return 水印文字总长度
     */
    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }



}