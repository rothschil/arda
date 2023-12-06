package io.github.rothschil.client.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ForecastDto {
    @JSONField(name = "date")
    private String date;
    @JSONField(name = "high")
    private String high;
    @JSONField(name = "low")
    private String low;
    @JSONField(name = "ymd")
    private String ymd;
    @JSONField(name = "week")
    private String week;
    @JSONField(name = "sunrise")
    private String sunrise;
    @JSONField(name = "sunset")
    private String sunset;
    @JSONField(name = "aqi")
    private Integer aqi;
    @JSONField(name = "fx")
    private String fx;
    @JSONField(name = "fl")
    private String fl;
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "notice")
    private String notice;
}
