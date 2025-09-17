package io.github.luyang.platform.uac;

import io.github.luyang.starter.security.remote.openfeign.RemoteTokenServiceApi;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yang.lu
 */
@EnableDubbo
@EnableFeignClients(clients = RemoteTokenServiceApi.class)
@SpringBootApplication
public class UacApplication {

	public static void main(String[] args) {
		SpringApplication.run(UacApplication.class, args);
	}
}
