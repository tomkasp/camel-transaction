package com.tomkasp.cameltransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CamelTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelTransactionApplication.class, args);
	}
}
