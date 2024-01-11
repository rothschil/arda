package io.github.rothschil.domain.vo;

import io.github.rothschil.common.base.vo.BaseResp;

/**
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @since 1.0.0
 */
public class PostRespVo extends BaseResp {

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
