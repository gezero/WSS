package cz.peinlich.exam;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This class is the starting point for Spring Boot. The @EnableAutoConfiguration annotation is the one that tells
 * Spring Boot to try and configure the project automatically. The class was generated using start.spring.io.
 * To find the actual working job check the {@link cz.peinlich.exam.color.job.JobConfiguration}
 * <p/>
 * User: George
 * Date: 20.5.2014
 * Time: 5:33
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableBatchProcessing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
