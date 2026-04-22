package io.github.luyang.platform.uac;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yang.lu
 */
@EnableDubbo
@SpringBootApplication
public class UacApplication {

	public static void main(String[] args) {
		SpringApplication.run(UacApplication.class, args);
	}
}
