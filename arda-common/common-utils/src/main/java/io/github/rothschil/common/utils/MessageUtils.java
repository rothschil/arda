package io.github.rothschil.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import io.github.rothschil.common.utils.bean.SpringUtils;

/** 获取i18n资源文件
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2019/10/9 - 21:22
 * @since 1.0.0
 */
public class MessageUtils {
    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     * @param code 消息键
     * @param args 参数
     * @return 获取国际化翻译值
     */
    public static String message(String code, Object... args) {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
