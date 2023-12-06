package io.github.rothschil.client;

import cn.hutool.json.JSONUtil;
import io.github.rothschil.common.intf.IntfConfEntity;
import io.github.rothschil.common.utils.RestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

@Slf4j
public class WebClientTest {


    @Test
    public void wea() {
        String url = "http://t.weather.itboy.net/api/weather/city/101030100";
        IntfConfEntity intfConf = IntfTools.building(url);
        RespCase resp= RestUtils.get(intfConf,null,new HttpHeaders(),RespCase.class);
        log.warn("RespCase {}", JSONUtil.parse(resp));
    }
}
