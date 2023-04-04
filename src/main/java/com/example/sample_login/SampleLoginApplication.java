package com.example.sample_login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com"})
@EnableJpaRepositories( basePackages = "com.example")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SampleLoginApplication {

    public static void main(String[] args) {
        String profile = System.getProperty( "spring.profiles.active" , "local" );
        String envPath = System.getProperty( "spring.config.location", "src/main/resources/env/" + profile + "/" );

        System.setProperty( "spring.profiles.active" , profile );
        System.setProperty( "spring.config.location" , envPath );

        SpringApplication sp = new SpringApplication( SampleLoginApplication.class );
        sp.run(args);

    }

}
