package com.dhlg.utils.common.uploadDown;

import com.dhlg.utils.common.Dictionaries;
import com.dhlg.utils.common.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 描述:上传文件
 * User:whfch
 * Date:2020/4/16
 * Time:19:37
 */
public class uploadFileUtils {


    public static String uploadImg(MultipartFile file) {
        //获取上传文件名,包含后缀
        String originalFilename = file.getOriginalFilename();
        //获取后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存的文件名
        String dFileName = UUID.randomUUID()+substring;
        //保存路径
        //springboot 默认情况下只能加载 resource文件夹下静态资源文件

        String path = Dictionaries.LOCALIMGSRC;

        //生成保存文件
        File uploadFile = new File(path+dFileName);
        //将上传文件保存到路径
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Dictionaries.IMGSRC+dFileName;
    }
    public static boolean delete(String url) {
        if (!StringUtils.isBlank(url)){
            String trueLocalImgSrc=url.replace(Dictionaries.IMGSRC,Dictionaries.LOCALIMGSRC);
            File file = new File(trueLocalImgSrc);
            try {
                file.delete();
            }catch (Exception e){
                return false;
            }
            return true;
        }
        return false;
    }

    //base64转换成图片
    public static String uploadImgByHead(String url) {

        String str = "data:image/png;base64,";
        String replaceStr = url.toString().replace(str, "");
        //保存的文件名
        String dFileName = UUID.randomUUID().toString();
        //保存路径
        String path = Dictionaries.LOCALIMGSRC;
        //保存路径全称
        String allPath=path+dFileName+".jpg";
        if (GenerateImage(replaceStr,allPath)){
            return allPath;
        }
        return "-1";
    }

    //base64字符串转化成图片
    private static boolean GenerateImage(String imgStr,String url) {
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpg图片
            OutputStream out = new FileOutputStream(url);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
