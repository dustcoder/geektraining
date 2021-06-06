package com.ren.task02;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = ConfigProperties.class)
public class AutoConfiguration {

    @Bean
    public Student student(ConfigProperties configProperties) {
        return new Student(configProperties.getStudent().getId(), configProperties.getStudent().getName());
    }

}
