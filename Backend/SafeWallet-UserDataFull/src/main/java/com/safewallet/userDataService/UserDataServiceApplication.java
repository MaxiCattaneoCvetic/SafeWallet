package com.safewallet.userDataService;

import com.safewallet.userDataService.aliasCbu_generator.Cbu;
import com.safewallet.userDataService.repository.IUserRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableMongoRepositories
@EnableFeignClients
public class UserDataServiceApplication {

	@Autowired
	IUserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserDataServiceApplication.class, args);
	}

	@Bean
	public OpenAPI customApi() {
		return new OpenAPI()
				.info(new Info()
						.title("SafeWallet spring boot API")
						.version("1.0")
						.description("Microservice: UserDataFull")
						.termsOfService("-")
						.license(new License().name("#").url("-"))

				);
	}

}
