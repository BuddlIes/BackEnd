package com.buddle.UserManager;

import com.buddle.UserManager.Entity.UserInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UserManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagerApplication.class, args);

	}

}
