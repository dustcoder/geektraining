package com.week08.task01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.week08.task01.mapper")
public class Task01Application {

	public static void main(String[] args) {
		SpringApplication.run(Task01Application.class, args);
	}

}
