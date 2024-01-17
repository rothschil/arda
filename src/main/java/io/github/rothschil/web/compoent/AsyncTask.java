package io.github.rothschil.web.compoent;

import cn.hutool.json.JSONUtil;
import io.github.rothschil.common.base.vo.RequestHeaderVo;
import io.github.rothschil.common.utils.UserTransmittableUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class AsyncTask {

    @Async
    public void async(){
        RequestHeaderVo vo = (RequestHeaderVo) UserTransmittableUtils.get();
        log.info("async {}", JSONUtil.parse(vo));
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public void async2(){
        RequestHeaderVo vo = (RequestHeaderVo) UserTransmittableUtils.get();
        log.info("async2 {}", JSONUtil.parse(vo));
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public void async3(){
        RequestHeaderVo vo = (RequestHeaderVo) UserTransmittableUtils.get();
        log.info("async3 {}", JSONUtil.parse(vo));
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
