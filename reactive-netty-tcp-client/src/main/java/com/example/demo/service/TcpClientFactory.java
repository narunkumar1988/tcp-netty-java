package com.example.demo.service;

import com.example.demo.config.TcpClientProperties;
import com.example.demo.config.TcpConfig;
import io.netty.channel.ChannelOption;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.tcp.TcpClient;

import java.time.Duration;

@Component
public class TcpClientFactory {
    private final TcpClientProperties properties;

    public TcpClientFactory(TcpClientProperties properties) {
        this.properties = properties;
    }

    public TcpClient createTcpClient(){
        final TcpConfig config = this.properties.getConfig();
        final ConnectionProvider connectionProvider = ConnectionProvider.builder("fixed")
                .maxConnections(config.getMaxConnections())
                .maxIdleTime(Duration.ofSeconds(config.getIdleTimeout()))
                .maxLifeTime(Duration.ofSeconds(config.getMaxLifeTime())).build();
        return TcpClient.create(connectionProvider)
                .host(this.properties.getHostName())
                .port(this.properties.getPort())
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, config.getConnectionTimeoutMillis())
                .option(ChannelOption.SO_KEEPALIVE, config.isKeepAlive())
                .option(ChannelOption.TCP_NODELAY, config.isNoDelay())
                .doOnChannelInit(((connectionObserver, channel, socketAddress) -> {
                    channel.pipeline().addFirst("Logging Handler", new LoggingHandler("reactive.netty.examples"));
                    channel.pipeline().addLast("frameDecoder", new LineBasedFrameDecoder(80));
                    channel.pipeline().addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
                    channel.pipeline().addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
                }));
    }
}
