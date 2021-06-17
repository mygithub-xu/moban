package com.dhlg.utils.common;

import com.dhlg.utils.common.StringUtils;
import org.springframework.stereotype.Component;
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
@Component
public class uploadFileUtils {


    public static String uploadImg(MultipartFile file, String fileNetSrc, String fileDownSrc) {


        //获取上传文件名,包含后缀
        String originalFilename = file.getOriginalFilename();
        //获取后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存的文件名
        String dFileName = UUID.randomUUID()+substring;
        //保存路径
        //springboot 默认情况下只能加载 resource文件夹下静态资源文件

        String path = fileDownSrc;

        //生成保存文件
        File uploadFile = new File(path+dFileName);
        //将上传文件保存到路径
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileNetSrc+dFileName;
    }
    public static boolean delete(String url, String fileNetSrc, String fileDownSrc) {
        if (!StringUtils.isBlank(url)){
            String trueLocalImgSrc=url.replace(fileNetSrc,fileDownSrc);
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
    public static String uploadImgByHead(String url, String fileDownSrc) {

        String str = "data:image/png;base64,";
        String replaceStr = url.toString().replace(str, "");
        //保存的文件名
        String dFileName = UUID.randomUUID().toString();
        //保存路径
        String path = fileDownSrc;
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
        {
            return false;
        }
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
