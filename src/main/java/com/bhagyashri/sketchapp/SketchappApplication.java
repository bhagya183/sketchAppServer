package com.bhagyashri.sketchapp;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//(exclude=DataSourceAutoConfiguration.class)
@EnableJpaAuditing
@EnableConfigurationProperties({FileProperties.class})
@EntityScan(basePackages = "com.bhagyashri.sketchapp.entity")
public class SketchappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SketchappApplication.class, args);
	}

}
