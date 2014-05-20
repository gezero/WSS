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
 * The project should be buildable using the standard <em>maven package</em> command.
 * <p/>
 * To run the build project simply run  <em>java -jar target/wallstreetsystems-exam-0.0.1-SNAPSHOT.jar</em>
 * <p/>
 * The application takes from classpath file input-list.txt, there is list of files to handle. Each file will be handled
 * separately by the Batch Job. Currently the files from input/* will be handled. The output will be in the output/
 * directory. Currently the tests will also try to execute the job and therefore after mvn package the output directory
 * will contain already results.
 * <p/>
 * If you want to run some other file as an input, it has to be specified in the input-list.txt file. It is also
 * possible to specify custom list of input files using the input-file-url property. To check how to set this property,
 * see - Externalized configuration in the
 * <a href=http://docs.spring.io/spring-boot/docs/1.0.2.RELEASE/reference/htmlsingle/#boot-features-external-config>
 *     Spring Boot reference</a>. For instance check the Accessing command line properties chapter there.
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
