package io.github.rothschil.client.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CityInfoDto {
    @JSONField(name = "city")
    private String city;
    @JSONField(name = "citykey")
    private String citykey;
    @JSONField(name = "parent")
    private String parent;
    @JSONField(name = "updateTime")
    private String updateTime;
}
