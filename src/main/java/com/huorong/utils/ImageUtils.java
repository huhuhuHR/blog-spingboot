package com.huorong.utils;

import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Id;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * Created by huorong on 18/1/30.
 */
public class ImageUtils {
    public static String GenerateImage(String base64Image, String prefix, String suffix) {
        if (StringUtils.isEmpty(base64Image)) {
            return "";
        }
        String imageName = "";
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            byte[] b = decoder.decodeBuffer(base64Image);
            for (int i = 0; i < b.length; i++) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            imageName = String.valueOf(Id.next());
            String imgFilePath = prefix + imageName + suffix;
            out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return imageName + suffix;
    }

    public static String GetImageBase64(String imageName, String prefix, String suffix) {
        String fileName = prefix + imageName + suffix;
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(fileName);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}
