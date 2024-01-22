package com.example.safewallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SafewalletApplicationKeycloak {

	public static void main(String[] args) {
		SpringApplication.run(SafewalletApplicationKeycloak.class, args);
		System.out.println("SpringBoot + Keycloack ON");
	}

}
