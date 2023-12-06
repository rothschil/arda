package io.github.rothschil.client;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.rothschil.client.resp.CityInfoDto;
import io.github.rothschil.client.resp.DataDto;
import io.github.rothschil.common.base.vo.BaseResp;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RespCase extends BaseResp {

    @JSONField(name = "message")
    private String message;
    @JSONField(name = "status")
    private Integer status;
    @JSONField(name = "date")
    private String date;
    @JSONField(name = "time")
    private String time;
    @JSONField(name = "cityInfo")
    private CityInfoDto cityInfo;
    @JSONField(name = "data")
    private DataDto data;
}
