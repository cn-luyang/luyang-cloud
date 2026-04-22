package io.github.luyang.platform.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * @author yang.lu
 */
/*@EnableDubbo*/
@SpringBootApplication
@ConfigurationPropertiesScan("io.github.luyang.platform.uaa.common.properties")
public class UaaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UaaApplication.class, args);
	}
}

