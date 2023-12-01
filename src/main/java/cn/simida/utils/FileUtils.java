package cn.simida.utils;

import com.upyun.RestManager;
import com.upyun.UpException;
import com.upyun.UpYunUtils;
import okhttp3.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FileUtils {
    public static Result uploadToUpYun(MultipartFile file, String userId, Integer operationCode) throws IOException, UpException {
        System.out.println("图片>>>>>>>>>>>>>>>>>>>>" + file);
        System.out.println("用户id>>>>>>>>>>>>>>>>>>>>" + userId);

        if (userId == null) {
            userId = "publicAvatar";
        }
        String uploadFilePath = null;
        switch (operationCode) {
            case 1: // 头像
                uploadFilePath = "avatar/" + userId + "/";
                break;
            case 2: // 动态
                uploadFilePath = "feed/" + userId + "/";
                break;
            case 3: // 背景
                uploadFilePath = "userCenter/" + userId + "/";
                break;
        }
        String date = TimeUtils.getDate("yyyy_MM_dd");
        uploadFilePath += date + "/";

        System.out.println("上传路径>>>>>>>>>>>>>>>>>>>>" + uploadFilePath);
        // 当前时间戳
        long currentTimeMillis = System.currentTimeMillis();
        uploadFilePath += currentTimeMillis;

        // 获取文件拓展名
        String fileExt = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        System.out.println("文件拓展名>>>>>>>>>>>>>>>>>>>>" + fileExt);

        // /develop/simida/分类（头像、动态）/用户id/日期（2023_11_1）/时间戳+文件拓展名
        // uploadFilePath格式：avatar/1001/2023_11_1/
        String filePath = "/develop/simida/" + uploadFilePath + "." + fileExt;


        RestManager manager = new RestManager("cikian", "cikian", "D1rD3YcbZgkluT7A8SkTYAow6DE2Y3aY");

        Map<String, String> params = new HashMap<>();
        params.put(RestManager.PARAMS.CONTENT_MD5.getValue(), UpYunUtils.md5(file.getBytes()));


        try (Response result = manager.writeFile(filePath, file.getBytes(), params)) {
            if (result.isSuccessful()) {
                System.out.println("上传成功图片路径：" + "https://img-upyun.cikian.cn/" + "." + filePath);
                HashMap<String, String> map = new HashMap<>();
                map.put("filePath", "https://img-upyun.cikian.cn/" + filePath);
                return new Result(ErrorCode.COMMON_SUCCESS, map, "上传成功");
            }
        }
        return new Result(ErrorCode.COMMON_FAIL, null, "上传失败");
    }
}
