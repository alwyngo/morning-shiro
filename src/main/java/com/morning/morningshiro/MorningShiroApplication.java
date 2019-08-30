package com.morning.morningshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan(basePackages = {"com.morning.morningshiro.dao.mapper"})
@ImportResource(locations = {"classpath:spring-*"})
public class MorningShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MorningShiroApplication.class, args);
	}

}