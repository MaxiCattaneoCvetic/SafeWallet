package com.example.Transfers;

import com.example.Transfers.repository.ICardRepostitory;
import com.example.Transfers.repository.ITransferRepository;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableMongoRepositories
public class TransfersApplication {



	@Autowired
	ITransferRepository iTransferRepository;

	@Autowired
	ICardRepostitory iCardRepostitory;


	public static void main(String[] args) {
		SpringApplication.run(TransfersApplication.class, args);
	}


	@Bean
	public OpenAPI customApi() {
		return new OpenAPI()
				.info(new Info()
						.title("SafeWallet spring boot API")
						.version("1.0")
						.description("Microservice: Transfers")
						.termsOfService("https://webporfolio-gray.vercel.app/")
						.license(new License().name("#").url("https://webporfolio-gray.vercel.app/"))

				);
	}


}
