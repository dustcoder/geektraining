package com.ren.task02;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "st")
public class ConfigProperties {

    private Student student = new Student();

    @Data
    public static class Student {
        private int id;
        private String name;

        @Override
        public String toString() {
            return "Student{" +
                    "id " + id +
                    " name: " + name;
        }
    }
}
