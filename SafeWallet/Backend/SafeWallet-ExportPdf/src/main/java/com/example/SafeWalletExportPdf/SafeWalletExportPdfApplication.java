package com.example.SafeWalletExportPdf;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class SafeWalletExportPdfApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SafeWalletExportPdfApplication.class, args);


	}

	@Bean
	public OpenAPI customApi() {
		return new OpenAPI()
				.info(new Info()
						.title("SafeWallet spring boot API")
						.version("1.0")
						.description("Microservice: ExportPdf")
						.termsOfService("https://webporfolio-gray.vercel.app/")
						.license(new License().name("#").url("https://webporfolio-gray.vercel.app/"))

				);
	}


}
