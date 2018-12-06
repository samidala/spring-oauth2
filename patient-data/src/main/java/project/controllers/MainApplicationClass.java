package project.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("project")
@EntityScan("project")
@EnableJpaRepositories("project.repository")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class MainApplicationClass {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    public static void main(String args[]) {
        SpringApplication.run(MainApplicationClass.class, args);
    }
}
