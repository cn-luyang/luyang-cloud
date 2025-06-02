package io.github.luyang.platform.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yang.lu
 */
@EnableDiscoveryClient // 启用Nacos Discovery客户端功能

@SpringBootApplication
public class OpenApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenApplication.class, args);
	}
}
