package io.github.rothschil.common.config.cache;

public interface CacheNames {

    /**
     * 演示案例
     */
    String DEMO_CACHE = "demo:cache#60s#10m#20";

    /**
     * 系统配置
     */
    String SYS_CONFIG = "sys_config";

    /**
     * 数据字典
     */
    String SYS_DICT = "sys_dict";

    /**
     * 租户
     */
    String SYS_TENANT = GlobalConstants.GLOBAL_REDIS_KEY + "sys_tenant#30d";

    /**
     * 用户账户
     */
    String SYS_USER_NAME = "sys_user_name#30d";

    /**
     * 用户名称
     */
    String SYS_NICKNAME = "sys_nickname#30d";

    /**
     * 部门
     */
    String SYS_DEPT = "sys_dept#30d";

    /**
     * OSS内容
     */
    String SYS_OSS = "sys_oss#30d";

    /**
     * OSS配置
     */
    String SYS_OSS_CONFIG = GlobalConstants.GLOBAL_REDIS_KEY + "sys_oss_config";

    /**
     * 在线用户
     */
    String ONLINE_TOKEN = "online_tokens";

}
