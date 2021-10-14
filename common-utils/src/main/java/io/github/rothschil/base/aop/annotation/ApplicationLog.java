package io.github.rothschil.base.aop.annotation;

import io.github.rothschil.common.enums.BusinessType;
import io.github.rothschil.common.enums.OperatorType;

import java.lang.annotation.*;

/**
 * 切面的应用日志
 *
 * @author WCNGS@QQ.COM
 * @date 2019/10/29 16:50
 * @since 1.0.0
 */
@SuppressWarnings("unused")
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ApplicationLog {

    /**
     * 被修改的实体的唯一标识,例如:用户实体的唯一标识为"id"
     */
    String key() default "id";

    /**
     * 业务操作名称,例如:"修改用户、修改菜单"
     */
    String value() default "";


    /**
     * 模块
     */
    String title() default "";

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;


    /**
     * 功能
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

}
