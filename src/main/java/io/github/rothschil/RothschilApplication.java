package io.github.rothschil;

import io.github.rothschil.common.config.Version;
import io.github.rothschil.common.persistence.jpa.repository.factory.BaseRepositoryFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@MapperScan("io.github.rothschil.**.mapper")
@Slf4j
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"io.github.rothschil"},repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@SpringBootApplication
public class RothschilApplication {

	public static void main(String[] args) {
		SpringApplication app =new SpringApplication(RothschilApplication.class);
		Environment env = app.run(args).getEnvironment();
		Version.printlnVersionInfo(env);
	}

}
