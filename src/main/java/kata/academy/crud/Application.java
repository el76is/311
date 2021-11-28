package kata.academy.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@EnableJpaRepositories(basePackages = "kata.academy.crud.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "kata.academy.crud.entities")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
