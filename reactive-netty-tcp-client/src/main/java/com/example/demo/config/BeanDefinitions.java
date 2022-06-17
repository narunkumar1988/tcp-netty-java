package com.example.demo.config;

import com.example.demo.service.TcpClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.tcp.TcpClient;

@Configuration
public class BeanDefinitions {

    private final TcpClientFactory clientFactory;

    public BeanDefinitions(TcpClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Bean
    public TcpClient createTcpClient(){
        return this.clientFactory.createTcpClient();
    }
}
