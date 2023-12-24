package com.xpertcaller.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication
public class ConsultantApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConsultantApplication.class, args);
	}

}
