package io.renren.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {
    public static String saveRecordPicFile(MultipartFile file,String stuId, int type)
                                           throws Exception{
//        char s = File.separatorChar;
        char s = '/';
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf(".")+1);

        //以时间命名图片名
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String time = ft.format(new Date());
        String newFileName = time+"."+fileType;

        //相对地址
        String typeName = RecordUtils.map.get(type);
        String relativePath = "images"+s+typeName+s+stuId+s;

        InputStream inputStream = file.getInputStream();
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);

        //生成文件夹 static/image/static/images/type/stuId/
        //绝对地址
        String path = System.getProperty("user.dir")+s+"static"+s+relativePath;
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //存文件
        File f = new File(path+newFileName);
        OutputStream outputStream = new FileOutputStream(f);
        outputStream.write(buffer);
        outputStream.close();
        //只返回相对路径
        return relativePath+newFileName;
    }
}
