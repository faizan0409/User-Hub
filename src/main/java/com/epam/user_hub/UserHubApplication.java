package com.epam.user_hub;

import com.epam.user_hub.config.RsaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaConfig.class)
public class UserHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserHubApplication.class, args);
	}

}
