package org.sed.systemmonitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "org.sed.systemmonitor.repository")
public class MongoConfig {

	@Bean
	public MongoClient mongo() throws Exception {

		return MongoClients.create(
				"mongodb://root:toor@cluster0-shard-00-00.bf5yj.mongodb.net:27017,cluster0-shard-00-01.bf5yj.mongodb.net:27017,cluster0-shard-00-02.bf5yj.mongodb.net:27017/test?ssl=true&replicaSet=atlas-y58u09-shard-0&authSource=admin&retryWrites=true&w=majority");

	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), "test");
	}

}
