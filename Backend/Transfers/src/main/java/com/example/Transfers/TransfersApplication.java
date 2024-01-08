package com.example.Transfers;

import com.example.Transfers.repository.ITransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableMongoRepositories
public class TransfersApplication {



	@Autowired
	ITransferRepository iTransferRepository;


	public static void main(String[] args) {
		SpringApplication.run(TransfersApplication.class, args);
	}

}
