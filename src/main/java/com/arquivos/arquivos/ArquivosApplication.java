package com.arquivos.arquivos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ArquivosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArquivosApplication.class, args);
	}

}
