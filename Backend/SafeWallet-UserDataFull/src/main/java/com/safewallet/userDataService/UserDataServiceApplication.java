package com.safewallet.userDataService;

import com.safewallet.userDataService.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableMongoRepositories
public class UserDataServiceApplication {
	@Autowired
	IUserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserDataServiceApplication.class, args);
	}
}
