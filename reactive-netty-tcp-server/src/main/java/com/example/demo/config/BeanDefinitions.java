package com.example.demo.config;

import io.netty.channel.Channel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.DisposableServer;

@Configuration
public class BeanDefinitions {

    @Bean
    public DisposableServer createDisposableServer(){
        return new DisposableServer() {
            @Override
            public Channel channel() {
                return null;
            }
        };
    }
}
