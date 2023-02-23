package com.digital.coffeeorderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class CoffeeOrderAppApplication {

	public static final String API_V = "api/v1";

	public static void main(String[] args) {
		SpringApplication.run(CoffeeOrderAppApplication.class, args);
	}

}
