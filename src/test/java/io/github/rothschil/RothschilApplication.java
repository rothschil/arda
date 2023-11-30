package io.github.rothschil;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan("io.github.rothschil.**.mapper")
@Slf4j
@SpringBootTest
public class RothschilApplication {

	@Test
	void contextLoads() {
	}

}
