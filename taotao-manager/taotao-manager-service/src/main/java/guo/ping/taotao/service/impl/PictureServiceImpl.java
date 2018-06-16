package guo.ping.taotao.service.impl;

import guo.ping.taotao.common.pojo.PictureResult;
import guo.ping.taotao.common.utils.FastDFSClient;
import guo.ping.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureServiceImpl implements PictureService {

    @Value("${IMAGE_SERVER_BASE_URL}")
    private String IMAGE_SERVER_BASE_URL;

    @Override
    public PictureResult upload(MultipartFile picFile) {

        PictureResult pictureResult = new PictureResult();

        if (picFile.isEmpty()) {
            pictureResult.setError(1);
            pictureResult.setMessage("图片为空");
            return pictureResult;
        }

        try {
            // 截取扩展名
            String originalFilename = picFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            // 上传
            FastDFSClient client = new FastDFSClient("classpath:properties/client.conf");
            String url = client.uploadFile(picFile.getBytes(), extName);
            // 拼接url 返回数据
            pictureResult.setUrl(IMAGE_SERVER_BASE_URL + url);
            pictureResult.setError(0);

        } catch (Exception e) {
            e.printStackTrace();
            pictureResult.setError(1);
            pictureResult.setMessage("图片上传失败");
        }

        return pictureResult;
    }
}
