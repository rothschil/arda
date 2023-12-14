package io.github.rothschil.client;

import io.github.rothschil.domain.mybatis.entity.IntfConfEntity;
import io.github.rothschil.common.utils.RestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import reactor.core.Disposable;

import java.util.concurrent.TimeUnit;

@Slf4j
public class WebClientTest {


    @Test
    public void wea() {
        String url = "http://t.weather.itboy.netf/api/weather/city/101030100";
        IntfConfEntity intfConf = IntfTools.building(url);
        // RespCase resp= RestUtils.getBySynch(intfConf,null,new HttpHeaders(),RespCase.class);
        Disposable disposable = RestUtils.getByAsynch(intfConf,null,new HttpHeaders(),RespCase.class);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
