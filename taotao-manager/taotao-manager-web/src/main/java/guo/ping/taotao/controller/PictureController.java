package guo.ping.taotao.controller;

import guo.ping.taotao.common.pojo.PictureResult;
import guo.ping.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/pic")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/upload")
    @ResponseBody
    public PictureResult uploadFile(MultipartFile uploadFile) {  // 参数必须是为uploadFile
        return pictureService.upload(uploadFile);
    }
}
