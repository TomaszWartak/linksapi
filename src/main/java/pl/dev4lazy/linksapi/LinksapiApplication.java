package pl.dev4lazy.linksapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LinksapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinksapiApplication.class, args);
    }

   /* @Bean
    ShortlinkValueGenerator shortlinkValueGenerator() {
        return new ShortlinkValueGenerator();
    }

    */
}
