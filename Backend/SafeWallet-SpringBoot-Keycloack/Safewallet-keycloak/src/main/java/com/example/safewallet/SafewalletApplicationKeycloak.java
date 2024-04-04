package com.example.safewallet;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SafewalletApplicationKeycloak {

	public static void main(String[] args) {
		SpringApplication.run(SafewalletApplicationKeycloak.class, args);
		System.out.println("SpringBoot + Keycloack ON");
	}

	@Bean
	public OpenAPI customApi() {
		return new OpenAPI()
				.info(new Info()
						.title("SafeWallet spring boot API")
						.version("1.0")
						.description("Microservice: Keycloak")
						.termsOfService("-")
						.license(new License().name("#").url("-"))

				);
	}



}
