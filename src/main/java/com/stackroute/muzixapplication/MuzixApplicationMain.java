package com.stackroute.muzixapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MuzixApplicationMain {

	public static void main(String[] args) {
		SpringApplication.run(MuzixApplicationMain.class, args);
	}

}
