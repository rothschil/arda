package io.github.rothschil.common.utils;

import cn.hutool.core.util.RandomUtil;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用构建
 * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @version 1.0.0
 */
public class ToolUtils {


    // /**
    //  * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
    //  * @param cacheable 实例
    //  * @return String
    //  **/
    // public static String buildContent(Cacheable cacheable) {
    //     String content = cacheable.value();
    //     String pattern = "\\w+\\.\\w+";
    //     if(ToolUtils.operation(content, pattern)){
    //         return "{#"+content+"}";
    //     }
    //     return content;
    // }

    public static int getTTL(int ttl) {
        if(ttl>1){
            return ttl * 60 + RandomUtil.randomInt(0, 180);
        }
        return 100;
    }

    /**
     * 获取一个UUID值
     * @return UUID值[String]
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * //String pattern = "[^{\\w+\\S}$]";
     * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @param content   内容
     * @param pattern   格式
     * @return boolean
     **/
    public static boolean operation(String content,String pattern){

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(content);
        return m.matches();
    }
}
