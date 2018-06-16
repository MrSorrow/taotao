package guo.ping.taotao.service;

import guo.ping.taotao.common.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片文件上传
 */
public interface PictureService {

    PictureResult upload(MultipartFile picFile);
}
