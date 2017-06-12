package com.idou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@EnableConfigurationProperties*/
@MapperScan("com.idou.dao")
public class JcyAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(JcyAdminApplication.class, args);
	}
}
