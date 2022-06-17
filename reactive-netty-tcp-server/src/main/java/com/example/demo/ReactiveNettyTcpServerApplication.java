package com.example.demo;

import com.example.demo.config.TcpServerConfiguration;
import com.example.demo.server.TCPServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({TcpServerConfiguration.class})
public class ReactiveNettyTcpServerApplication {

	private final TCPServer tcpServer;

	public ReactiveNettyTcpServerApplication(TCPServer tcpServer) {
		this.tcpServer = tcpServer;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveNettyTcpServerApplication.class, args);
	}

}
