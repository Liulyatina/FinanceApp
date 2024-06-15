package by.it_academy.finance_management_user;

import by.it_academy.finance_management_user.config.properties.JWTProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "by.it_academy.finance_management_user.dao.api")
@EnableScheduling
@EnableWebMvc
@EnableConfigurationProperties({JWTProperty.class})
@EnableFeignClients(basePackages = "by.it_academy.finance_management_user.service.feign.api")
public class FinanceManagementUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinanceManagementUserApplication.class, args);
    }
}