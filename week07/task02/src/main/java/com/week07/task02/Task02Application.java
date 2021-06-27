package com.week07.task02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.week07.task02.Mapper"})
public class Task02Application {

	public static void main(String[] args) {
		SpringApplication.run(Task02Application.class, args);
	}

}
