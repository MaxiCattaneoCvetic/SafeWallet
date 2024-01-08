package com.example.safewallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SafewalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafewalletApplication.class, args);
		System.out.println("SpringBoot + Keycloack ON");
	}

}
