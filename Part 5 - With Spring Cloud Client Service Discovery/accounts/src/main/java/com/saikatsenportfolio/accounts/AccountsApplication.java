package com.saikatsenportfolio.accounts;

import com.saikatsenportfolio.accounts.dto.AccountsContactDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info=@Info(
				title = "Accounts Microservice API Documentation",
				description = "SpringBoot App Accounts Microservice API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Saikat Sen",
						email = "saikatsenproject@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		)
)
@EnableConfigurationProperties(value = {AccountsContactDto.class})
@EnableFeignClients
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
