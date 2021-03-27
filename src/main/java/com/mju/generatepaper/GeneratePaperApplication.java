package com.mju.generatepaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mju.generatepaper")
public class GeneratePaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneratePaperApplication.class, args);
    }

}
