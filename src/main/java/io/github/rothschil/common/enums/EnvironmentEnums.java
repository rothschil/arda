package io.github.rothschil.common.enums;


import io.github.rothschil.common.constant.Constant;

/**
 *
 *
 * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @version 1.0.0
 */
public enum EnvironmentEnums {

    /**
     * 主环境
     */
    MASTER(Constant.IPADDRESS_MASTR),

    /**
     * 备用环境
     */
    SLAVE(Constant.IPADDRESS_SLAVE);

    private String code;

    EnvironmentEnums(String code) {
        this.code = code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
