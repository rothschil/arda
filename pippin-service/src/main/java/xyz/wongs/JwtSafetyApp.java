package xyz.wongs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date
 * @Version 1.0.0
 */
@SpringBootApplication
public class JwtSafetyApp {
    static final Logger LOGGER = LoggerFactory.getLogger(JwtSafetyApp.class);
    public static void main(String[] args) {
        try {
            SpringApplication.run(JwtSafetyApp.class, args);
        } catch (Throwable e) {
            LOGGER.error("System error", e);
        }
    }
}
