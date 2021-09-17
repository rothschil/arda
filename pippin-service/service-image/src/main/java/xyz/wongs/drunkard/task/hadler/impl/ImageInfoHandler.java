package xyz.wongs.drunkard.task.hadler.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.moon.entity.ImageInfo;
import xyz.wongs.drunkard.moon.service.ImageInfoService;
import xyz.wongs.drunkard.task.hadler.IntfImageInfoHandler;

import java.util.List;

/** 异步处理图像信息接口
 * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @description //TODO
 * @github <a>https://github.com/rothschil</a>
 * @date 2021/9/17 - 15:41
 * @version 1.0.0
 */
@Data
@Component("imageInfoHandler")
public class ImageInfoHandler implements IntfImageInfoHandler {

    private List<ImageInfo> lists;

    @Autowired
    private ImageInfoService imageInfoService;

    public void processData(){
        imageInfoService.insert(lists);
    }
}
