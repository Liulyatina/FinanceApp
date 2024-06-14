package by.it_academy.finance_management_classifier;

import by.it_academy.finance_management_classifier.config.properties.JWTProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
@EnableJpaRepositories
@EnableScheduling
@EnableWebMvc
@EnableConfigurationProperties({JWTProperty.class})
@EnableFeignClients(basePackages = "by.it_academy.finance_management_classifier.service.feign.api")

public class FinanceManagementClassifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceManagementClassifierApplication.class, args);
	}

}
