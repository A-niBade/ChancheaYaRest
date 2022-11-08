package com.chanchearest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.chanchearest.entities")
public class ChancheayaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChancheayaApplication.class, args);
	}

}
