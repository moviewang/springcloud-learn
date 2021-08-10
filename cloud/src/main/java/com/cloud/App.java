/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.concurrent.ForkJoinPool;

@EnableEurekaServer
@SpringBootApplication
@EnableConfigurationProperties
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        new ForkJoinPool();
    }
}
