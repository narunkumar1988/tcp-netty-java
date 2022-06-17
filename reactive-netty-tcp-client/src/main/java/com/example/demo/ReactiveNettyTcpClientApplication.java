package com.example.demo;

import com.example.demo.config.TcpClientProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({TcpClientProperties.class})
public class ReactiveNettyTcpClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveNettyTcpClientApplication.class, args);
	}

}
