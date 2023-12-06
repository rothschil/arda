package io.github.rothschil.client.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class DataDto {
    @JSONField(name = "shidu")
    private String shidu;
    @JSONField(name = "pm25")
    private Integer pm25;
    @JSONField(name = "pm10")
    private Integer pm10;
    @JSONField(name = "quality")
    private String quality;
    @JSONField(name = "wendu")
    private String wendu;
    @JSONField(name = "ganmao")
    private String ganmao;
    @JSONField(name = "forecast")
    private List<ForecastDto> forecast;
    @JSONField(name = "yesterday")
    private YesterdayDto yesterday;
}
