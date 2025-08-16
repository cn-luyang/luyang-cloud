package io.github.luyang.jenkins.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang.lu
 */
@RestController
public class TestController {

	@GetMapping("/test")
	public String test() {
		return "test success";
	}
}
