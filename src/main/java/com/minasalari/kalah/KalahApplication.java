package com.minasalari.kalah;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "KalahGameAPI", version = "2.0.", description = "Kalah Game Information"))
public class KalahApplication {
    public static void main(String[] args) {
        SpringApplication.run(KalahApplication.class, args);

    }
}
