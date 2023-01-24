package com.dc.asynctask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AsyncTaskApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncTaskApiApplication.class, args);
	}

}
