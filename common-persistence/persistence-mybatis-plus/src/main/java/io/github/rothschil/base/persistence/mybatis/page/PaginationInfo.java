package io.github.rothschil.base.persistence.mybatis.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * @Description
 * @author WCNGS@QQ.COM
 * 
 * @date 2017/12/2 13:48
 * @since 1.0.0
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@PropertySource("classpath:config.properties")
@ConfigurationProperties(prefix = "page")
public class PaginationInfo {

    private int pageNum;

    private int pageSize;

}
