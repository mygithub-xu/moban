package com.dhlg.utils.common;

import cn.hutool.core.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


/**
 * 文件工具类
 * compressToZip 压缩
 *
 * @date 2021/8/10 10:29
 * @author xu
 * @return
 */
@Slf4j
public class FileUtils {

    /*
    * 删除指定文件
    *
    */
    public static void delZipFile(String url){
        File file=new File(url);
        if (file.getName().endsWith(".zip")) {  // zip文件  判断 是否存在
            if(file.delete()) {
                log.info("zip文件已经删除");
            }else{
                log.info("zip文件删除失败");
            }
        }
    }

    /**
     *
     * @param file 文件
     * @date 2021/8/10 10:38
     * @author xu
     * @return void
     */
    public static void downFile(File file){
        HttpServletResponse response = HttpContextUtils.getResponse();
        response.setContentType("application/force-download");
        response.setCharacterEncoding("UTF-8");
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(com.alibaba.excel.util.FileUtils.readFileToByteArray(file));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param fileName 文件名
     * @date 2021/8/10 10:38
     * @author xu
     * @return void
     */
    public static void creatFile(String fileName){
        File dir = new File(fileName);
        // 判断目录是否存在
        if (!dir.exists()) {
            // 生成单层目录
//            dir.mkdir();

            // 生成多层目录
            dir.mkdirs();
        }
    }
    /**
     * 压缩文件
     *
     * @param sourceFilePath 源文件路径
     * @param zipFilePath    压缩后文件存储路径
     * @param zipFilename    压缩文件名
     */
    public static void compressToZip(String sourceFilePath, String zipFilePath, String zipFilename) {
        File sourceFile = new File(sourceFilePath);
        File zipPath = new File(zipFilePath);
        if (zipPath.exists()) {
            log.info("删除");
            zipPath.delete();
        }
        zipPath.mkdirs();
        File zipFile = new File(zipPath + File.separator + zipFilename);
        try (
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            writeZip(sourceFile, "", zos);
            //文件压缩完成后，删除被压缩文件
            boolean flag = deleteDir(sourceFile);
            log.info("删除被压缩文件[" + sourceFile + "]标志：{}", flag);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    /**
     * 遍历所有文件，压缩
     *
     * @param file       源文件目录
     * @param parentPath 压缩文件目录
     * @param zos        文件流
     */
    public static void writeZip(File file, String parentPath, ZipOutputStream zos) {
        if (file.isDirectory()) {
            //目录
            parentPath += file.getName() + File.separator;
            File[] files = file.listFiles();
            for (File f : files) {
                writeZip(f, parentPath, zos);
            }
        } else {
            //文件
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                //指定zip文件夹
                ZipEntry zipEntry = new ZipEntry(parentPath + file.getName());
                zos.putNextEntry(zipEntry);
                int len;
                byte[] buffer = new byte[1024 * 10];
                while ((len = bis.read(buffer, 0, buffer.length)) != -1) {
                    zos.write(buffer, 0, len);
                    zos.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage(), e.getCause());
            }
        }
    }

    /**
     * 删除文件夹
     *
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        //删除空文件夹
        return dir.delete();
    }

    public static void unZip(File srcFile, String destDirPath) throws RuntimeException {
        long start = System.currentTimeMillis();
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println("解压" + entry.getName());
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if(!targetFile.getParentFile().exists()){
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("解压完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        } finally {
            if(zipFile != null){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void mainfun(){
        String fileSrc = "D:/aaaaaa";
        String storeSrc = "D:/bbbb";
        String zipName = "mytest05.zip";
        compressToZip(fileSrc,storeSrc,zipName);
        File file = new File(storeSrc + "/" + zipName);
        unZip(file,storeSrc);
    }


    @Test
    public void cccccc(){
//        String fileSrc = "D:/aaaaaa";
//        String storeSrc = "D:/bbbb";
//        String zipName = "mytest05.zip";
//        compressToZip(fileSrc,storeSrc,zipName);
//        ZipUtil.zip("d:/aaaaaa", "d:/bbbb/aaa.zip");
        File unzip = ZipUtil.unzip("D:\\bbbb\\1.tgz", "D:\\aaaaaa");
//        File file = new File(storeSrc + "/" + zipName);
//        unZip(file,storeSrc);
    }
}